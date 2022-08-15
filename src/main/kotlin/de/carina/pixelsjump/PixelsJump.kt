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

import de.carina.pixelsjump.commands.util.CommandRegister
import de.carina.pixelsjump.events.PlayerMoves
import de.carina.pixelsjump.events.extra.*
import de.carina.pixelsjump.events.inventory.ArenaInventoriesListener
import de.carina.pixelsjump.events.inventory.CommandInventoryListener
import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.files.Configuration
import de.carina.pixelsjump.util.files.Messages
import de.carina.pixelsjump.util.sign.SignCreator
import de.carina.pixelsjump.util.stats.PlayerStats
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin

class PixelsJump : JavaPlugin() {

    /*
    * TODO: Single Jump modus in bezug auf end
    * TODO: Single jump arena no goal possible
    * TODO: Endlocation?
    * TODO: Hiding?
    * TODO: Multi player
    * TODO: checkPoints
    * TODO: jump bloks
    * TODO: Edit commands on premise in GUI?
    * TODO: leave item?
    * TODO: Hide all other players item?
    *  TODO: Code kommentieren
    * TODO: Singlejump no to checkpoint
    *  TODO: movement for checkpoints
    *   TODO: Damage when not in same arena
    * TODO: Player Chat
    * */


    companion object {
        lateinit var instance: PixelsJump
        var prefix = "§8[§6PixelsJump§8]§r"
        val utility = de.carina.pixelsjump.util.Utility()

    }


    override fun onEnable() {
        instance = this
        Configuration.initiateConfig()
        prefix = ChatColor.translateAlternateColorCodes('&', Configuration.config["prefix"]!!.toString())
        Messages.createMessagesFile()
        PlayerStats.loadStats()
        ArenaHelper.loadArenas()
        // Plugin startup logic
        init(Bukkit.getPluginManager())
        Messages.messages["load"]!!
    }

    override fun onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage(Messages.messages["unload"]!!)


    }


    private fun init(pluginManager: PluginManager) {
        getCommand("pixelsjump")!!.setExecutor(CommandRegister())
        pluginManager.registerEvents(CommandInventoryListener(), this)
        pluginManager.registerEvents(ArenaInventoriesListener(), this)
        pluginManager.registerEvents(PlayerJoin(), this)
        pluginManager.registerEvents(PlayerDamage(), this)
        pluginManager.registerEvents(PlayerMoves(), this)
        pluginManager.registerEvents(Blocks(), this)
        pluginManager.registerEvents(PlayerChat(), this)
        pluginManager.registerEvents(Checkpoint(), this)
        pluginManager.registerEvents(SignCreator(), this)

        extra()
    }

    private fun extra() {
        Configuration.pointsPerJump = Configuration.config["jump-points"] as Int
        Configuration.arenaBreak = Configuration.config["arena-break"] as Boolean
        Configuration.arenaPlace = Configuration.config["arena-place"] as Boolean

    }
}
