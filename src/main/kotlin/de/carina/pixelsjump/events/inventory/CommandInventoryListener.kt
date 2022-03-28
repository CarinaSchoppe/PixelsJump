/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 16:15 by Carina The Latest changes made by Carina on 28.03.22, 16:15.
 *  All contents of "CommandInventory.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.events.inventory

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.inventory.Items
import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class CommandInventoryListener : Listener {

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.view.title() != Component.text("Arena Builder"))
            return
        event.isCancelled = true
        val item = event.currentItem ?: return
        val player = event.whoClicked as org.bukkit.entity.Player
        if (PixelsJump.utility.arenaPlayerNames[player] == null) {
            player.sendMessage(PixelsJump.utility.messageConverter("no-arena-name"))
            return
        }
        when (item) {
            Items.startLocationItem() -> {
                if (!player.hasPermission("pixelsjump.addLocation.start")) {
                    player.sendMessage(PixelsJump.utility.messageConverter("no-permission"))
                    return
                }
                player.performCommand("pixelsjump add ${PixelsJump.utility.arenaPlayerNames[player]} start")
            }
            Items.endLocationItem() -> {
                println("test")
                if (!player.hasPermission("pixelsjump.addLocation.end")) {
                    player.sendMessage(PixelsJump.utility.messageConverter("no-permission"))
                    return
                }
                player.performCommand("pixelsjump add ${PixelsJump.utility.arenaPlayerNames[player]} end")

            }
            Items.finishArenaBuildItem() -> {
                if (!player.hasPermission("pixelsjump.addLocation.finish")) {
                    player.sendMessage(PixelsJump.utility.messageConverter("no-permission"))
                    return
                }
                player.performCommand("pixelsjump finish ${PixelsJump.utility.arenaPlayerNames[player]}")
                player.closeInventory()
            }
        }


    }

}
