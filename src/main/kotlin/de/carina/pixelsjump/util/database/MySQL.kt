package de.carina.pixelsjump.util.database


import de.carina.pixelsjump.util.files.Configuration
import de.carina.pixelsjump.util.files.Messages
import de.carina.pixelsjump.util.stats.PlayerStatsHandler
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class MySQL {

    init {
        if (Configuration.config["mysql"] as Boolean) {
            createDatabaseIfNotExists()
        }
    }

    companion object {
        fun loadPlayers() {
            if (Configuration.config["mysql"] as Boolean) {
                val statement = connection.createStatement()
                val result = statement.executeQuery("SELECT * FROM statsPlayer")
                while (result.next()) {
                    val playerName = result.getString("playerName")
                    val uuid = UUID.fromString(result.getString("uuid"))
                    val games = result.getInt("games")
                    val points = result.getInt("points")
                    val fails = result.getInt("fails")
                    val wins = result.getInt("wins")
                    Bukkit.getConsoleSender().sendMessage(
                        Messages.messages["stats-loaded"]!!.replace("%player%", Bukkit.getOfflinePlayers().firstOrNull { it.uniqueId == uuid }?.name ?: "offline")
                    )
                    PlayerStatsHandler.statistics.add(PlayerStatsHandler.PlayerStats(playerName, uuid, games, points, fails, wins))
                }
            }
        }

        fun saveStats() {
            if (Configuration.config["mysql"] as Boolean) {
                val statement = connection.createStatement()
                for (stats in PlayerStatsHandler.statistics) {
                    statement.execute("REPLACE INTO statsPlayer (playerName, uuid, games, points, fails, wins) VALUES ('${stats.playerName}', '${stats.uuid}', ${stats.games}, ${stats.points}, ${stats.fails}, ${stats.wins})")
                }
            }
        }

        lateinit var connection: Connection
    }

    private fun createDatabaseIfNotExists(): Boolean {
        if (!(Configuration.config["mysql"]!! as Boolean)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Configuration.config["prefix"] as String) + "§7Using YML-Settings file")

            return false
        }


        //check if the .db file exists
        //get plugins folderpath

        lateinit var databaseFile: File
        if (Configuration.config["sqlite-enable"] as Boolean) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Configuration.config["prefix"] as String) + "§aUsing SQLite-Settings file")

            databaseFile = try {
                val databaseFile = File(Configuration.config["sqlite-path"]!! as String)
                if (!databaseFile.exists()) {
                    databaseFile.createNewFile()
                }
                databaseFile
            } catch (e: Exception) {
                val file = File("plugins/PixelsJumpRemastered/database.db")
                if (!file.exists()) {
                    file.createNewFile()
                    Configuration.config["sqlite-path"] = file.absolutePath
                    Configuration.ymlConfiguration.set("sqlite-path", file.absolutePath)
                    Configuration.ymlConfiguration.save(Configuration.configFile)
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Configuration.config["prefix"] as String) + "§aCreating own database-file...")

                }
                file
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Configuration.config["prefix"] as String) + "§aUsing MySQL database connection!")

        }

        val host = Configuration.config["mysql-host"]!! as String
        val port = Configuration.config["mysql-port"]!! as Int
        val database = Configuration.config["mysql-database"]!! as String
        val username = Configuration.config["mysql-user"]!! as String
        val password = Configuration.config["mysql-password"]!! as String
        //create a connection to the mysql database
        connection = if (Configuration.config["sqlite-enable"] as Boolean) DriverManager.getConnection("jdbc:sqlite:${databaseFile.absolutePath}")
        else DriverManager.getConnection("jdbc:mysql://$host:$port/$database?useSSL=false", username, password)
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Configuration.config["prefix"] as String) + "§aConnected to database!")

        createTableStatsPlayer()

        addAllPlayersFromDataBaseToPLayerList()


        return true
    }

    //    data class PlayerStats(var playerName: String, var uuid: UUID, var games: Int = 0, var points: Int = 0, var fails: Int = 0, var wins: Int = 0)
    private fun createTableStatsPlayer(): Boolean {
        val sqlCommand = "CREATE TABLE IF NOT EXISTS statsPlayer (" +
                "playerName VARCHAR(36) NOT NULL," +
                "uuid VARCHAR(36) NOT NULL," +
                "games INT NOT NULL," +
                "points INT NOT NULL," +
                "fails DOUBLE NOT NULL," +
                "wins INT NOT NULL," +
                "PRIMARY KEY (uuid)" +
                ");"
        //execute the sqlCommand on the connection
        val statement = connection.prepareStatement(sqlCommand)
        statement.execute()
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Configuration.config["prefix"] as String) + "§aCreating table statsPlayer...")

        return true
    }

    private fun addAllPlayersFromDataBaseToPLayerList(): Boolean {
        if (!(Configuration.config["mysql"]!! as Boolean))
            return false


        //create sqlite connection to databaseFile
        val sqlCommand = "SELECT * FROM statsPlayer"
        val statement = connection.prepareStatement(sqlCommand)
        val result = statement.executeQuery()
        while (result.next()) {
            val playerName = result.getString("playerName")
            val uuid = result.getString("uuid")
            val games = result.getInt("games")
            val points = result.getInt("points")
            val fails = result.getInt("fails")
            val wins = result.getInt("wins")
            val playerStats = PlayerStatsHandler.PlayerStats(playerName, UUID.fromString(uuid), games, points, fails, wins)
            PlayerStatsHandler.statistics.add(playerStats)

        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Configuration.config["prefix"] as String) + "§aAdded all players from database to playerlist...")

        return true

    }

}
