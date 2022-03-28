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

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.files.Location
import de.carina.pixelsjump.util.files.Messages
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Utility {

    val playersInArena = mutableListOf<Player>()

    fun preCommandStuff(sender: CommandSender, args: Array<out String>, command: String, argsLength: Int, commandEnter: String): Boolean {
        if (!command.equals(commandEnter, true)) return false
        if (sender !is Player) {
            sender.sendMessage(messageConverter("no-player"))
            return false
        }
        if (args.size != argsLength) {
            sender.sendMessage(messageConverter("wrong-args"))
            return false
        }
        return true
    }

    fun checkForArena(name: String, sender: Player? = null): Boolean {
        if (Location.locations.find { it.name == name } == null) {
            sender?.sendMessage(messageConverter("no-arena").replace("%arena%", name))
            return false
        }
        return true
    }

    fun sendMessage(messagePath: String) {
        Bukkit.getConsoleSender().sendMessage(PixelsJump.prefix + ChatColor.translateAlternateColorCodes('&', Messages.ymlConfiguration.getString(messagePath)!!))
    }

    fun messageConverter(messagePath: String): String {
        return PixelsJump.prefix + ChatColor.translateAlternateColorCodes('&', Messages.ymlConfiguration.getString(messagePath)!!)
    }
}
