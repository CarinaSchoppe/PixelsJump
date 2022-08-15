/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 15:25 by Carina The Latest changes made by Carina on 28.03.22, 15:25.
 *  All contents of "LocationFinish.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
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
import org.bukkit.entity.Player

class ArenaFinish(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {

    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 2, "finish", "pixelsjump.finish-arena"))
            return

        if (ArenaHelper.arenaNotExists(args[1])) {
            sender.sendMessage(Messages.messages["no-arena"]!!.replace("%arena%", args[1]))
            return
        }
        val arena = ArenaHelper.getOrCreateArena(args[1])
        if (arena.startLocation == null) {
            sender.sendMessage(Messages.messages["arena-not-valid"]!!.replace("%arena%", args[1]))
            return
        }
        if (arena.backLocation == null) {
            sender.sendMessage(Messages.messages["arena-not-valid"]!!.replace("%arena%", args[1]))
            return
        }

        if (!arena.single && arena.finishLocation == null) {
            sender.sendMessage(Messages.messages["arena-not-valid"]!!.replace("%arena%", args[1]))
            return
        }

        if (arena.single) {
            sender.sendMessage(Messages.messages["arena-single"]!!.replace("%arena%", args[1]))
        }


        sender.sendMessage(Messages.messages["arena-saved"]!!.replace("%arena%", args[1]))
        PixelsJump.utility.arenaPlayerNames.remove(sender as Player)
        arena.saveArena()

    }
}
