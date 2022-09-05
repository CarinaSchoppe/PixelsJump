/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 12:58 by Carina The Latest changes made by Carina on 28.03.22, 12:58.
 *  All contents of "Utility.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.util

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.arena.Arena
import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.files.Messages
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.block.data.BlockData
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Utility {


    val playerVisibilitySwitchMap = mutableMapOf<Player, Boolean>()

    /**
     * @param sender the sender of the command
     * @param command: the prefix command
     * @param argsLength: total amount of arguments including command self
     * @param permission: permission to perform the command
     * @param commandEnter the command after the prefix
     *
     * @return true if the command can be excecuted if not send proper messages
     */
    fun preCommandStuff(sender: CommandSender, command: Command, args: Array<out String>, argsLength: Int, commandEnter: String?, permission: String): Boolean {
        if (!command.name.equals("pixelsjump", true)) {
            return false
        }

        if (args.isEmpty()) {
            sender.sendMessage(Messages.messages["default-command"]!!)
            return false
        }

        if (sender !is Player) {
            sender.sendMessage(Messages.messages["no-player"]!!)
            return false
        }

        if (args.size < argsLength) {
            sender.sendMessage(Messages.messages["wrong-args"]!!)
            return false
        }


        if (commandEnter != null && !args[0].equals(commandEnter, true)) {
            return false
        }


        if (!sender.hasPermission(permission)) {
            sender.sendMessage(Messages.messages["no-permission"]!!)
            return false
        }
        return true
    }


    fun makeJumpBlockInvisible(location: Location, block: BlockData, player: Player) {
        Bukkit.getOnlinePlayers().forEach {
            if (it != player) {
                it.sendBlockChange(location, block)
            }
        }
    }

    /**
     * @param player Player to check
     * @param arena Arena to check
     * if arena = null: hide all players
     */
    fun hideAllPlayersNotInSameArena(player: Player, arena: Arena?) {
        for (onlinePlayer in Bukkit.getOnlinePlayers()) {
            if (arena != null && arena.players.contains(player) && arena.players.contains(onlinePlayer) || player == onlinePlayer)
                continue
            onlinePlayer.hidePlayer(PixelsJump.instance, player)
            player.hidePlayer(PixelsJump.instance, onlinePlayer)
        }
    }

    /**
     * @param player Player to show to
     * @param arena Arena to show to
     * if arena is null shows all players that are not in an arena
     * if arena != null: shows all players in the same arena
     */
    fun showAllPlayersInSameArena(player: Player, arena: Arena?) {
        for (onlinePlayer in Bukkit.getOnlinePlayers()) {
            if (arena != null && arena.players.contains(player) && arena.players.contains(onlinePlayer)) {
                onlinePlayer.showPlayer(PixelsJump.instance, player)
                player.showPlayer(PixelsJump.instance, onlinePlayer)
                continue
            } else {
                if (ArenaHelper.playerArena.containsKey(onlinePlayer) && player != onlinePlayer) continue
                player.showPlayer(PixelsJump.instance, onlinePlayer)
                onlinePlayer.showPlayer(PixelsJump.instance, player)
            }


        }
    }

    /**
     * @param name of the arena
     * @param sender who asks if the arena exists
     * checks if an arena with the @param name exists
     * if not sends a message to @param player
     * */
    fun checkForArena(name: String, sender: Player? = null): Boolean {
        val arena = ArenaHelper.arenas.find { it.name == name }
        if (arena == null || !arena.active) {
            sender?.sendMessage(Messages.messages["no-arena"]!!.replace("%arena%", name))
            return false
        }
        return true
    }


    val arenaPlayerNames = mutableMapOf<Player, String?>()

}
