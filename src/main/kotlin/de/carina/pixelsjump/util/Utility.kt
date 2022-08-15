/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 12:58 by Carina The Latest changes made by Carina on 28.03.22, 12:58.
 *  All contents of "Utility.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.util

import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.files.Messages
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class Utility {

    val playerInventory = mutableMapOf<Player, Inventory>()

    fun preCommandStuff(sender: CommandSender, command: Command, args: Array<out String>, argsLength: Int, commandEnter: String?, permission: String): Boolean {
        if (!command.name.equals("pixelsjump", true)) {
            return false
        }

        if (args.isEmpty()) {
            sender.sendMessage(Messages.messages["default-command"]!!)
            return false
        }

        if (sender !is Player) {
            sender.sendMessage(Messages.messages["no-player"]!!)
            return false
        }

        if (args.size < argsLength) {
            sender.sendMessage(Messages.messages["wrong-args"]!!)
            return false
        }


        if (commandEnter != null && !args[0].equals(commandEnter, true)) {
            return false
        }


        if (!sender.hasPermission(permission)) {
            sender.sendMessage(Messages.messages["no-permission"]!!)
            return false
        }
        return true
    }

    fun checkForArena(name: String, sender: Player? = null): Boolean {
        if (ArenaHelper.arenas.find { it.name == name } == null) {
            sender?.sendMessage(Messages.messages["no-arena"]!!.replace("%arena%", name))
            return false
        }
        return true
    }


    val arenaPlayerNames = mutableMapOf<Player, String?>()

}
