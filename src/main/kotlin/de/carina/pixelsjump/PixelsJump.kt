package de.carina.pixelsjump

import org.bukkit.plugin.java.JavaPlugin

class PixelsJump : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        println("PixelsJump wurde aktiviert!")
    }

    override fun onDisable() {
        // Plugin shutdown logic
        println("PixelsJump wurde deaktiviert!")
    }

}
