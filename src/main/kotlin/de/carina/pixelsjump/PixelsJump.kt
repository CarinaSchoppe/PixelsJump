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

import de.carina.pixelsjump.commands.CommandRegister
import de.carina.pixelsjump.events.PlayerMovesInJumpNRun
import de.carina.pixelsjump.events.extra.PlayerDamage
import de.carina.pixelsjump.events.extra.PlayerJoin
import de.carina.pixelsjump.events.inventory.ArenaInventoriesListener
import de.carina.pixelsjump.events.inventory.CommandInventoryListener
import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.files.Configuration
import de.carina.pixelsjump.util.files.Messages
import de.carina.pixelsjump.util.stats.Statistics
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin

class PixelsJump : JavaPlugin() {


    companion object {
        lateinit var instance: PixelsJump
        var prefix = "§8[§6PixelsJump§8]§r"
        val utility = de.carina.pixelsjump.util.Utility()

    }


    override fun onEnable() {
        instance = this
        Configuration.loadConfig()
        prefix = ChatColor.translateAlternateColorCodes('&', Configuration.ymlConfiguration.getString("prefix")!!)
        Messages.loadMessages()
        Statistics.loadStats()
        ArenaHelper.loadArenas()
        // Plugin startup logic
        init(Bukkit.getPluginManager())
        utility.sendMessage("load")

    }

    override fun onDisable() {
        // Plugin shutdown logic
        utility.sendMessage("unload")

    }


    private fun init(pluginManager: PluginManager) {
        getCommand("pixelsjump")!!.setExecutor(CommandRegister())
        pluginManager.registerEvents(CommandInventoryListener(), this)
        pluginManager.registerEvents(ArenaInventoriesListener(), this)
        pluginManager.registerEvents(PlayerJoin(), this)
        pluginManager.registerEvents(PlayerDamage(), this)
        pluginManager.registerEvents(PlayerMovesInJumpNRun(), this)
    }
}
