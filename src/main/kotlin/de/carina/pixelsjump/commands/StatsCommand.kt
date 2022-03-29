/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 29.03.22, 11:35 by Carina The Latest changes made by Carina on 29.03.22, 11:35.
 *  All contents of "StatsCommand.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.commands

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.stats.Statistics
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class StatsCommand(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {

    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 2, "stats", "pixelsjump.stats"))
            return
        if (Bukkit.getOfflinePlayerIfCached(args[1]) == null) {
            sender.sendMessage(PixelsJump.utility.messageConverter("player-no-stats").replace("%player%", args[1]))
            return
        }
        if (Statistics.statistics.find { it.uuid == Bukkit.getOfflinePlayerIfCached(args[1])!!.uniqueId } == null) {
            sender.sendMessage(PixelsJump.utility.messageConverter("player-no-stats").replace("%player%", args[1]))
            return
        }

        val stats = Statistics.statistics.find { it.uuid == Bukkit.getOfflinePlayerIfCached(args[1])!!.uniqueId }!!
        if ((sender as Player).uniqueId == stats.uuid)
            sender.sendMessage(PixelsJump.utility.messageConverter("player-stats").replace("%wins%", stats.wins.toString()).replace("%fails%", stats.fails.toString()).replace("%blocks%", stats.maxBlocks.toString()).replace("%games%", stats.games.toString()).replace("%points%", stats.points.toString()))
        else
            sender.sendMessage(PixelsJump.utility.messageConverter("player-stats-other").replace("%wins%", stats.wins.toString()).replace("%fails%", stats.fails.toString()).replace("%blocks%", stats.maxBlocks.toString()).replace("%games%", stats.games.toString()).replace("%points%", stats.points.toString()).replace("%player%", args[1]))

    }
}
