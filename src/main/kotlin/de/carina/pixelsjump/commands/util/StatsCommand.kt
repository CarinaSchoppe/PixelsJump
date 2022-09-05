/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 8/15/22, 12:54 PM by Carina The Latest changes made by Carina on 8/15/22, 12:54 PM.
 *  All contents of "StatsCommand.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.commands.util

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.PlayerStatsHandler
import de.carina.pixelsjump.util.files.Messages
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class StatsCommand(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {


    /**
     * check if the players stats exist and than print them out based on own stats, foreign stats or not existing stats
     * */
    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 2, "stats", "pixelsjump.stats"))
            return
        if (Bukkit.getOfflinePlayerIfCached(args[1]) == null) {
            sender.sendMessage(Messages.messages["player-no-stats"]!!.replace("%player%", args[1]))
            return
        }
        if (PlayerStatsHandler.statistics.find { it.uuid == Bukkit.getOfflinePlayerIfCached(args[1])!!.uniqueId } == null) {
            sender.sendMessage(Messages.messages["player-no-stats"]!!.replace("%player%", args[1]))
            return
        }

        val stats = PlayerStatsHandler.statistics.find { it.uuid == Bukkit.getOfflinePlayerIfCached(args[1])!!.uniqueId }!!
        if ((sender as Player).uniqueId == stats.uuid)
            sender.sendMessage(Messages.messages["player-stats"]!!.replace("%wins%", stats.wins.toString()).replace("%fails%", stats.fails.toString()).replace("%games%", stats.games.toString()).replace("%points%", stats.points.toString()))
        else
            sender.sendMessage(Messages.messages["player-stats-other"]!!.replace("%wins%", stats.wins.toString()).replace("%fails%", stats.fails.toString()).replace("%games%", stats.games.toString()).replace("%points%", stats.points.toString()).replace("%player%", args[1]))

    }
}
