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
        ymlConfiguration.addDefault("default-command", "&7Please use &6/pixelsjump <arguments> <arena> <optional> &7!")
        ymlConfiguration.addDefault("no-arena-name", "&cYou must enter the arena name first before you can do this!")
        ymlConfiguration.addDefault("add-start", "&aYou successfully added a start point for arena &6%arena%&a!")
        ymlConfiguration.addDefault("no-jump", "&cYou are not in a jump ´n run!")
        ymlConfiguration.addDefault("arena-saved", "&aYou successfully saved the arena &6%arena%&a!")
        ymlConfiguration.addDefault("loading-arenas-start", "&7Loading arenas...")
        ymlConfiguration.addDefault("loading-arenas-end", "&7Loading arenas finished!")
        ymlConfiguration.addDefault("loading-statistics-start", "&7Loading player statistics...")
        ymlConfiguration.addDefault("loading-statistics-end", "&7Loading player statistics finished!")
        ymlConfiguration.addDefault("arena-single", "&aYou successfully made the arena a single jump Arena with the name &6%arena%&a!")
        ymlConfiguration.addDefault("jump-stop", "&7You stopped the jump ´n run with the name &6%name%&7!")
        ymlConfiguration.addDefault("jump-start", "&aYou started the jump ´n run with the name &6%name%&7!")


        saveMessageFile()
    }

    fun saveMessageFile() {
            ymlConfiguration.options().copyDefaults(true)
            ymlConfiguration.save(messagesFile)

    }


}
