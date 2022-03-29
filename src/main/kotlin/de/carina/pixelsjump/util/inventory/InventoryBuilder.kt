/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 16:14 by Carina The Latest changes made by Carina on 28.03.22, 16:08.
 *  All contents of "InventoryBuilder.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.util.inventory

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack


class InventoryBuilder(val name: String, size: Int) {

    private var inventory: Inventory = org.bukkit.Bukkit.createInventory(null, size, LegacyComponentSerializer.legacySection().deserialize(name))
    fun buildInventory(): Inventory {
        return inventory
    }

    fun addItem(item: ItemStack, slot: Int): InventoryBuilder {
        inventory.setItem(slot, item)
        return this
    }


    fun fillInventory(item: ItemStack): InventoryBuilder {
        for (i in 0 until inventory.size) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, item)
            }
        }
        return this
    }

}
