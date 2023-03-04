/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 16:06 by Carina The Latest changes made by Carina on 28.03.22, 16:06.
 *  All contents of "ItemBuilder.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.util.inventory

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.Material
import org.bukkit.inventory.meta.ItemMeta

class ItemBuilder(material: Material, amount: Int) {
    private val itemStack = org.bukkit.inventory.ItemStack(material, amount)
    private val itemMeta: ItemMeta = itemStack.itemMeta

    fun addItemName(name: String): ItemBuilder {
        itemMeta.displayName(
            LegacyComponentSerializer.legacySection().deserialize(name)
        )
        itemStack.itemMeta = itemMeta
        return this

    }


    private fun addItemLore(lore: String): ItemBuilder {
        itemMeta.lore()?.add(LegacyComponentSerializer.legacySection().deserialize(lore))
        itemStack.itemMeta = itemMeta
        return this
    }

    fun addItemLore(lore: List<String>): ItemBuilder {
        lore.forEach { addItemLore(it) }
        itemStack.itemMeta = itemMeta
        return this
    }

    fun addEnchantment(enchantment: org.bukkit.enchantments.Enchantment, level: Int): ItemBuilder {
        itemMeta.addEnchant(enchantment, level, true)
        itemStack.itemMeta = itemMeta
        return this
    }

    fun addItemFlags(flags: Array<org.bukkit.inventory.ItemFlag>): ItemBuilder {
        itemMeta.addItemFlags(*flags)
        itemStack.itemMeta = itemMeta
        return this
    }

    fun build(): org.bukkit.inventory.ItemStack {
        itemStack.itemMeta = itemMeta
        return itemStack
    }

}
