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

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.Inventory


class InventoryBuilder(name: String, size: Int) {
    companion object {
        fun starterInventory(): Inventory {
            val addLocation = ItemBuilder(Material.OAK_DOOR, 1).addItemName("§aAdd Location").addItemLore(listOf("§7Click to add a new Location to the Arena")).build()
            val finishArena = ItemBuilder(Material.DIAMOND, 1).addItemName("§aFinish Arena").addItemLore(listOf("§7Click to finish the Arena")).build()
            val pane = ItemBuilder(Material.BLUE_STAINED_GLASS_PANE, 1).addItemName("").addEnchantment(Enchantment.DURABILITY, 0).build()
            return InventoryBuilder("§aArena Builder", 9).addItem(addLocation, 1).addItem(finishArena, 8).fillInventory(pane).buildInventory()
        }
    }

    val inventory: Inventory = org.bukkit.Bukkit.createInventory(null, size, Component.text(name))
    fun buildInventory(): Inventory {
        return inventory
    }

    fun addItem(item: org.bukkit.inventory.ItemStack, slot: Int): InventoryBuilder {
        inventory.setItem(slot, item)
        return this
    }


    fun fillInventory(item: org.bukkit.inventory.ItemStack): InventoryBuilder {
        for (i in 0 until inventory.size) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, item)
            }
        }
        return this
    }

}
