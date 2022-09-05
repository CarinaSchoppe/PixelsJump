package de.carina.pixelsjump.util.misc

import de.carina.pixelsjump.util.BlockGenerator
import de.carina.pixelsjump.util.arena.ArenaHelper
import de.carina.pixelsjump.util.files.Configuration
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective

object Scoreboard {


    fun addPlayerScoreboard(player: Player) {
        val scoreboard = Bukkit.getScoreboardManager().newScoreboard
        val objective: Objective = scoreboard.registerNewObjective("aaa", Criteria.DUMMY, Component.text(Configuration.config["prefix"] as String))
        scoreboard.clearSlot(org.bukkit.scoreboard.DisplaySlot.SIDEBAR)
        objective.displaySlot = DisplaySlot.SIDEBAR
        val six = objective.getScore("§Game: §d${ArenaHelper.playerArena[player]!!.name}")
        val five = objective.getScore("§aFails:")
        val four = objective.getScore("§f" + PlayerStatsHandler.statistics[player.uniqueId]!!.fails)
        val three = objective.getScore("§Checkpoint:")
        val two = objective.getScore("§f" + ArenaHelper.playerArena[player]!!.checkPoints.indexOf(BlockGenerator.playerCheckpoints[player]!!))
        val one = objective.getScore("§cPoints: ")
        val zero = objective.getScore("§f" + PlayerStatsHandler.statistics[player.uniqueId]!!.points)
        four.score = 4
        six.score = 6
        three.score = 3
        two.score = 2
        one.score = 1
        zero.score = 0
        five.score = 5
        player.scoreboard = scoreboard
    }
}
