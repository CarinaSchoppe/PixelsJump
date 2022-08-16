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
import de.carina.pixelsjump.util.arena.Arena
import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.files.Messages
import de.carina.pixelsjump.util.inventory.Items
import de.carina.pixelsjump.util.stats.PlayerStats
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class JoinArena(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {

    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 2, "join", "pixelsjump.join")) return
        if (!PixelsJump.utility.checkForArena(args[1], sender as Player)) return
        val arena = ArenaHelper.getOrCreateArena(args[1])
        if (ArenaHelper.playersInArenas.contains(sender)) {
            sender.sendMessage(Messages.messages["arena-allready"]!!.replace("%arena%", ArenaHelper.arenas.find { it.players.contains(sender) }!!.name))
            return
        }

        addPlayerToArena(sender, arena)
    }

    private fun addPlayerToArena(player: Player, arena: Arena) {

        player.sendMessage(Messages.messages["arena-join"]!!.replace("%arena%", args[1]))

        player.playerListName(LegacyComponentSerializer.legacySection().deserialize(PixelsJump.prefix + "§7" + sender.name))
        PlayerStats.joinArena(player)
        player.teleport(arena.startLocation!!.toLocation())
        BlockGenerator.playerJumpBlocks[player] = mutableListOf()
        PixelsJump.utility.playerInventory[player] = player.inventory
        player.gameMode = GameMode.SURVIVAL
        BlockGenerator.playerCheckpoints[player] = arena.startLocation!!
        player.inventory.clear()
        if (arena.single)
            player.inventory.setItem(8, Items.leaveItem)
        else {
            player.inventory.setItem(8, Items.toCheckPointItem)
            player.inventory.setItem(5, Items.leaveItem)
        }
        if (arena.single)
            BlockGenerator.generateBlock(player)
        BlockGenerator.playerJumps[player] = 0
        arena.players.add(player)
        ArenaHelper.playersInArenas.add(player)
        BlockGenerator.playerAFK[player] = Pair(Bukkit.getScheduler().runTaskTimer(PixelsJump.instance, Runnable {
            if (BlockGenerator.playerAFK[player]?.second == true)
                player.performCommand("pixelsjump leave")
            else
                BlockGenerator.playerAFK[player] = Pair(BlockGenerator.playerAFK[player]!!.first, true)
        }, 250, 250), true)
        player.level = 0
       PixelsJump.utility.hideAllPlayersNotInSameArena(player, arena)
        player.playSound(player.location, Sound.ENTITY_PLAYER_LEVELUP, 1.toFloat(), 1.toFloat())

    }
}
