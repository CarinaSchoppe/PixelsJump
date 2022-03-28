/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 16:43 by Carina The Latest changes made by Carina on 28.03.22, 16:43.
 *  All contents of "Items.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.util.inventory

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

object Items {

    fun startLocationItem(): ItemStack {
        return ItemBuilder(Material.OAK_DOOR, 1).addItemName("Add Location Start").addItemLore(listOf("Click to add a new Location to the Arena")).build()
    }

    fun endLocationItem(): ItemStack {
        return ItemBuilder(Material.GRASS_BLOCK, 1).addItemName("Add Location End").addItemLore(listOf("Click to add a new Location to the Arena")).build()

    }

    fun paneFillerItem(): ItemStack {
        return ItemBuilder(Material.BLUE_STAINED_GLASS_PANE, 1).addItemName("").addEnchantment(Enchantment.DURABILITY, 0).build()

    }

    fun arenaItem(name: String): ItemStack {
        return ItemBuilder(Material.OAK_SIGN, 1).addItemName(name).addItemLore(listOf("Click to open the Arena")).build()
    }

    fun finishArenaBuildItem(): ItemStack {
        return ItemBuilder(Material.DIAMOND, 1).addItemName("Finish Arena").addItemLore(listOf("Click to finish the Arena")).build()

    }
}
