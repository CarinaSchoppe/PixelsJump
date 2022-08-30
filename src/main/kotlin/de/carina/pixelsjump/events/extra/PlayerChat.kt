/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 29.03.22, 18:13 by Carina The Latest changes made by Carina on 29.03.22, 18:13.
 *  All contents of "Chatter.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.events.extra

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.arena.ArenaHelper
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class PlayerChat : Listener {

    @EventHandler
    fun onChat(event: AsyncChatEvent) {
        if (ArenaHelper.playersInArenas.contains(event.player)) {
            event.isCancelled = true
            val arena = ArenaHelper.arenas.find { it.players.contains(event.player) }!!
            if (!arena.chat) return
            arena.players.forEach {
                it.sendMessage("${PixelsJump.prefix} ${event.player.name} says: ${PlainTextComponentSerializer.plainText().serialize(event.message())}")
            }
        } else {
            event.isCancelled = true
            Bukkit.getOnlinePlayers().forEach {
                if (!ArenaHelper.playersInArenas.contains(it))
                    it.sendMessage("${PixelsJump.prefix} ${event.player.name} says: ${PlainTextComponentSerializer.plainText().serialize(event.message())}")

            }
        }
    }
}
