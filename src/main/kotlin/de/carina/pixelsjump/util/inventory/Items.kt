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

    val startLocationItem: ItemStack = ItemBuilder(Material.OAK_DOOR, 1).addItemName(ItemNames.ADD_START_LOCATION.text).addItemLore(listOf("§aClick to add the start location to the arena")).build()


    val singleJumpNRunItem = ItemBuilder(Material.RABBIT_FOOT, 1).addItemName(ItemNames.SINGLE_JUMP_N_RUN.text).addItemLore(listOf("§aClick to set the arena to a single jump n run")).build()


    val toCheckPointItem: ItemStack =
        ItemBuilder(Material.BARRIER, 1).addItemName(ItemNames.CHECKPOINT.text).addItemLore(listOf("§aClick to go back to your latest checkpoint")).build()


    val arenaDamageItem =
        ItemBuilder(Material.DIAMOND_SWORD, 1).addItemName(ItemNames.ARENA_DAMAGE.text).addItemLore(listOf("§aClick to add activate the damage for the arena")).build()


    val checkPointItem = ItemBuilder(Material.IRON_HOE, 1).addItemName(ItemNames.ADD_CHECKPOINT_LOCATION.text).addItemLore(listOf("§aClick to add a new Checkpoint to the Arena")).build()


    val backLocationItem = ItemBuilder(Material.END_CRYSTAL, 1).addItemName(ItemNames.ADD_BACK_LOCATION.text).addItemLore(listOf("§aClick to add the back location")).build()


    val paneFillerItem = ItemBuilder(Material.BLUE_STAINED_GLASS_PANE, 1).addItemName(ItemNames.GLASS_PANE_FILLER.text).addEnchantment(Enchantment.DURABILITY, 0).build()


    fun arenaItem(name: String): ItemStack {
        return ItemBuilder(Material.OAK_SIGN, 1).addItemName(name).addItemLore(listOf("§aClick to open the Arena")).build()
    }

    val finishArenaBuildItem = ItemBuilder(Material.DIAMOND, 1).addItemName(ItemNames.FINISH_ARENA.text).addItemLore(listOf("Click to finish the Arena")).build()

    val finishLocationItem = ItemBuilder(Material.ACACIA_BOAT, 1).addItemName(ItemNames.ADD_END_LOCATION.text).addItemLore(listOf("§aClick to add the finish location")).build()
}
