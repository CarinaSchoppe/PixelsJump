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

        saveMessageFile()
    }

    fun saveMessageFile() {
        try {
            ymlConfiguration.options().copyDefaults(true)
            ymlConfiguration.save(messagesFile)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}
