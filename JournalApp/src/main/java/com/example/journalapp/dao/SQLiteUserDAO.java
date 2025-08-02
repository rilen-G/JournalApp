package com.example.journalapp.dao;

import com.example.journalapp.model.User;
import com.example.journalapp.util.DatabaseManager;

import java.sql.*;

public class SQLiteUserDAO implements UserDAO {
    private final Connection conn;

    public SQLiteUserDAO() throws SQLException {
        this.conn = DatabaseManager.getConnection();
    }

    @Override
    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO users(username, password_hash) VALUES(?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys   ()) {
                if (keys.next()) {
                    user.setId(keys.getInt(1));
                }
            }
        }
    }

    @Override
    public User findByUsername(String username) throws SQLException {
        String sql = "SELECT id, username, password_hash FROM users WHERE username = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPasswordHash(rs.getString("password_hash"));
                    return u;
                }
                return null;
            }
        }
    }

    @Override
    public User findById(int id) throws SQLException {
        String sql = "SELECT id, username, password_hash FROM users WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPasswordHash(rs.getString("password_hash"));
                    return u;
                }
                return null;
            }
        }
    }
}
