/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 29.03.22, 20:24 by Carina The Latest changes made by Carina on 29.03.22, 20:24.
 *  All contents of "ArenaSetup.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.commands.setup

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.files.Messages
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class ArenaSetup(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {
    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 2, "setup", "pixelsjump.setup"))
            return
        val arena = ArenaHelper.getOrCreateArena(args[1])
        sender.sendMessage(Messages.messages["arena-setup"]!!.replace("%arena%", arena.name))

    }
}
