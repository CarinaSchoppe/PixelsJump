/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 29.03.22, 16:45 by Carina The Latest changes made by Carina on 29.03.22, 16:45.
 *  All contents of "PlayerMovesInJumpNRun.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.events

import de.carina.pixelsjump.util.BlockGenerator
import de.carina.pixelsjump.util.CustomLocation
import de.carina.pixelsjump.util.arena.Arena
import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.files.Configuration
import de.carina.pixelsjump.util.files.Messages
import de.carina.pixelsjump.util.stats.PlayerStats
import org.bukkit.Sound
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class PlayerMoves : Listener {

    @EventHandler
    fun playerMovesInJumpNRun(event: PlayerMoveEvent) {
        if (!event.player.hasPermission("pixelsjump.jumpnrun")) return
        if (!ArenaHelper.playersInArenas.contains(event.player)) return
        //player afk stuff
        val arena = ArenaHelper.arenas.find { it.players.contains(event.player) }!!
        BlockGenerator.playerAFK[event.player] = Pair(BlockGenerator.playerAFK[event.player]!!.first, false)
        //check if a player fails
        if (failChecker(arena, event)) return
        //check if a good event is happening like checkpoint or win
        if (blockRelated(event, arena) || locationRelated(arena, event)) {
            event.player.playSound(event.player.location, Sound.ENTITY_PLAYER_LEVELUP, 1.toFloat(), 1.toFloat())
            return
        }

        //if player is in singleplayer mode and on the next jump block -> generate a new jump block
        if (arena.single && event.player.location.block.getRelative(BlockFace.DOWN).type == BlockGenerator.playerBlock[event.player]!!.type && event.player.location.block.getRelative(BlockFace.DOWN).location.toCenterLocation() == BlockGenerator.playerBlock[event.player]!!.location.toCenterLocation()) {
            BlockGenerator.generateBlock(event.player)
            PlayerStats.addPoints(event.player, Configuration.pointsPerJump)
        }
    }

    /**
     * checks if the player is moving on a checkpointblock or on a "winning" block
     */
    private fun blockRelated(event: PlayerMoveEvent, arena: Arena): Boolean {
        if (arena.single) return false
        //check if its a checkpointblock
        if (event.player.location.block.getRelative(BlockFace.DOWN).type == BlockGenerator.checkPointMaterial && BlockGenerator.playerCheckpoints[event.player]!! != CustomLocation(event.player.location.block.getRelative(BlockFace.DOWN).location.toCenterLocation())) {
            BlockGenerator.playerCheckpoints[event.player] = CustomLocation(event.player.location.block.getRelative(BlockFace.DOWN).location.toCenterLocation().add(0.0, 1.0, 0.0))
            event.player.sendMessage(Messages.messages["arena-checkpoint-reached"]!!.replace("%arena%", ArenaHelper.arenas.find { it.players.contains(event.player) }!!.name))
            return true
            //check if its the winning block
        } else if (event.player.location.block.getRelative(BlockFace.DOWN).type == BlockGenerator.endPointFinish && BlockGenerator.playerCheckpoints[event.player]!! != CustomLocation(event.player.location.block.getRelative(BlockFace.DOWN).location.toCenterLocation())) {
            jumpWon(event.player, arena)
            return true
        }
        return false
    }

    /**
     * player has won!
     */
    private fun jumpWon(player: Player, arena: Arena) {
        if (arena.single) return
        PlayerStats.addWin(player)
        player.sendMessage(Messages.messages["arena-goal-reached"]!!.replace("%arena%", ArenaHelper.arenas.find { it.players.contains(player) }!!.name))
        player.performCommand("pixelsjump leave")
        return
    }


    /**
     * Check if the player in the arena is done with the jump and run.
     * based on the currentPlayerLocation and the location saved in the arena settings
     * if some of that corresponds: it fires true
     * @return if a checkpoint has been reached
     */
    private fun locationRelated(arena: Arena, event: PlayerMoveEvent): Boolean {
        if (arena.single) return false
        if (arena.finishLocation == CustomLocation(event.player.location.toCenterLocation()) && !arena.single) {
            jumpWon(event.player, arena)
            return true
        }
        if (arena.checkPoints.isEmpty())
            return false
        //check if the current position corresponts to a checkpoint in the arena checkpoints and the current checkpoint != locationCheckpoint
        if (arena.checkPoints.size > arena.checkPoints.indexOf(BlockGenerator.playerCheckpoints[event.player]!!) + 1 && CustomLocation(event.player.location.toCenterLocation().subtract(0.toDouble(), 1.toDouble(), 0.toDouble())) == arena.checkPoints[arena.checkPoints.indexOf(BlockGenerator.playerCheckpoints[event.player]!!) + 1]) {
            BlockGenerator.playerCheckpoints[event.player] = arena.checkPoints[arena.checkPoints.indexOf(BlockGenerator.playerCheckpoints[event.player]!!) + 1]
            event.player.sendMessage(Messages.messages["arena-checkpoint-reached"]!!.replace("%arena%", ArenaHelper.arenas.find { it.players.contains(event.player) }!!.name))
            return true
        }
        return false
    }


    /**
     * @return checks if a player based on his position and the current arena has done a failing move
     */
    private fun failChecker(arena: Arena, event: PlayerMoveEvent): Boolean {
        //Player is in a single arena
        if (arena.single && event.player.location.block.getRelative(BlockFace.DOWN).location.y < BlockGenerator.playerBlock[event.player]!!.location.y - 2) {
            event.player.sendMessage(Messages.messages["arena-player-failed"]!!.replace("%arena%", ArenaHelper.arenas.find { it.players.contains(event.player) }!!.name))
            PlayerStats.addFail(event.player)
            event.player.performCommand("pixelsjump leave")
            return true
        }
        //player is in a multi arena
        if (event.player.location.block.getRelative(BlockFace.DOWN).location.y < BlockGenerator.playerCheckpoints[event.player]!!.y - 3) {
            event.player.sendMessage(Messages.messages["arena-player-fell"]!!.replace("%arena%", ArenaHelper.arenas.find { it.players.contains(event.player) }!!.name))
            PlayerStats.addFail(event.player)
            return true
        }

        return false
    }
}
