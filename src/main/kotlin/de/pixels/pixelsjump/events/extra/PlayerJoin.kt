/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 29.03.22, 15:59 by Carina The Latest changes made by Carina on 29.03.22, 15:59.
 *  All contents of "PlayerJoin.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.events.extra

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.misc.PlayerStatsHandler
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoin : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        if (PlayerStatsHandler.statistics.contains(event.player.uniqueId))
            return

        PlayerStatsHandler.statistics[event.player.uniqueId] = PlayerStatsHandler.PlayerStats(event.player.name, event.player.uniqueId)
        PlayerStatsHandler.addStats(event.player)


        PixelsJump.utility.hideAllPlayersNotInSameArena(event.player, null)
        PixelsJump.utility.showAllPlayersInSameArena(event.player, null)
    }
}
