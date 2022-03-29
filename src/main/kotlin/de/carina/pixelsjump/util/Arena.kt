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
                val arena = Arena(file.name.replace(".yml", ""), arrayOfNulls(4))
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
                    } else if (key == "checkpoints") {
                        //load the checkpoints based on the config
                        ymlConfiguration.getList("checkpoints")!!.forEach {
                            arena.checkPoints.add(it as Location); Bukkit.getConsoleSender().sendMessage(PixelsJump.utility.messageConverter("checkpoint-loaded").replace("%number%", arena.checkPoints.size.toString()).replace("%arena%", arena.name))
                        }

                    } else if (key == "back") {
                        val location: Location
                        val world = ymlConfiguration.getString("back.world")?.let { Bukkit.getWorld(it) }
                        val x = ymlConfiguration.getInt("back.x")
                        val y = ymlConfiguration.getInt("back.y")
                        val z = ymlConfiguration.getInt("back.z")
                        val yaw = ymlConfiguration.getInt("back.yaw")
                        val pitch = ymlConfiguration.getInt("back.pitch")
                        location = Location(world, x.toDouble(), y.toDouble(), z.toDouble(), yaw.toFloat(), pitch.toFloat())
                        arena.addBackLocation(location)
                    } else if (key == "single") {
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
        val arena = Arena(name, arrayOfNulls(4))
        arenas.add(arena)
        return arena
    }
}

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
