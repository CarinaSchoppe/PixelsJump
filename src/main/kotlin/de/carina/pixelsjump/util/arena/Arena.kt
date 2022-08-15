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
import de.carina.pixelsjump.util.json.CustomLocation
import org.bukkit.Location
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import java.io.File


class Arena(val name: String) {


    @Transient
    var players = mutableSetOf<Player>()


    val checkPoints: MutableList<CustomLocation> = mutableListOf()
    var startLocation: CustomLocation? = null
    var finishLocation: CustomLocation? = null
    var backLocation: CustomLocation? = null

    @Transient
    private lateinit var file: File
    var single: Boolean = false
    var damage: Boolean = false

    fun saveArena() {
        file = File("plugins/PixelsJumpRemastered/arenas/$name.json")
        val gson = GsonBuilder().setPrettyPrinting().create()
        var json = gson.toJson(this)
        file.writeText(json)
    }


    fun addCheckpointLocation(location: Location) {
        checkPoints.add(CustomLocation(location.block.getRelative(BlockFace.DOWN).location.toCenterLocation()))
    }
}
