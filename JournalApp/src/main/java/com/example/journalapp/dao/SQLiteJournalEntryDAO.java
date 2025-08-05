package com.example.journalapp.dao;

import com.example.journalapp.model.JournalEntry;
import com.example.journalapp.util.DatabaseManager;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SQLiteJournalEntryDAO implements JournalEntryDAO {
    private final Connection conn;

    public SQLiteJournalEntryDAO() throws SQLException {
        this.conn = DatabaseManager.getConnection();
    }

    @Override
    public void createEntry(JournalEntry entry) throws SQLException {
        String sql = "INSERT INTO entries(user_id, title, content, created_at) VALUES(?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, entry.getId());
            ps.setString(2, entry.getTitle());
            ps.setString(3, entry.getContent());
            ps.setString(4, entry.getCreatedAt().toString());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    entry.setId(keys.getInt(1));
                }
            }
        }
    }

    @Override
    public void updateEntry(JournalEntry entry) throws SQLException {
        String sql = "UPDATE entries SET title = ?, content = ?, updated_at = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entry.getTitle());
            ps.setString(2, entry.getContent());
            ps.setString(3, LocalDateTime.now().toString());
            ps.setInt(4, entry.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteEntry(int entryId) throws SQLException {
        String sql = "DELETE FROM entries WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, entryId);
            ps.executeUpdate();
        }
    }

    @Override
    public List<JournalEntry> findEntriesByUser(int userId) throws SQLException {
        String sql = "SELECT * FROM entries WHERE user_id = ? ORDER BY created_at DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                List<JournalEntry> list = new ArrayList<>();
                while (rs.next()) {
                    JournalEntry e = new JournalEntry();
                    e.setId(rs.getInt("id"));
                    e.setUserId(rs.getInt("user_id"));
                    e.setTitle(rs.getString("title"));
                    e.setContent(rs.getString("content"));
                    e.setCreatedAt(LocalDateTime.parse(rs.getString("created_at")));
                    String upd = rs.getString("updated_at");
                    if (upd != null) e.setUpdatedAt(LocalDateTime.parse(upd));
                    list.add(e);
                }
                return list;
            }
        }
    }

    @Override
    public List<JournalEntry> searchEntries(int userId, String keyword) throws SQLException {
        String sql = "SELECT * FROM entries WHERE user_id = ? AND (title LIKE ? OR content LIKE ?) ORDER BY created_at DESC";
        String pattern = "%" + keyword + "%";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, pattern);
            ps.setString(3, pattern);
            try (ResultSet rs = ps.executeQuery()) {
                List<JournalEntry> list = new ArrayList<>();
                while (rs.next()) {
                    JournalEntry e = new JournalEntry();
                    e.setId(rs.getInt("id"));
                    e.setId(rs.getInt("user_id"));
                    e.setTitle(rs.getString("title"));
                    e.setContent(rs.getString("content"));
                    e.setCreatedAt(LocalDateTime.parse(rs.getString("created_at")));
                    String upd = rs.getString("updated_at");
                    if (upd != null) e.setUpdatedAt(LocalDateTime.parse(upd));
                    list.add(e);
                }
                return list;
            }
        }
    }

    @Override
    public JournalEntry findById(int entryId) throws SQLException {
        String sql = "SELECT * FROM entries WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, entryId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    JournalEntry e = new JournalEntry();
                    e.setId(rs.getInt("id"));
                    e.setId(rs.getInt("user_id"));
                    e.setTitle(rs.getString("title"));
                    e.setContent(rs.getString("content"));
                    e.setCreatedAt(LocalDateTime.parse(rs.getString("created_at")));
                    String upd = rs.getString("updated_at");
                    if (upd != null) e.setUpdatedAt(LocalDateTime.parse(upd));
                    return e;
                }
                return null;
            }
        }
    }

    @Override
    public List<LocalDate> getEntryDates(int userId) throws SQLException {
        List<LocalDate> dates = new ArrayList<>();
        String query = "SELECT DATE(created_at) as entry_date FROM entries WHERE user_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                dates.add(LocalDate.parse(rs.getString("entry_date")));
            }
        }

        return dates;
    }
}
