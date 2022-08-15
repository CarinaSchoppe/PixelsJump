/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 29.03.22, 16:37 by Carina The Latest changes made by Carina on 29.03.22, 16:37.
 *  All contents of "ArenaDamage.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
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

class ArenaDamage(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {
    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 2, "damage", "pixelsjump.arena.damage"))
            return
        if (!ArenaHelper.arenaNotExists(args[1])) {
            sender.sendMessage(PixelsJump.utility.messageConverter("no-arena").replace("%arena%", args[1]))
            return
        }
        if (ArenaHelper.getArena(args[1]).damage == false) {
            ArenaHelper.getArena(args[1]).damage = true
            sender.sendMessage(PixelsJump.utility.messageConverter("arena-damage-yes").replace("%arena%", args[1]))
            return
        } else {
            ArenaHelper.getArena(args[1]).damage = false
            sender.sendMessage(PixelsJump.utility.messageConverter("arena-damage-no").replace("%arena%", args[1]))
            return
        }

    }
}
