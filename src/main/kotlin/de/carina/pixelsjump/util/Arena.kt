/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 15:03 by Carina The Latest changes made by Carina on 28.03.22, 15:03.
 *  All contents of "Arena.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.util

import de.carina.pixelsjump.PixelsJump
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File


object ArenaHelper {

    val arenas = mutableSetOf<Arena>()
    val playersInArenas = mutableSetOf<Player>()
    fun loadArenas() {
        PixelsJump.utility.sendMessage("loading-arenas-start")
        val directory = File("plugins/PixelsJumpRemastered/arenas")
        if (!directory.exists())
            directory.mkdir()

        val files = directory.listFiles()
        if (files != null) {
            for (file in files) {
                val ymlConfiguration = YamlConfiguration.loadConfiguration(file)
                val arena = Arena(file.name.replace(".yml", ""), arrayOfNulls(2))
                for (key in ymlConfiguration.getKeys(false)) {
                    if (key == "start") {
                        //load the start location based on the config
                        var location: Location
                        val world = ymlConfiguration.getString("start.world")?.let { Bukkit.getWorld(it) }
                        val x = ymlConfiguration.getInt("start.x")
                        val y = ymlConfiguration.getInt("start.y")
                        val z = ymlConfiguration.getInt("start.z")
                        val yaw = ymlConfiguration.getInt("start.yaw")
                        val pitch = ymlConfiguration.getInt("start.pitch")
                        location = Location(world, x.toDouble(), y.toDouble(), z.toDouble(), yaw.toFloat(), pitch.toFloat())
                        arena.addStartLocation(location)

                    } else if (key == "end") {
                        //load the end location based on the config
                        val location: Location
                        val world = ymlConfiguration.getString("end.world")?.let { Bukkit.getWorld(it) }
                        val x = ymlConfiguration.getInt("end.x")
                        val y = ymlConfiguration.getInt("end.y")
                        val z = ymlConfiguration.getInt("end.z")
                        val yaw = ymlConfiguration.getInt("end.yaw")
                        val pitch = ymlConfiguration.getInt("end.pitch")
                        location = Location(world, x.toDouble(), y.toDouble(), z.toDouble(), yaw.toFloat(), pitch.toFloat())
                        arena.addEndLocation(location)
                    }
                    if (key == "single") {
                        arena.single = ymlConfiguration.getBoolean(key)
                    }
                }
                Bukkit.getConsoleSender().sendMessage(PixelsJump.utility.messageConverter("arena-loaded").replace("%arena%", arena.name.replace(".yml", "")))
                arenas.add(arena)
            }
        }
        PixelsJump.utility.sendMessage("loading-arenas-end")

    }

    fun arenaExists(arenaName: String): Boolean {
        for (arena in arenas) {
            if (arena.name == arenaName) {
                return true
            }
        }
        return false
    }

    fun getArena(name: String): Arena {
        for (arena in arenas) {
            if (arena.name == name) {
                return arena
            }
        }
        val arena = Arena(name, arrayOfNulls(2))
        arenas.add(arena)
        return arena
    }
}

class Arena(val name: String, var locations: Array<Location?> = arrayOfNulls(2)) {

    val players = mutableSetOf<Player>()
    private val file: File = File("plugins/PixelsJumpRemastered/arenas/$name.yml")
    var single: Boolean? = null
    private val ymlConfiguration: YamlConfiguration = YamlConfiguration.loadConfiguration(file)

    fun saveArena() {
        ymlConfiguration.addDefault("single", single ?: false)
        if (locations[0] != null) {
            ymlConfiguration.set("start.world", locations[0]!!.world.name)
            ymlConfiguration.set("start.x", locations[0]!!.x)
            ymlConfiguration.set("start.y", locations[0]!!.y)
            ymlConfiguration.set("start.z", locations[0]!!.z)
            ymlConfiguration.set("start.yaw", locations[0]!!.yaw)
            ymlConfiguration.set("start.pitch", locations[0]!!.pitch)
        }
        if (locations[1] != null) {
            ymlConfiguration.set("end.world", locations[1]!!.world.name)
            ymlConfiguration.set("end.x", locations[1]!!.x)
            ymlConfiguration.set("end.y", locations[1]!!.y)
            ymlConfiguration.set("end.z", locations[1]!!.z)
            ymlConfiguration.set("end.yaw", locations[1]!!.yaw)
            ymlConfiguration.set("end.pitch", locations[1]!!.pitch)
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
}
