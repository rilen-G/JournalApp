package com.example.journalapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Manages the SQLite database connection and schema initialization.
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:journal.db";
    private static Connection connection;

    private DatabaseManager() {
        // Private constructor to prevent instantiation
    }

    /**
     * Returns a singleton Connection to the SQLite database.
     * @return Connection instance
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
        }
        return connection;
    }

    /**
     * Initializes the database schema: creates tables if they do not exist.
     * Must be called once at application startup.
     * @throws SQLException if a database access error occurs
     */
    public static void initializeDatabase() throws SQLException {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // Create users table
            String createUsers = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT UNIQUE NOT NULL,
                    password_hash TEXT NOT NULL
                );
                """;
            stmt.execute(createUsers);

            // Create journal entries table
            String createEntries = """
                CREATE TABLE IF NOT EXISTS entries (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER NOT NULL,
                    title TEXT NOT NULL,
                    content TEXT,
                    created_at TEXT NOT NULL,
                    updated_at TEXT,
                    FOREIGN KEY(user_id) REFERENCES users(id)
                );
                """;
            stmt.execute(createEntries);
        }
    }
}
