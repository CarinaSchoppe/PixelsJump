/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 29.03.22, 18:13 by Carina The Latest changes made by Carina on 29.03.22, 18:13.
 *  All contents of "Blocks.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.events.extra

import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.files.Configuration
import de.carina.pixelsjump.util.files.Messages
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class Blocks : Listener {

    @EventHandler
    fun onBlockBreak(event: org.bukkit.event.block.BlockBreakEvent) {
        if (!ArenaHelper.playersInArenas.contains(event.player))
            return
        if (!Configuration.arenaBreak)
            return
        event.isCancelled = true
        event.player.sendMessage(Messages.messages["block-break-cancelled"]!!.replace("%block%", event.block.type.name).replace("%arena%", ArenaHelper.arenas.find { it.players.contains(event.player) }!!.name))

    }



    @EventHandler
    fun onBlockPlace(event: org.bukkit.event.block.BlockPlaceEvent) {
        if (!ArenaHelper.playersInArenas.contains(event.player))
            return
        if (!Configuration.arenaPlace)
            return
        event.isCancelled = true
        event.player.sendMessage(Messages.messages["block-place-cancelled"]!!.replace("%block%", event.block.type.name).replace("%block%", event.block.type.name).replace("%arena%", ArenaHelper.arenas.find { it.players.contains(event.player) }!!.name))

    }


}
