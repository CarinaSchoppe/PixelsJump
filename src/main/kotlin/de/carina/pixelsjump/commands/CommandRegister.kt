/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 14:48 by Carina The Latest changes made by Carina on 28.03.22, 14:48.
 *  All contents of "CommandRegister.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.commands

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.commands.arena.ArenaInventory
import de.carina.pixelsjump.commands.arena.JoinArena
import de.carina.pixelsjump.commands.arena.LeaveArena
import de.carina.pixelsjump.commands.setup.*
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class CommandRegister : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 0, null, "pixelsjump.use")) {
            return true
        }
        when (args[0]) {
            "add" -> LocationAdder(sender, command, args).execute()
            "remove" -> ArenaRemover(sender, command, args).execute()
            "list" -> LocationList(sender, command, args).execute()
            "single" -> LocationList(sender, command, args).execute()
            "finish" -> LocationFinish(sender, command, args).execute()
            "gui" -> InventoryStart(sender, command, args).execute()
            "arenas" -> ArenaInventory(sender, command, args).execute()
            "single" -> SingleJumpNRun(sender, command, args).execute()
            "join" -> JoinArena(sender, command, args).execute()
            "leave" -> LeaveArena(sender, command, args).execute()
            "stats" -> StatsCommand(sender, command, args).execute()
            "damage" -> ArenaDamage(sender, command, args).execute()
        }

        return true
    }
}
