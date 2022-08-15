/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 29.03.22, 16:32 by Carina The Latest changes made by Carina on 29.03.22, 16:32.
 *  All contents of "PlayerDamage.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.events.extra

import de.carina.pixelsjump.util.arena.ArenaHelper
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class PlayerDamage : Listener {
    @EventHandler
    fun onPlayerDamage(event: EntityDamageEvent) {
        if (event.entity !is Player)
            return
        if (!ArenaHelper.playersInArenas.contains(event.entity as Player))
            return

        ArenaHelper.arenas.forEach {
            if (it.players.contains(event.entity as Player) && it.damage == false) {
                event.isCancelled = true
                return
            }
        }
    }
}
