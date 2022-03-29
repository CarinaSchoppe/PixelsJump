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

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.BlockGenerator
import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.files.Configuration
import de.carina.pixelsjump.util.stats.Statistics
import org.bukkit.Location
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class PlayerMovesInJumpNRun : Listener {

    @EventHandler
    fun playerMovesInJumpNRun(event: PlayerMoveEvent) {
        if (!event.player.hasPermission("pixelsjump.jumpnrun")) return

        if (!ArenaHelper.playersInArenas.contains(event.player)) return

        if (event.player.location.block.getRelative(BlockFace.DOWN).location.y < BlockGenerator.playerBlockJumps[event.player]!!.location.y - 2) {
            if (ArenaHelper.arenas.find { it.players.contains(event.player) }!!.single == true) {

                event.player.sendMessage(PixelsJump.utility.messageConverter("arena-player-failed").replace("%arena%", ArenaHelper.arenas.find { it.players.contains(event.player) }!!.name))
                Statistics.addFail(event.player)
                event.player.teleport(ArenaHelper.arenas.find { it.players.contains(event.player) }!!.locations[3] as Location)
                return
            } else {
                event.player.sendMessage(PixelsJump.utility.messageConverter("arena-player-fell").replace("%arena%", ArenaHelper.arenas.find { it.players.contains(event.player) }!!.name))
                Statistics.addFail(event.player)
                event.player.teleport(BlockGenerator.playerCheckpoints[event.player]!!)
                return
            }
        }
        if (event.player.location.block.getRelative(BlockFace.DOWN).type == BlockGenerator.checkPointMaterial) {
            BlockGenerator.playerCheckpoints[event.player] = event.player.location.block.getRelative(BlockFace.DOWN).location
            event.player.sendMessage(PixelsJump.utility.messageConverter("arena-checkpoint-reached").replace("%arena%", ArenaHelper.arenas.find { it.players.contains(event.player) }!!.name))

            return
        } else if (event.player.location.block.getRelative(BlockFace.DOWN).type == BlockGenerator.endPointFinish) {
            Statistics.addWin(event.player)
            event.player.sendMessage(PixelsJump.utility.messageConverter("arena-goal-reached").replace("%arena%", ArenaHelper.arenas.find { it.players.contains(event.player) }!!.name))
            event.player.performCommand("pixelsjump leave")
            return
        }
        if (event.player.location.block.getRelative(BlockFace.DOWN).type == BlockGenerator.playerBlockJumps[event.player]!!.type && event.player.location.block.getRelative(BlockFace.DOWN).location.toCenterLocation() == BlockGenerator.playerBlockJumps[event.player]!!.location.toCenterLocation()) {
            BlockGenerator.generateBlock(event.player)
            Statistics.addPoints(event.player, Configuration.pointsPerJump)
        }

    }
}
