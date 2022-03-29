/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 29.03.22, 11:11 by Carina The Latest changes made by Carina on 29.03.22, 11:11.
 *  All contents of "JoinArena.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.commands.arena

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.BlockGenerator
import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.stats.Statistics
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class JoinArena(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {

    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 2, "join", "pixelsjump.join")) return
        if (!PixelsJump.utility.checkForArena(args[1], sender as Player)) return
        val arena = ArenaHelper.getArena(args[1])
        if (ArenaHelper.playersInArenas.contains(sender)) {
            sender.sendMessage(PixelsJump.utility.messageConverter("arena-allready").replace("%arena%", ArenaHelper.arenas.find { it.players.contains(sender) }!!.name))
            return
        }
        arena.players.add(sender)
        ArenaHelper.playersInArenas.add(sender)
        sender.sendMessage(PixelsJump.utility.messageConverter("arena-join").replace("%arena%", args[1]))
        Bukkit.getOnlinePlayers().forEach {
            if (it != sender) {
                it.hidePlayer(PixelsJump.instance, sender)
            }
        }
        sender.playerListName(LegacyComponentSerializer.legacySection().deserialize(PixelsJump.prefix + "§7" + sender.name))
        Statistics.joinArena(sender)
        sender.teleport(arena.locations[0]!! as Location)
        BlockGenerator.playerJumpBlocks[sender] = mutableListOf()
        PixelsJump.utility.playerInventory[sender] = sender.inventory
        sender.gameMode = GameMode.SURVIVAL
        sender.inventory.clear()
        BlockGenerator.generateBlock(sender)
        BlockGenerator.playerJumps[sender] = 0
    }
}
