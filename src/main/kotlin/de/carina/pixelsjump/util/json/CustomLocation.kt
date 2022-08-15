/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 8/15/22, 4:06 PM by Carina The Latest changes made by Carina on 8/15/22, 4:06 PM.
 *  All contents of "CustomLocation.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.util.json

import org.bukkit.Bukkit
import org.bukkit.Location

data class CustomLocation(var world: String, var x: Double, var y: Double, var z: Double, var yaw: Float, var pitch: Float) {
    constructor(location: Location) : this(location.world.name, location.x, location.y, location.z, location.yaw, location.pitch)


    fun toLocation(): Location {
        val location = Location(Bukkit.getWorld(world), x, y, z, yaw, pitch)
        return location
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CustomLocation) return false

        if (world != other.world) return false
        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false

        return true
    }


}
