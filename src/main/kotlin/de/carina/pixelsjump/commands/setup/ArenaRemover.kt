/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 14:56 by Carina The Latest changes made by Carina on 28.03.22, 14:56.
 *  All contents of "LocationRemover.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
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
import java.io.File

class ArenaRemover(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {
    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 2, "remove", "pixelsjump.remove"))
            return

        if (!ArenaHelper.arenaNotExists(args[1])) {
            sender.sendMessage(PixelsJump.utility.messageConverter("no-arena").replace("%arena%", args[1]))
            return
        }
        ArenaHelper.arenas.forEach {
            if (it.name == args[1]) {
                ArenaHelper.arenas.remove(it)
                sender.sendMessage(PixelsJump.utility.messageConverter("arena-removed").replace("%arena%", args[1]))
                File("plugins/PixelsJumpRemastered/arenas/${args[1]}.yml").delete()
                return
            }
        }

    }


}
