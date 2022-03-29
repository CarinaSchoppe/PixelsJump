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
    private lateinit var configFile: File
    lateinit var ymlConfiguration: YamlConfiguration
    fun loadConfig() {
        configFile = File(path)
        ymlConfiguration = YamlConfiguration.loadConfiguration(configFile)


        ymlConfiguration.addDefault("arena-break", 50)
        ymlConfiguration.addDefault("arena-place", 50)
        ymlConfiguration.addDefault("jump-points", 1)
        ymlConfiguration.addDefault("prefix", "&8[&6PixelsJump&8]&r")
        saveConfigFile()
    }

    private fun saveConfigFile() {
        ymlConfiguration.options().copyDefaults(true)
        ymlConfiguration.save(configFile)

    }
}
