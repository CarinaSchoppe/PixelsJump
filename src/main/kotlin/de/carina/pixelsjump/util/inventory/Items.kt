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
        return ItemBuilder(Material.OAK_DOOR, 1).addItemName(ItemNames.ADD_LOCATION_START.text).addItemLore(listOf("§aClick to add a new Location to the Arena")).build()
    }

    fun endLocationItem(): ItemStack {
        return ItemBuilder(Material.GRASS_BLOCK, 1).addItemName(ItemNames.ADD_LOCATION_END.text).addItemLore(listOf("§aClick to add a new Location to the Arena")).build()

    }

    fun checkPointItem(): ItemStack {
        return ItemBuilder(Material.IRON_HOE, 1).addItemName(ItemNames.ADD_CHECKPOINT.text).addItemLore(listOf("§aClick to add a new Checkpoint to the Arena")).build()
    }

    fun backLocationItem(): ItemStack {
        return ItemBuilder(Material.END_CRYSTAL, 1).addItemName(ItemNames.ADD_BACK_ARENA.text).addItemLore(listOf("§aClick to add the back location")).build()
    }

    fun paneFillerItem(): ItemStack {
        return ItemBuilder(Material.BLUE_STAINED_GLASS_PANE, 1).addItemName(ItemNames.GLASS_PANE_FILLER.text).addEnchantment(Enchantment.DURABILITY, 0).build()
    }

    fun arenaItem(name: String): ItemStack {
        return ItemBuilder(Material.OAK_SIGN, 1).addItemName(name).addItemLore(listOf("§aClick to open the Arena")).build()
    }

    fun finishArenaBuildItem(): ItemStack {
        return ItemBuilder(Material.DIAMOND, 1).addItemName(ItemNames.FINISH_ARENA.text).addItemLore(listOf("Click to finish the Arena")).build()

    }
}
