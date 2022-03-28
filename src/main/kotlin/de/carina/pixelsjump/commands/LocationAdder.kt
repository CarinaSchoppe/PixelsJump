package de.carina.pixelsjump.commands

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.ArenaHelper
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class LocationAdder(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {

    fun execute() {
        if (PixelsJump.utility.preCommandStuff(sender, command, args, 3, "add", "pixelsjump.addLocation"))
            return


        //format: <type> <name> <position>
        val player = sender as Player
        val arena = ArenaHelper.getArena(args[1])
        if (args[2] == "start") {
            arena.addStartLocation(player.location)
            sender.sendMessage(PixelsJump.utility.messageConverter("add-start").replace("%arena%", args[1]))
        } else if (args[2] == "end") {
            arena.addEndLocation(player.location)
            sender.sendMessage(PixelsJump.utility.messageConverter("add-end").replace("%arena%", args[1]))
        }
    }


}
