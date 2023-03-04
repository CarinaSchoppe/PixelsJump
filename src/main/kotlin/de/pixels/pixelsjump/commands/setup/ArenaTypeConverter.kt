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
import de.carina.pixelsjump.util.files.Messages
import de.carina.pixelsjump.util.misc.ConstantStrings
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class ArenaTypeConverter(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {

    /**
     * Switches the mode of the different arena types (single, multi)
     */
    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 2, "single", "pixelsjump.single"))
            return
        if (ArenaHelper.arenaNotExists(args[1])) {
            sender.sendMessage(Messages.messages["no-arena"]!!.replace(ConstantStrings.ARENA_PERCENT, args[1]))
            return
        }
        val arena = ArenaHelper.getOrCreateArena(args[1])
        if (arena.single) {
            arena.single = false
            sender.sendMessage(Messages.messages["arena-single-no"]!!.replace(ConstantStrings.ARENA_PERCENT, args[1]))
        } else {
            arena.single = true
            sender.sendMessage(Messages.messages["arena-single-yes"]!!.replace(ConstantStrings.ARENA_PERCENT, args[1]))
        }

    }
}
