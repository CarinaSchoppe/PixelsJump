/*
 * Copyright Notice for PixelsJumpRemastered
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 28.03.22, 15:25 by Carina The Latest changes made by Carina on 28.03.22, 15:25.
 *  All contents of "LocationFinish.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carina.pixelsjump.commands.setup

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.files.Messages
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.io.File

class ArenaFinish(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {

    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 2, "finish", "pixelsjump.finish-arena"))
            return


        //perform savety checks if all configs are valid
        if (ArenaHelper.arenaNotExists(args[1])) {
            sender.sendMessage(Messages.messages["no-arena"]!!.replace("%arena%", args[1]))
            return
        }
        val arena = ArenaHelper.getOrCreateArena(args[1])
        arena.active = false
        Bukkit.getScheduler().runTaskAsynchronously(PixelsJump.instance, Runnable {
            val file = File("plugins/PixelsJumpRemastered/arenas/${arena.name}.json")
            file.delete()
        })

        if (ArenaHelper.arenaInvalid(args[1], arena, sender as Player)) return


        sender.sendMessage(Messages.messages["arena-saved"]!!.replace("%arena%", args[1]))
        PixelsJump.utility.arenaPlayerNames.remove(sender)
        arena.saveArena()
        arena.active = true

    }
}
