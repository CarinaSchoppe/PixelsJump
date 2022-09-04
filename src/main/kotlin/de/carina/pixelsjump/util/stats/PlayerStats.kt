/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 14:01 by Carina The Latest changes made by Carina on 28.03.22, 14:01.
 *  All contents of "Statistics.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

@file:Suppress("SameReturnValue")

package de.carina.pixelsjump.util.stats

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import de.carina.pixelsjump.util.database.MySQL
import de.carina.pixelsjump.util.files.Configuration
import de.carina.pixelsjump.util.files.Messages
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.io.File
import java.util.*

object PlayerStats {
    data class PlayerStats(var playerName: String, var uuid: UUID, var games: Int = 0, var points: Int = 0, var fails: Int = 0, var wins: Int = 0)

    private const val path = "plugins/PixelsJumpRemastered/statistics.json"
    private var file: File = File(path)
    val statistics = mutableListOf<PlayerStats>()


    private fun loadPlayers() {
        if (Configuration.config["mysql"] as Boolean) {
            MySQL.loadPlayers()
        } else {
            if (!file.exists()) return
            val itemType = object : TypeToken<List<PlayerStats>>() {}.type
            val playerStats = Gson().fromJson<MutableList<PlayerStats>>(file.bufferedReader(), itemType)
            playerStats.forEach { player ->
                Bukkit.getConsoleSender().sendMessage(
                    Messages.messages["stats-loaded"]!!.replace("%player%", Bukkit.getOfflinePlayers().firstOrNull { it.uniqueId == player.uuid }?.name ?: "offline")
                )
                statistics.add(player)
            }
        }


    }

    fun loadStats() {
        Bukkit.getConsoleSender().sendMessage(Messages.messages["loading-statistics-start"]!!)
        file = File(path)
        loadPlayers()
        Bukkit.getConsoleSender().sendMessage(Messages.messages["loading-statistics-end"]!!)

    }


    fun addStats(player: Player) {
        val statsPlayer = getStatsPlayer(player.name)
        if (statsPlayer == null)
            statistics.add(PlayerStats(player.name, player.uniqueId, 0, 0, 0, 0))
        saveStatsFile()
    }

    private fun getStatsPlayer(name: String): PlayerStats? {
        return statistics.firstOrNull { it.playerName == name }
    }

    private fun saveStatsFile() {
        if (Configuration.config["mysql"] as Boolean) {
            MySQL.saveStats()
        } else {
            val gson = GsonBuilder().setPrettyPrinting().create()
            val json = gson.toJson(statistics)
            file.writeText(json)
        }
    }

    fun joinArena(player: Player) {
        val statsPlayer = getStatsPlayer(player.name) ?: return
        statsPlayer.games++
        saveStatsFile()
    }

    fun addFail(player: Player) {
        val playerStats = getStatsPlayer(player.name) ?: return
        playerStats.fails++
        saveStatsFile()
    }

    fun addPoints(player: Player, amount: Int) {
        val playerStats = getStatsPlayer(player.name) ?: return
        playerStats.points += amount
        saveStatsFile()
    }

    fun addWin(player: Player) {
        val playerStats = getStatsPlayer(player.name) ?: return
        playerStats.wins++
        playerStats.points += 5
        saveStatsFile()

    }
}
