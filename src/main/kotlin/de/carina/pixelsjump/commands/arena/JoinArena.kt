/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 29.03.22, 11:11 by Carina The Latest changes made by Carina on 29.03.22, 11:11.
 *  All contents of "JoinArena.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.commands.arena

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.ArenaHelper
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class JoinArena(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {

    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 2, "join", "pixelsjump.join")) return
        if (!PixelsJump.utility.checkForArena(args[1], sender as Player)) return
        val arena = ArenaHelper.getArena(args[1])
        if (ArenaHelper.playersInArenas.contains(sender)) {
            sender.sendMessage(PixelsJump.utility.messageConverter("arena-allready").replace("%arena%", args[1]))
            return
        }
        arena.players.add(sender)
        ArenaHelper.playersInArenas.add(sender)
        sender.sendMessage(PixelsJump.utility.messageConverter("arena-join").replace("%arena%", args[1]))
        sender.teleport(arena.locations[0]!!)
    }
}
