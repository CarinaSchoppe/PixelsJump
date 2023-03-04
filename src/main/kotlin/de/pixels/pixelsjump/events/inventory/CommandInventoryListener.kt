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
import de.carina.pixelsjump.util.files.Messages
import de.carina.pixelsjump.util.inventory.InventoryNames
import de.carina.pixelsjump.util.inventory.Items
import de.carina.pixelsjump.util.misc.ConstantStrings
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class CommandInventoryListener : Listener {

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (LegacyComponentSerializer.legacySection().serialize(event.view.title()) != InventoryNames.ARENA_BUILDER.text)
            return
        event.isCancelled = true
        if (!event.whoClicked.hasPermission("pixelsjump.setup"))
            return
        val item = event.currentItem ?: return
        val player = event.whoClicked as Player
        if (PixelsJump.utility.arenaPlayerNames[player] == null) {
            player.sendMessage(Messages.messages["no-arena-name"]!!)
            return
        }

        //get current item in the arena_builder inventory and perform the action related
        when (item) {
            Items.startLocationItem ->
                startLocationItem(player, event)


            Items.singleJumpNRunItem ->
                singleJumpItem(player)


            Items.finishArenaBuildItem ->
                finishArenaBuildItem(player, event)


            Items.checkPointItem ->
                checkPointItem(player, event)


            Items.backLocationItem ->
                backLocationItem(player, event)


            Items.arenaDamageItem ->
                arenaDamageItem(player)


            Items.finishLocationItem ->
                finishLocationItem(player, event)

        }


    }

    private fun finishLocationItem(player: Player, event: InventoryClickEvent) {
        if (!player.hasPermission("pixelsjump.addLocation.finish")) {
            player.sendMessage(Messages.messages[ConstantStrings.NO_PERMISSION]!!)
            return
        }
        if (event.isLeftClick) {
            player.playSound(player, Sound.BLOCK_LAVA_POP, 1f, 1f)
            player.performCommand("pixelsjump add ${PixelsJump.utility.arenaPlayerNames[player]} finish")
        } else if (event.isRightClick) {
            player.playSound(player, Sound.ITEM_GOAT_HORN_SOUND_0, 1f, 1f)
            player.performCommand("pixelsjump remove ${PixelsJump.utility.arenaPlayerNames[player]} finish")
        }
    }

    private fun arenaDamageItem(player: Player) {
        if (!player.hasPermission("pixelsjump.arena.damage")) {
            player.sendMessage(Messages.messages[ConstantStrings.NO_PERMISSION]!!)
            return
        }
        player.performCommand("pixelsjump damage ${PixelsJump.utility.arenaPlayerNames[player]}")
        player.playSound(player, Sound.BLOCK_LAVA_POP, 1f, 1f)
    }

    private fun backLocationItem(player: Player, event: InventoryClickEvent) {
        if (!player.hasPermission("pixelsjump.addLocation.back")) {
            player.sendMessage(Messages.messages[ConstantStrings.NO_PERMISSION]!!)
            return
        }
        if (event.isLeftClick) {
            player.performCommand("pixelsjump add ${PixelsJump.utility.arenaPlayerNames[player]} back")
            player.playSound(player, Sound.BLOCK_LAVA_POP, 1f, 1f)

        } else if (event.isRightClick) {
            player.performCommand("pixelsjump remove ${PixelsJump.utility.arenaPlayerNames[player]} back")
            player.playSound(player, Sound.ITEM_GOAT_HORN_SOUND_0, 1f, 1f)


        }
    }

    private fun checkPointItem(player: Player, event: InventoryClickEvent) {
        if (!player.hasPermission("pixelsjump.addLocation.checkpoint")) {
            player.sendMessage(Messages.messages[ConstantStrings.NO_PERMISSION]!!)
            return
        }
        if (event.isLeftClick) {
            player.performCommand("pixelsjump add ${PixelsJump.utility.arenaPlayerNames[player]} checkpoint")
            player.playSound(player, Sound.BLOCK_LAVA_POP, 1f, 1f)


        } else if (event.isRightClick) {
            player.performCommand("pixelsjump remove ${PixelsJump.utility.arenaPlayerNames[player]} checkpoint")
            player.playSound(player, Sound.ITEM_GOAT_HORN_SOUND_0, 1f, 1f)

        }
    }

    private fun finishArenaBuildItem(player: Player, event: InventoryClickEvent) {
        if (!player.hasPermission("pixelsjump.addLocation.finish")) {
            player.sendMessage(Messages.messages[ConstantStrings.NO_PERMISSION]!!)
            return
        }
        if (event.isLeftClick) {
            player.performCommand("pixelsjump finish ${PixelsJump.utility.arenaPlayerNames[player]}")
            player.playSound(player, Sound.BLOCK_LAVA_POP, 1f, 1f)

        } else if (event.isRightClick) {
            player.performCommand("pixelsjump delete ${PixelsJump.utility.arenaPlayerNames[player]}")
            player.playSound(player, Sound.ITEM_GOAT_HORN_SOUND_0, 1f, 1f)

        }
        player.closeInventory()
    }

    private fun singleJumpItem(player: Player) {
        if (!player.hasPermission("pixelsjump.addLocation.single")) {
            player.sendMessage(Messages.messages[ConstantStrings.NO_PERMISSION]!!)
            return
        }
        player.playSound(player, Sound.BLOCK_LAVA_POP, 1f, 1f)

        player.performCommand("pixelsjump single ${PixelsJump.utility.arenaPlayerNames[player]}")
    }

    private fun startLocationItem(player: Player, event: InventoryClickEvent) {
        if (!player.hasPermission("pixelsjump.addLocation.start")) {
            player.sendMessage(Messages.messages[ConstantStrings.NO_PERMISSION]!!)
            return
        }
        if (event.isLeftClick) {
            player.performCommand("pixelsjump add ${PixelsJump.utility.arenaPlayerNames[player]} start")
            player.playSound(player, Sound.BLOCK_LAVA_POP, 1f, 1f)

        } else if (event.isRightClick) {
            player.performCommand("pixelsjump remove ${PixelsJump.utility.arenaPlayerNames[player]} start")
            player.playSound(player, Sound.ITEM_GOAT_HORN_SOUND_0, 1f, 1f)

        }
    }

}
