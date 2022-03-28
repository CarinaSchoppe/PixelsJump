/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 16:22 by Carina The Latest changes made by Carina on 28.03.22, 16:22.
 *  All contents of "InventoryStart.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.commands

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.inventory.Inventories
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class InventoryStart(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {

    fun execute() {
        if (PixelsJump.utility.preCommandStuff(sender, command, args, 2, "gui", "pixelsjump.gui"))
            return
        val player = sender as org.bukkit.entity.Player
        PixelsJump.utility.arenaPlayerNames[player] = args[1]

        player.openInventory(Inventories.starterInventory())

    }
}
