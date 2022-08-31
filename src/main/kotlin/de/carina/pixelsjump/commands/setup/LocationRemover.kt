package de.carina.pixelsjump.commands.setup

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.files.Messages
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class LocationRemover(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {

    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 3, "remove", "pixelsjump.removeLocation.*"))
            return


        //format: <type> <name> <position>
        if (ArenaHelper.arenaNotExists(args[1])) {
            sender.sendMessage(Messages.messages["no-arena"]!!.replace("%arena%", args[1]))
            return
        }
        val arena = ArenaHelper.getOrCreateArena(args[1])
        arena.active = false
        when (args[2].lowercase()) {
            "start" -> {
                arena.startLocation = null
                sender.sendMessage(Messages.messages["start-location-removed"]!!.replace("%arena%", args[1]))

            }

            "checkpoint" -> {
                if (!arena.checkPoints.isEmpty())
                    arena.checkPoints.remove(arena.checkPoints.last())
                sender.sendMessage(Messages.messages["checkpoint-location-removed"]!!.replace("%arena%", args[1]))

            }

            "back" -> {
                arena.backLocation = null
                sender.sendMessage(Messages.messages["back-location-removed"]!!.replace("%arena%", args[1]))

            }

            "finish" -> {
                arena.endLocation = null
                sender.sendMessage(Messages.messages["end-location-removed"]!!.replace("%arena%", args[1]))
            }

            else -> throw IllegalArgumentException("Unknown location type: ${args[2]}")
        }

    }

}
