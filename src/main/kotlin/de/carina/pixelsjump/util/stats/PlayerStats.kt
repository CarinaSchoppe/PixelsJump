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

data class PlayerStats(var uuid: UUID, var games: Int = 0, var points: Int = 0, var fails: Int = 0, var wins: Int = 0) {
    fun addStats(values: Array<Int>) {
        this.games += values[1]
        this.points += values[2]
        this.fails += values[3]
        this.wins += values[4]
    }
}
