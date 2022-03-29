/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 12:52 by Carina The Latest changes made by Carina on 28.03.22, 12:52.
 *  All contents of "BlockGenerator.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.util

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import java.util.*
import kotlin.math.abs

object BlockGenerator {

    val playerBlock = mutableMapOf<Player, Block>()
    val playerCheckpoints = mutableMapOf<Player, Location>()
    val checkPointMaterial = Material.DIAMOND_BLOCK
    val endPointFinish = Material.GOLD_BLOCK
    val playerJumps = mutableMapOf<Player, Int>()
    val playerJumpBlocks = mutableMapOf<Player, MutableList<Block>>()

    enum class Blocks(val material: Material) {
        RED(Material.RED_CONCRETE),
        GREEN(Material.GREEN_CONCRETE),
        BLUE(Material.BLUE_CONCRETE),
        YELLOW(Material.YELLOW_CONCRETE),
        PURPLE(Material.PURPLE_CONCRETE),
        WHITE(Material.WHITE_CONCRETE),
        BLACK(Material.BLACK_CONCRETE),
        ORANGE(Material.ORANGE_CONCRETE),
        LIGHT_BLUE(Material.LIGHT_BLUE_CONCRETE),
        PINK(Material.PINK_CONCRETE),
        LIME(Material.LIME_CONCRETE),
        CYAN(Material.CYAN_CONCRETE),
        MAGENTA(Material.MAGENTA_CONCRETE),
        GRAY(Material.GRAY_CONCRETE),
        LIGHT_GRAY(Material.LIGHT_GRAY_CONCRETE),
    }

    fun generateBlock(player: Player) {
        playerJumps[player] = (playerJumps[player] ?: 0) + 1
        val type = Blocks.values().random()
        var length = Random().nextInt(4) + 1
        val height = Random().nextInt(1)
        var x = Random().nextInt(3) - 1
        var z = Random().nextInt(3) - 1
        if (height == 1 && length == 4) {
            length = 2
        }
        if (abs(x) == 1 && abs(z) == 1) {
            length = 2
        } else if (x == 0 && z == 0) {
            x = 1
            z = -1
        }
        val newLocation = player.location.add((length * x).toDouble(), height.toDouble(), (length * z).toDouble())
        val block = player.world.getBlockAt(newLocation)
        playerJumpBlocks[player]!!.add(block)
        block.type = type.material
        playerBlock[player] = block
        if (playerJumps[player] ?: 0 >= 2) {
            player.world.getBlockAt(playerJumpBlocks[player]!![0].location).type = Material.AIR
            playerJumpBlocks[player]!!.removeAt(0)
        }
    }
}
