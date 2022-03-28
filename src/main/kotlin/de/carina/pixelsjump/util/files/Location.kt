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

    companion object {
        val locations = mutableListOf<Location>()
        fun load() {
            val directory = File("plugins/PixelsJumpRemastered/locations")
            if (!directory.exists()) directory.mkdir()
            for (file in directory.listFiles()) {
                val config = YamlConfiguration.loadConfiguration(file)
                val name = file.name.replace(".yml", "")
                val world = config.getString("world")
                val x = config.getDouble("x")
                val y = config.getDouble("y")
                val z = config.getDouble("z")
                val yaw = config.getDouble("yaw")
                val pitch = config.getDouble("pitch")
                val location = org.bukkit.Location(org.bukkit.Bukkit.getWorld(world!!), x, y, z, yaw.toFloat(), pitch.toFloat())
                locations.add(Location(name, location))
            }
        }
    }

    private var file: File = File("plugins/PixelsJumpRemastered/locations/$name.yml")
    private var ymlConfiguration: YamlConfiguration = YamlConfiguration.loadConfiguration(file)


    fun saveLocation() {
        ymlConfiguration.set("world", Location.world.name)
        ymlConfiguration.set("x", Location.x)
        ymlConfiguration.set("y", Location.y)
        ymlConfiguration.set("z", Location.z)
        ymlConfiguration.set("yaw", Location.yaw)
        ymlConfiguration.set("pitch", Location.pitch)
        ymlConfiguration.save(file)
    }

}
