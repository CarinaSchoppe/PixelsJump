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

object BlockGenerator {

    val playerBlockJumps = mutableMapOf<Player, Block>()
    val playerBlockOld = mutableMapOf<Player, Block>()
    val playerCheckpoints = mutableMapOf<Player, Location>()
    val checkPointMaterial = Material.DIAMOND_BLOCK
    val endPointFinish = Material.GOLD_BLOCK

    enum class blocks(val material: Material) {
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
        val type = blocks.values().random()
        var length = Random().nextInt(5)
        val height = Random().nextInt(2)
        val x = Random().nextInt(3) - 1
        val z = Random().nextInt(3) - 1
        if (height == 1 && length == 4) {
            length = 3
        }
        val newLocation = player.location.add((length * x).toDouble(), height.toDouble(), (length * z).toDouble())
        val block = player.world.getBlockAt(newLocation)
        block.type = type.material
        playerBlockJumps[player] = block
        if (playerBlockOld[player] != null)
            player.world.getBlockAt(playerBlockOld[player]!!.location).type = Material.AIR
        playerBlockOld[player] = block
    }
}
