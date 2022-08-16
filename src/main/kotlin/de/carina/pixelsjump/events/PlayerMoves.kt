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
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class PlayerMoves : Listener {

    @EventHandler
    fun playerMovesInJumpNRun(event: PlayerMoveEvent) {
        if (!event.player.hasPermission("pixelsjump.jumpnrun")) return

        if (!ArenaHelper.playersInArenas.contains(event.player)) return
        val arena = ArenaHelper.arenas.find { it.players.contains(event.player) }!!
        BlockGenerator.playerAFK[event.player] = Pair(BlockGenerator.playerAFK[event.player]!!.first, false)
        if (failChecker(arena, event)) return
        if (blockRelated(event, arena) || locationRelated(arena, event)) {
            event.player.playSound(event.player.location, Sound.ENTITY_PLAYER_LEVELUP, 1.toFloat(), 1.toFloat())
            return
        }
        if (arena.single && event.player.location.block.getRelative(BlockFace.DOWN).type == BlockGenerator.playerBlock[event.player]!!.type && event.player.location.block.getRelative(BlockFace.DOWN).location.toCenterLocation() == BlockGenerator.playerBlock[event.player]!!.location.toCenterLocation()) {
            BlockGenerator.generateBlock(event.player)
            PlayerStats.addPoints(event.player, Configuration.pointsPerJump)
        }
    }

    private fun blockRelated(event: PlayerMoveEvent, arena: Arena): Boolean {
        if (arena.single) return false
        if (event.player.location.block.getRelative(BlockFace.DOWN).type == BlockGenerator.checkPointMaterial && BlockGenerator.playerCheckpoints[event.player]!! != CustomLocation(event.player.location.block.getRelative(BlockFace.DOWN).location.toCenterLocation())) {
            BlockGenerator.playerCheckpoints[event.player] = CustomLocation(event.player.location.block.getRelative(BlockFace.DOWN).location.toCenterLocation().add(0.0, 1.0, 0.0))
            event.player.sendMessage(Messages.messages["arena-checkpoint-reached"]!!.replace("%arena%", ArenaHelper.arenas.find { it.players.contains(event.player) }!!.name))
            return true
        } else if (event.player.location.block.getRelative(BlockFace.DOWN).type == BlockGenerator.endPointFinish && BlockGenerator.playerCheckpoints[event.player]!! != CustomLocation(event.player.location.block.getRelative(BlockFace.DOWN).location.toCenterLocation())) {
            jumpWon(event, arena)
            return true
        }
        return false
    }

    private fun jumpWon(event: PlayerMoveEvent, arena: Arena) {
        if (arena.single) return
        PlayerStats.addWin(event.player)
        event.player.sendMessage(Messages.messages["arena-goal-reached"]!!.replace("%arena%", ArenaHelper.arenas.find { it.players.contains(event.player) }!!.name))
        event.player.performCommand("pixelsjump leave")
        return
    }


    /**
     * Check if the player in the arena is done with the jump and run.
     */
    private fun locationRelated(arena: Arena, event: PlayerMoveEvent): Boolean {
        if (arena.single) return false
        if (arena.finishLocation == CustomLocation(event.player.location.toCenterLocation()) && !arena.single) {
            jumpWon(event, arena)
            return true
        }
        if (arena.checkPoints.isEmpty())
            return false
        if (arena.checkPoints.size > arena.checkPoints.indexOf(BlockGenerator.playerCheckpoints[event.player]!!) + 1 && CustomLocation(event.player.location.toCenterLocation().subtract(0.toDouble(), 1.toDouble(), 0.toDouble())) == arena.checkPoints[arena.checkPoints.indexOf(BlockGenerator.playerCheckpoints[event.player]!!) + 1]) {
            BlockGenerator.playerCheckpoints[event.player] = arena.checkPoints[arena.checkPoints.indexOf(BlockGenerator.playerCheckpoints[event.player]!!) + 1]
            event.player.sendMessage(Messages.messages["arena-checkpoint-reached"]!!.replace("%arena%", ArenaHelper.arenas.find { it.players.contains(event.player) }!!.name))
            return true
        }
        return false
    }


    private fun failChecker(arena: Arena, event: PlayerMoveEvent): Boolean {
        if (arena.single) {
            if (event.player.location.block.getRelative(BlockFace.DOWN).location.y < BlockGenerator.playerBlock[event.player]!!.location.y - 2) {
                event.player.sendMessage(Messages.messages["arena-player-failed"]!!.replace("%arena%", ArenaHelper.arenas.find { it.players.contains(event.player) }!!.name))
                PlayerStats.addFail(event.player)
                event.player.performCommand("pixelsjump leave")
                return true
            }
        } else {
            print("here")
            if (event.player.location.block.getRelative(BlockFace.DOWN).location.y < BlockGenerator.playerCheckpoints[event.player]!!.y - 3) {
                event.player.sendMessage(Messages.messages["arena-player-fell"]!!.replace("%arena%", ArenaHelper.arenas.find { it.players.contains(event.player) }!!.name))
                PlayerStats.addFail(event.player)
                return true
            }
        }
        return false
    }
}
