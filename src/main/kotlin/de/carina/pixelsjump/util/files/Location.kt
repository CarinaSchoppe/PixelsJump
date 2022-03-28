/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 14:17 by Carina The Latest changes made by Carina on 28.03.22, 14:17.
 *  All contents of "Location.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.util.files

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class Location(val name: String, val Location: org.bukkit.Location) {
    private lateinit var file: File
    lateinit var ymlConfiguration: YamlConfiguration

    init {
        file = File("plugins/PixelsJumpRemastered/locations/$name.yml")
        ymlConfiguration = YamlConfiguration.loadConfiguration(file)
    }

    fun save() {
        ymlConfiguration.set("world", Location.world.name)
        ymlConfiguration.set("x", Location.x)
        ymlConfiguration.set("y", Location.y)
        ymlConfiguration.set("z", Location.z)
        ymlConfiguration.set("yaw", Location.yaw)
        ymlConfiguration.set("pitch", Location.pitch)
        ymlConfiguration.save(file)
    }

}
