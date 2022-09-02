/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 29.03.22, 11:36 by Carina The Latest changes made by Carina on 29.03.22, 11:36.
 *  All contents of "LeaveArena.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.commands.arena

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.BlockGenerator
import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.files.Messages
import net.kyori.adventure.text.Component
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot

class LeaveArena(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {


    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 1, "leave", "pixelsjump.leave")) return
        val player = sender as Player
        if (!ArenaHelper.playersInArenas.contains(player)) {
            player.sendMessage(Messages.messages["no-jump"]!!)
            return
        }


        //remove the player from everywhere
        player.playerListName(Component.text(player.name))
        ArenaHelper.playersInArenas.remove(player)
        BlockGenerator.playerCheckpoints.remove(player)
        BlockGenerator.playerBlock.remove(player)
        BlockGenerator.playerJumpBlocks[player]!!.forEach {
            it.type = Material.AIR
        }
        BlockGenerator.playerAFK[player]!!.first.cancel()
        BlockGenerator.playerAFK.remove(player)
        player.level = 0
        BlockGenerator.playerJumpBlocks.remove(player)
        val arena = ArenaHelper.arenas.find { it.players.contains(player) }
        player.teleport(arena!!.backLocation!!.toLocation())
        arena.players.remove(player)
        //remove player scoreboard
        player.scoreboard.clearSlot(DisplaySlot.SIDEBAR)
        player.playSound(player, Sound.ENTITY_GHAST_SCREAM, 1f, 1f)
        player.inventory.clear()
        PixelsJump.utility.showAllPlayersInSameArena(player, null)
        player.gameMode = GameMode.SURVIVAL
        player.sendMessage(Messages.messages["arena-leave"]!!.replace("%arena%", arena.name))
    }
}
