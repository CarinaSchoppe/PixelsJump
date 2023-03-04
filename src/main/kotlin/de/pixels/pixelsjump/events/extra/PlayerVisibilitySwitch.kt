/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 8/16/22, 5:09 PM by Carina The Latest changes made by Carina on 8/16/22, 5:09 PM.
 *  All contents of "PlayerVisibilitySwitch.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.events.extra

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.files.Messages
import de.carina.pixelsjump.util.inventory.Items
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent

class PlayerVisibilitySwitch : Listener {


    @EventHandler
    fun playerInventoryClick(event: InventoryClickEvent) {
        if (!ArenaHelper.playerArena.containsKey(event.whoClicked))
            return

        event.isCancelled = true

    }

    @EventHandler
    fun onPlayerVisibilitySwitchChange(event: PlayerInteractEvent) {
        if (!ArenaHelper.playerArena.containsKey(event.player))
            return

        event.isCancelled = true
        if (event.item == null)
            return
        if (event.item!!.itemMeta == null)
            return

        if (event.item!!.itemMeta != Items.visibilitySwitchItem.itemMeta)
            return

        if (!PixelsJump.utility.playerVisibilitySwitchMap.containsKey(event.player))
            PixelsJump.utility.playerVisibilitySwitchMap[event.player] = true
        event.player.playSound(event.player, Sound.BLOCK_LAVA_POP, 1f, 1f)

        val arena = ArenaHelper.playerArena[event.player] ?: return

        if (PixelsJump.utility.playerVisibilitySwitchMap[event.player]!!) {
            Bukkit.getOnlinePlayers().forEach {
                event.player.hidePlayer(PixelsJump.instance, it)
            }
            event.player.sendMessage(Messages.messages["visibility-disabled"]!!.replace("%arena%", arena.name))
        } else {
            for (onlinePlayer in Bukkit.getOnlinePlayers()) {
                if (arena.players.contains(event.player) && arena.players.contains(onlinePlayer)) {
                    event.player.showPlayer(PixelsJump.instance, onlinePlayer)
                }
            }

            event.player.sendMessage(Messages.messages["visibility-enabled"]!!.replace("%arena%", arena.name))


        }
        PixelsJump.utility.playerVisibilitySwitchMap[event.player] = PixelsJump.utility.playerVisibilitySwitchMap[event.player]!!.not()

    }
}
