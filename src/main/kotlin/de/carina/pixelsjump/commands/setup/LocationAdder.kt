package de.carina.pixelsjump.commands.setup

import de.carina.pixelsjump.PixelsJump
import de.carina.pixelsjump.util.CustomLocation
import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.files.Messages
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class LocationAdder(private val sender: CommandSender, private val command: Command, private val args: Array<out String>) {

    fun execute() {
        if (!PixelsJump.utility.preCommandStuff(sender, command, args, 3, "add", "pixelsjump.addLocation.*"))
            return


        //format: <type> <name> <position>
        val player = sender as Player
        if (ArenaHelper.arenaNotExists(args[1])) {
            sender.sendMessage(Messages.messages["no-arena"]!!.replace("%arena%", args[1]))
            return
        }
        val arena = ArenaHelper.getOrCreateArena(args[1])
        if (args[2].equals("start", true)) {
            arena.startLocation = CustomLocation(player.location.toCenterLocation())
            sender.sendMessage(Messages.messages["add-start"]!!.replace("%arena%", args[1]))
        } else if (args[2].equals("checkpoint", true)) {
            arena.addCheckpointLocation(player.location)
            sender.sendMessage(Messages.messages["add-checkpoint"]!!.replace("%arena%", args[1]).replace("%number%", arena.checkPoints.size.toString()))
        } else if (args[2].equals("back", true)) {
            arena.backLocation = CustomLocation(player.location.toCenterLocation())
            sender.sendMessage(Messages.messages["add-back"]!!.replace("%arena%", args[1]))
        } else if (args[2].equals("finish", true)) {
            arena.finishLocation = CustomLocation(player.location.toCenterLocation())
            sender.sendMessage(Messages.messages["add-end"]!!.replace("%arena%", args[1]))
        }
    }

}
