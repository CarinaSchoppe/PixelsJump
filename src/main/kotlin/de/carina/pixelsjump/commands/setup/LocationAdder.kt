package de.carina.pixelsjump.commands.setup

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.ArenaHelper
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class LocationAdder(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {

    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 3, "add", "pixelsjump.addLocation"))
            return


        //format: <type> <name> <position>
        val player = sender as Player
        val arena = ArenaHelper.getArena(args[1])
        if (args[2].equals("start", true)) {
            arena.addStartLocation(player.location)
            sender.sendMessage(PixelsJump.utility.messageConverter("add-start").replace("%arena%", args[1]))
        } else if (args[2].equals("end", true)) {
            arena.addEndLocation(player.location)
            sender.sendMessage(PixelsJump.utility.messageConverter("add-end").replace("%arena%", args[1]))
        } else if (args[2].equals("checkpoint", true)) {
            arena.addCheckpointLocation(player.location)
            sender.sendMessage(PixelsJump.utility.messageConverter("add-checkpoint").replace("%arena%", args[1]).replace("%number%", arena.checkPoints.size.toString()))
        } else if (args[2].equals("back", true)) {
            arena.addBackLocation(player.location)
            sender.sendMessage(PixelsJump.utility.messageConverter("add-back").replace("%arena%", args[1]))
        }
    }

}
