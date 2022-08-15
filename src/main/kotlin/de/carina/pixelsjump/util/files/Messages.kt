package de.carina.pixelsjump.util.files

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object Messages {

    private const val path = "plugins/PixelsJumpRemastered/messages.yml"
    private lateinit var messagesFile: File
    lateinit var ymlConfiguration: YamlConfiguration

    fun loadMessages() {
        messagesFile = File(path)
        ymlConfiguration = YamlConfiguration.loadConfiguration(messagesFile)

        ymlConfiguration.addDefault("load", "&7The Plugin was successfully loaded!")
        ymlConfiguration.addDefault("unload", "&7The Plugin was successfully unloaded!")
        ymlConfiguration.addDefault("error", "&cAn Error occurred!")
        ymlConfiguration.addDefault("no-permission", "&cYou don't have the permission to do this!")
        ymlConfiguration.addDefault("no-player", "&cYou must be a player to do this!")
        ymlConfiguration.addDefault("no-args", "&cYou must enter the command arguments!")
        ymlConfiguration.addDefault("wrong-args", "&cYou must enter the right (amount) command arguments!")
        ymlConfiguration.addDefault("no-player-found", "&cNo player found!")
        ymlConfiguration.addDefault("finished", "&aYou finished the jump ´n run!")
        ymlConfiguration.addDefault("no-arena", "&cNo arena found with the name &6%arena%&c!")
        ymlConfiguration.addDefault("arena-not-valid", "&cThe arena &6%arena% &cis not valid!")
        ymlConfiguration.addDefault("arena-removed", "&aThe arena &6%arena% &ahas been removed!")
        ymlConfiguration.addDefault("default-command", "&7Please use &6/pixelsjump <arguments> <arena> <optional> &7!")
        ymlConfiguration.addDefault("add-back", "&aYouve successfully added the back to the arena &6%arena%&a!")
        ymlConfiguration.addDefault("checkpoint-loaded", "&7Checkpoint &6%number% for arena &6%arena%&7, &7loaded!")
        ymlConfiguration.addDefault("no-arena-name", "&cYou must enter the arena name first before you can do this!")
        ymlConfiguration.addDefault("arena-join", "&7You were teleported to the arena &6%arena%&7!")
        ymlConfiguration.addDefault("arena-allready", "&cYou are allready in the arena &6%arena%&c!")
        ymlConfiguration.addDefault("add-checkpoint", "&aYou added a checkpoint to the arena &6%arena%&a, Checkpoints: &6%number%&a!")
        ymlConfiguration.addDefault("arena-setup", "&aYou successfully setup the arena &6%arena%&a you can now use other commands!")
        ymlConfiguration.addDefault("player-stats", "&7Your Stats: &6%wins%&7 Wins, &6%games%&7 Games, &6%fails%&7 Fails, &6%points%&7 Points")
        ymlConfiguration.addDefault("player-stats-other", "&6%player% &7Stats: &6%wins%&7 Wins, &6%games%&7 Games, &6%blocks%&7 Max-Blocks, &6%fails%&7 Fails, &6%points%&7 Points")
        ymlConfiguration.addDefault("player-no-stats", "&cThe Player &6%player% &cdoesnt have any stats cuz he never joined!")
        ymlConfiguration.addDefault("arena-loaded", "&7Loaded the arena &6%arena%&7!")
        ymlConfiguration.addDefault("stats-loaded", "&7Loaded the stats for player &6%player%&7!")
        ymlConfiguration.addDefault("add-start", "&aYou successfully added a start point for arena &6%arena%&a!")
        ymlConfiguration.addDefault("no-jump", "&cYou are not in a jump ´n run!")
        ymlConfiguration.addDefault("arena-saved", "&aYou successfully saved the arena &6%arena%&a!")
        ymlConfiguration.addDefault("arena-checkpoint-reached", "&aYou reached a checkpoint in the arena &6%arena%&a!")
        ymlConfiguration.addDefault("arena-goal-reached", "&aYou reached the goal in the arena &6%arena%&a!")
        ymlConfiguration.addDefault("arena-leave", "&7You left the arena &6%arena%&7!")
        ymlConfiguration.addDefault("arena-single-yes", "&aYou successfully set the arena &6%arena%&a to single!")
        ymlConfiguration.addDefault("arena-single-no", "&cYou successfully set the arena &6%arena%&c to multi!")
        ymlConfiguration.addDefault("loading-arenas-start", "&7Loading arenas...")
        ymlConfiguration.addDefault("loading-arenas-end", "&7Loading arenas finished!")
        ymlConfiguration.addDefault("loading-statistics-start", "&7Loading player statistics...")
        ymlConfiguration.addDefault("loading-statistics-end", "&7Loading player statistics finished!")
        ymlConfiguration.addDefault("arena-single", "&aYou successfully made the arena. A single jump Arena with the name &6%arena%&a!")
        ymlConfiguration.addDefault("jump-stop", "&7You stopped the jump ´n run with the name &6%name%&7!")
        ymlConfiguration.addDefault("arena-damage-yes", "&aYou successfully enabled the damage for arena &6%arena%&a!")
        ymlConfiguration.addDefault("arena-damage-no", "&cYou successfully disabled the damage for arena &6%arena%&c!")
        ymlConfiguration.addDefault("arena-player-failed", "&cYou failed the arena &6%arena%&c!")
        ymlConfiguration.addDefault("arena-player-fell", "&cYou fell in the arena &6%arena%&c you went back to your last checkpoint!")
        ymlConfiguration.addDefault("jump-start", "&aYou started the jump ´n run with the name &6%name%&7!")
        ymlConfiguration.addDefault("block-place-cancelled", "&cYou can't place &6%block%&c in the arena &6%arena%&c!")
        ymlConfiguration.addDefault("block-break-cancelled", "&cYou can't place &6%block%&c in the arena &6%arena%&c!")


        saveMessageFile()
    }

    private fun saveMessageFile() {
        ymlConfiguration.options().copyDefaults(true)
        ymlConfiguration.save(messagesFile)

    }


}
