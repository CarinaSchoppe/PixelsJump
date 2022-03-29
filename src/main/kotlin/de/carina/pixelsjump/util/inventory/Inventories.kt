/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 16:48 by Carina The Latest changes made by Carina on 28.03.22, 16:48.
 *  All contents of "Inventories.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.util.inventory

import de.carina.pixelsjump.util.arena.ArenaHelper
import org.bukkit.inventory.Inventory

object Inventories {
    fun starterInventory(): Inventory {
        return InventoryBuilder(InventoryNames.ARENA_BUILDER.text, 9).addItem(Items.backLocationItem(), 6).addItem(Items.startLocationItem(), 0).addItem(Items.checkPointItem(), 4).addItem(Items.endLocationItem(), 2).addItem(Items.finishArenaBuildItem(), 8).fillInventory(Items.paneFillerItem()).buildInventory()
    }

    fun arenaInventory(): Inventory {
        var builder = InventoryBuilder(InventoryNames.ARENAS.text, (ArenaHelper.arenas.size / 9 + 1) * 9)
        for ((index, arena) in ArenaHelper.arenas.withIndex()) {
            builder.addItem(Items.arenaItem("${ItemNames.ARENA_FRAME_COLOR.text}${arena.name}"), index)
        }
        builder.fillInventory(Items.paneFillerItem())
        return builder.buildInventory()
    }
}
