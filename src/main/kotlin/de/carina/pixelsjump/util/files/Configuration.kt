/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 12:39 by Carina The Latest changes made by Carina on 28.03.22, 12:39.
 *  All contents of "Config.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.util.files

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File


object Configuration {

    var pointsPerJump = 1
    var arenaBreak = false
    var arenaPlace = false
    private const val path = "plugins/PixelsJumpRemastered/config.yml"
    var configFile: File = File(path)
    var ymlConfiguration: YamlConfiguration = YamlConfiguration.loadConfiguration(configFile)
    val config = mutableMapOf<String, Any>()


    private fun loadConfig() {
        ymlConfiguration.getKeys(false).forEach { key ->
            config[key] = ymlConfiguration.get(key) as Any
        }
    }

    fun initiateConfig() {
        configFile = File(path)
        ymlConfiguration = YamlConfiguration.loadConfiguration(configFile)

        ymlConfiguration.addDefault("arena-break", true)
        ymlConfiguration.addDefault("arena-place", true)
        ymlConfiguration.addDefault("jump-points", 1)
        ymlConfiguration.addDefault("mysql", true)
        ymlConfiguration.addDefault("sqlite", true)
        ymlConfiguration.addDefault("sqlite-path", "plugins/TheHunter/sqlite.db")
        ymlConfiguration.addDefault("mysql-host", "localhost")
        ymlConfiguration.addDefault("mysql-port", 3306)
        ymlConfiguration.addDefault("mysql-database", "thehunter")
        ymlConfiguration.addDefault("mysql-user", "root")
        ymlConfiguration.addDefault("mysql-password", "")
        ymlConfiguration.addDefault("scoreboard", true)
        ymlConfiguration.addDefault("prefix", "&8[&6PixelsJump&8]&r")


        saveConfigFile()
        loadConfig()
    }

    private fun saveConfigFile() {
        ymlConfiguration.options().copyDefaults(true)
        ymlConfiguration.save(configFile)

    }
}
