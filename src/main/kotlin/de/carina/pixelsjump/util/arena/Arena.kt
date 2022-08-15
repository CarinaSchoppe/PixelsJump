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

import com.google.gson.GsonBuilder
import org.bukkit.Location
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import java.io.File


class Arena(val name: String) {
    val checkPoints: MutableList<Location> = mutableListOf()
    lateinit var startLocation: Location
    lateinit var finishLocation: Location
    lateinit var backLocation: Location
    val players = mutableSetOf<Player>()

    @Transient
    private val file: File = File("plugins/PixelsJumpRemastered/arenas/$name.json")
    var single: Boolean = false
    var damage: Boolean = false

    fun saveArena() {
        val gson = GsonBuilder().setPrettyPrinting().create()
        file.writeText(gson.toJson(this))
    }


    fun addCheckpointLocation(location: Location) {
        checkPoints.add(location.block.getRelative(BlockFace.DOWN).location.toCenterLocation())
    }


}
