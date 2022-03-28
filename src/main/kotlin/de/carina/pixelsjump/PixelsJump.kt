/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 12:29 by Carina The Latest changes made by Carina on 28.03.22, 12:22.
 *  All contents of "PixelsJump.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump

import de.carina.pixelsjump.util.files.Configuration
import de.carina.pixelsjump.util.files.Messages
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

class PixelsJump : JavaPlugin() {


    companion object {
        lateinit var instance: PixelsJump
        var prefix = "§8[§6PixelsJump§8]§r"

        fun sendMessage(messagePath: String) {
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', Messages.ymlConfiguration.getString("message")!!))
        }

        fun messageConverter(messagePath: String): String {
            return prefix + ChatColor.translateAlternateColorCodes('&', Messages.ymlConfiguration.getString("messagePath")!!)
        }
    }

    override fun onEnable() {
        instance = this
        Configuration.loadConfig()
        prefix = ChatColor.translateAlternateColorCodes('&', Configuration.ymlConfiguration.getString("prefix")!!)
        Messages.loadMessages()
        // Plugin startup logic
        sendMessage("load")

    }

    override fun onDisable() {
        // Plugin shutdown logic
        sendMessage("unload")

    }

}
