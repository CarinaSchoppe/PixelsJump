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
        ymlConfiguration.addDefault("no-player", "&cYou must be a player!")
        ymlConfiguration.addDefault("no-args", "&cYou must enter the command arguments!")
        ymlConfiguration.addDefault("no-player-found", "&cNo player found!")
        ymlConfiguration.addDefault("finished", "&aYou finished the jump ´n run!")
        ymlConfiguration.addDefault("no-jump", "&cYou are not in a jump ´n run!")
        ymlConfiguration.addDefault("jump-stop", "&7You stopped the jump ´n run!")
        ymlConfiguration.addDefault("jump-start", "&aYou started the jump ´n run!")


        saveMessageFile()
    }

    fun saveMessageFile() {
            ymlConfiguration.options().copyDefaults(true)
            ymlConfiguration.save(messagesFile)

    }


}