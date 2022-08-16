/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 29.03.22, 18:49 by Carina The Latest changes made by Carina on 29.03.22, 18:49.
 *  All contents of "Checkpoint.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.events.extra

import de.carina.pixelsjump.util.inventory.Items
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class CheckpointItem : Listener {

    /**
     * player clicks on the checkpoint item
     */
    @EventHandler
    fun onCheckpoint(event: PlayerInteractEvent) {
        if (!event.player.hasPermission("pixelsjump.checkpoint"))
            return
        if (event.player.inventory.itemInMainHand == Items.toCheckPointItem) {
            event.isCancelled = true
            event.player.performCommand("pixelsjump checkpoint")
        } else if (event.player.inventory.itemInMainHand == Items.leaveItem) {
            event.isCancelled = true
            event.player.performCommand("pixelsjump leave")
        }
    }

}
