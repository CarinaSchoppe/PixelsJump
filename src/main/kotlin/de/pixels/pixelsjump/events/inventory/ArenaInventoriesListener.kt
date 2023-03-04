/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 17:58 by Carina The Latest changes made by Carina on 28.03.22, 17:58.
 *  All contents of "ArenaInventories.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.events.inventory

import de.carina.pixelsjump.util.inventory.InventoryNames
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class ArenaInventoriesListener : Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {

        if (LegacyComponentSerializer.legacySection().serialize(event.view.title()) != InventoryNames.ARENAS.text)
            return
        event.isCancelled = true
        if (!event.whoClicked.hasPermission("pixelsjump.arenas"))
            return
        val item = event.currentItem ?: return
        val player = event.whoClicked as org.bukkit.entity.Player

        val arenaName = PlainTextComponentSerializer.plainText().serialize(item.itemMeta.displayName()!!)
        player.closeInventory()
        player.performCommand("pixelsjump join $arenaName")
    }
}
