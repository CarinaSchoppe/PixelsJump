/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 29.03.22, 18:42 by Carina The Latest changes made by Carina on 29.03.22, 18:42.
 *  All contents of "CheckPoint.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.commands.arena

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.BlockGenerator
import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.files.Messages
import de.carina.pixelsjump.util.stats.PlayerStatsHandler
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Checkpoint(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {

    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 1, "checkpoint", "pixelsjump.checkpoint")) return
        if (!ArenaHelper.playersInArenas.contains(sender)) {
            sender.sendMessage(Messages.messages["no-jump"]!!)
            return
        }

        PlayerStatsHandler.addFail(sender as Player)
        sender.level += 1
        sender.playSound(sender.location, Sound.BLOCK_ANVIL_USE, 1f, 1f)
        sender.teleport(BlockGenerator.playerCheckpoints[sender]!!.toLocation().add(0.toDouble(), 1.toDouble(), 0.toDouble()))
        sender.sendMessage(Messages.messages["arena-player-fell"]!!.replace("%arena%", ArenaHelper.arenas.find { it.players.contains(sender) }!!.name))

    }
}
