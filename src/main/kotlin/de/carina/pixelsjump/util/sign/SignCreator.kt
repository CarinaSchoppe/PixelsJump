/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 30.03.22, 10:58 by Carina The Latest changes made by Carina on 30.03.22, 10:58.
 *  All contents of "SignCreator.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.util.sign

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.arena.ArenaHelper
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.block.Sign
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.block.SignChangeEvent
import org.bukkit.event.player.PlayerInteractEvent

class SignCreator : Listener {

    @EventHandler
    fun onSignCreate(event: SignChangeEvent) {
        if (!event.player.hasPermission("pixelsjump.signcreate"))
            return
        if (event.line(0)?.let { PlainTextComponentSerializer.plainText().serialize(it) } != "[PixelsJump]")

            return

        val arenaName = PlainTextComponentSerializer.plainText().serialize(event.line(1)!!)
        if (!ArenaHelper.arenaNotExists(arenaName))
            return

        event.line(0, LegacyComponentSerializer.legacySection().deserialize(PixelsJump.prefix))
        event.line(1, LegacyComponentSerializer.legacySection().deserialize("§6$arenaName"))
        event.line(2, Component.text(""))
        event.line(3, Component.text(""))
    }

    @EventHandler
    fun onSignInteract(event: PlayerInteractEvent) {
        if (event.action != Action.RIGHT_CLICK_BLOCK)
            return
        if (!event.clickedBlock?.type.toString().contains("SIGN"))
            return
        val sign = event.clickedBlock?.state as Sign
        if (sign.line(0) != LegacyComponentSerializer.legacySection().deserialize(PixelsJump.prefix))
            return
        if (!event.player.hasPermission("pixelsjump.signuse"))
            return
        println(LegacyComponentSerializer.legacySection().serialize(sign.line(1)).substring(2))
        event.player.performCommand("pixelsjump join ${LegacyComponentSerializer.legacySection().serialize(sign.line(1)).substring(2)}")
    }
}
