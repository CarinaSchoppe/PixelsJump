/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 29.03.22, 16:12 by Carina The Latest changes made by Carina on 29.03.22, 16:12.
 *  All contents of "ArenaHelper.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.util.arena

import com.google.gson.Gson
import de.carina.pixelsjump.util.files.Messages
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.io.File

object ArenaHelper {

    val arenas = mutableSetOf<Arena>()
    val playersInArenas = mutableSetOf<Player>()
    fun loadArenas() {
        Bukkit.getConsoleSender().sendMessage(Messages.messages["loading-arenas-start"]!!)
        val directory = File("plugins/PixelsJumpRemastered/arenas")
        if (!directory.exists())
            directory.mkdir()

        val files = directory.listFiles()
        if (files != null) {
            for (file in files) {
                if (!file.extension.endsWith("json")) {
                    file.delete()
                    continue
                }
                val arena = Gson().fromJson(file.bufferedReader(), Arena::class.java)
                arena.players = mutableSetOf()
                Bukkit.getConsoleSender().sendMessage(Messages.messages["arena-loaded"]!!.replace("%arena%", arena.name.replace(".yml", "")))
                arenas.add(arena)
            }
        }
        Bukkit.getConsoleSender().sendMessage(Messages.messages["loading-arenas-end"]!!)


    }

    fun arenaNotExists(arenaName: String): Boolean {
        for (arena in arenas) {
            if (arena.name == arenaName) {
                return false
            }
        }
        return true
    }

    fun toArenaString(): String {
        var string = ""
        for (arena in arenas) {
            string += "§6" + arena.name + "§7, "
        }
        return string.substring(0, string.length - 2)
    }

    fun getOrCreateArena(name: String): Arena {
        for (arena in arenas) {
            if (arena.name == name) {
                return arena
            }
        }
        val arena = Arena(name)
        arenas.add(arena)
        return arena
    }

    fun isInSameArena(player: Player, onlinePlayers: Player): Boolean {
        for (arena in arenas) {
            if (arena.players.contains(player) && arena.players.contains(onlinePlayers)) {
                return true
            }
        }
        return false


    }
}
