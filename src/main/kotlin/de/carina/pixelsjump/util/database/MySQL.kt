package de.carina.pixelsjump.util.database


import de.carina.pixelsjump.util.files.Configuration
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import java.io.File
import java.sql.Connection
import java.sql.DriverManager

class MySQL {

    init {
        if (Configuration.config["mysql"] as Boolean) {
            createDatabaseIfNotExists()
        }
    }

    companion object {
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

    private fun createTableStatsPlayer(): Boolean {
        val sqlCommand = "CREATE TABLE IF NOT EXISTS statsPlayer (" +
                "uuid VARCHAR(36) NOT NULL," +
                "kills INT NOT NULL," +
                "deaths INT NOT NULL," +
                "points INT NOT NULL," +
                "kdr DOUBLE NOT NULL," +
                "wins INT NOT NULL," +
                "loses INT NOT NULL," +
                "games INT NOT NULL," +
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
            //  StatsSystem.playerStats[UUID.fromString(result.getString("uuid"))] = StatsPlayer(result.getInt("kills"), result.getInt("deaths"), result.getInt("points"), result.getDouble("kdr"), result.getInt("wins"), result.getInt("loses"), result.getInt("games"))
            //      data class PlayerStats(var playerName: String, var uuid: UUID, var games: Int = 0, var points: Int = 0, var fails: Int = 0, var wins: Int = 0)

        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Configuration.config["prefix"] as String) + "§aAdded all players from database to playerlist...")

        return true

    }

}
