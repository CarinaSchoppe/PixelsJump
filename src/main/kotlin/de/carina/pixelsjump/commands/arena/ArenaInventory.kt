/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 17:56 by Carina The Latest changes made by Carina on 28.03.22, 17:56.
 *  All contents of "ArenaInventory.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.commands.arena

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.inventory.Inventories
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class ArenaInventory(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {


    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 1, "arenas", "pixelsjump.arenas")) return

        val player = sender as org.bukkit.entity.Player
        player.openInventory(Inventories.arenaInventory())
    }
}
