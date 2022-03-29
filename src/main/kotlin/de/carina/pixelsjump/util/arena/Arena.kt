/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 29.03.22, 16:12 by Carina The Latest changes made by Carina on 29.03.22, 15:57.
 *  All contents of "Arena.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.util.arena

import org.bukkit.Location
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File


class Arena(val name: String, var locations: Array<Any?> = arrayOfNulls(4)) {
    val checkPoints: MutableList<Location> = mutableListOf()

    init {
        if (locations[2] != null) {
            checkPoints.addAll(locations[2] as MutableList<Location>)
        }
    }

    val players = mutableSetOf<Player>()
    private val file: File = File("plugins/PixelsJumpRemastered/arenas/$name.yml")
    var single: Boolean? = null
    private val ymlConfiguration: YamlConfiguration = YamlConfiguration.loadConfiguration(file)

    fun saveArena() {
        ymlConfiguration.addDefault("single", single ?: false)
        if (locations[0] != null) {
            ymlConfiguration.set("start.world", (locations[0]!! as Location).world.name)
            ymlConfiguration.set("start.x", (locations[0]!! as Location).x)
            ymlConfiguration.set("start.y", (locations[0]!! as Location).y)
            ymlConfiguration.set("start.z", (locations[0]!! as Location).z)
            ymlConfiguration.set("start.yaw", (locations[0]!! as Location).yaw)
            ymlConfiguration.set("start.pitch", (locations[0]!! as Location).pitch)
        }
        if (locations[1] != null) {
            ymlConfiguration.set("end.world", (locations[1]!! as Location).world.name)
            ymlConfiguration.set("end.x", (locations[1]!! as Location).x)
            ymlConfiguration.set("end.y", (locations[1]!! as Location).y)
            ymlConfiguration.set("end.z", (locations[1]!! as Location).z)
            ymlConfiguration.set("end.yaw", (locations[1]!! as Location).yaw)
            ymlConfiguration.set("end.pitch", (locations[1]!! as Location).pitch)
        }

        if (checkPoints.isNotEmpty()) {
            ymlConfiguration.set("checkpoints", checkPoints)
        }

        if (locations[3] != null) {
            ymlConfiguration.set("back.world", (locations[3]!! as Location).world.name)
            ymlConfiguration.set("back.x", (locations[3]!! as Location).x)
            ymlConfiguration.set("back.y", (locations[3]!! as Location).y)
            ymlConfiguration.set("back.z", (locations[3]!! as Location).z)
            ymlConfiguration.set("back.yaw", (locations[3]!! as Location).yaw)
            ymlConfiguration.set("back.pitch", (locations[3]!! as Location).pitch)
        }

        ymlConfiguration.options().copyDefaults(true)
        ymlConfiguration.save(file)
    }

    fun addStartLocation(location: Location) {
        locations[0] = location
    }

    fun addEndLocation(location: Location) {
        locations[1] = location
    }

    fun addCheckpointLocation(location: Location) {
        checkPoints.add(location)
    }

    fun setOnlyFirst() {
        ymlConfiguration.set("single", true)
    }

    fun isSingleArena(): Boolean {
        return try {
            if (single == null) {
                single = ymlConfiguration.getBoolean("single")
            }
            single!!
        } catch (e: Exception) {
            false
        }
    }

    fun addBackLocation(location: Location) {
        locations[3] = location

    }
}