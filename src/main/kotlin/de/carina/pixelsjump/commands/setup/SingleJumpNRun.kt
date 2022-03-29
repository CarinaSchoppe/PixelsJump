/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 29.03.22, 17:31 by Carina The Latest changes made by Carina on 29.03.22, 17:31.
 *  All contents of "SingleJumpNrun.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.commands.setup

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.arena.ArenaHelper
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class SingleJumpNRun(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {

    fun execute() {
        print("test2")
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 2, "single", "pixelsjump.single"))
            return
        print("test")
        if (!ArenaHelper.arenaExists(args[1])) {
            sender.sendMessage(PixelsJump.utility.messageConverter("no-arena").replace("%arena%", args[1]))
            return
        }

        val arena = ArenaHelper.arenas.find { it.name == args[1] }!!
        if (arena.single != false) {
            arena.single = false
            sender.sendMessage(PixelsJump.utility.messageConverter("arena-single-no").replace("%arena%", args[1]))
        } else {
            arena.single = true
            sender.sendMessage(PixelsJump.utility.messageConverter("arena-single-yes").replace("%arena%", args[1]))
        }

    }
}
