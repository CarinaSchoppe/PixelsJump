/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 14:04 by Carina The Latest changes made by Carina on 28.03.22, 14:02.
 *  All contents of "PlayerStats.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.util.stats

import java.util.*

data class PlayerStats(var uuid: UUID, var maxBlocks: Int, var games: Int, var points: Int, var fails: Int, var wins: Int) {
    fun addStats(values: Array<Int>) {
        this.maxBlocks += values[0]
        this.games += values[1]
        this.points += values[2]
        this.fails += values[3]
        this.wins += values[4]
    }
}
