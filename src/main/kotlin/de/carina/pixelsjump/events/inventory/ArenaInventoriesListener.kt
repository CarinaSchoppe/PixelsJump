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

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.ArenaHelper
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class ArenaInventoriesListener : Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.view.title() != Component.text("Arenas"))
            return
        event.isCancelled = true
        val item = event.currentItem ?: return
        val arena = ArenaHelper.getArena(PlainTextComponentSerializer.plainText().serialize(item.displayName()))

        val player = event.whoClicked as org.bukkit.entity.Player
        arena.locations[0]?.let { player.teleport(it) }
        player.sendMessage(PixelsJump.utility.messageConverter("arena-teleport").replace("%arena%", arena.name))

    }
}
