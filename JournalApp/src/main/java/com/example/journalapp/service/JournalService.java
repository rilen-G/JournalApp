package com.example.journalapp.service;

import com.example.journalapp.dao.JournalEntryDAO;
import com.example.journalapp.dao.SQLiteJournalEntryDAO;
import com.example.journalapp.model.JournalEntry;
import com.example.journalapp.model.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JournalService {
    private final JournalEntryDAO entryDAO;

    public JournalService() throws SQLException {
        this.entryDAO = new SQLiteJournalEntryDAO();
    }

    public void addEntry(String title, String content, User user) throws SQLException {
        JournalEntry entry = new JournalEntry();
        entry.setId(user.getId());
        entry.setTitle(title);
        entry.setContent(content);
        entry.setCreatedAt(java.time.LocalDateTime.now());
        entryDAO.createEntry(entry);
    }

    public void updateEntry(JournalEntry entry) throws SQLException {
        entryDAO.updateEntry(entry);
    }

    public void deleteEntry(int entryId) throws SQLException {
        entryDAO.deleteEntry(entryId);
    }

    public List<JournalEntry> getEntries(User user) throws SQLException {
        return entryDAO.findEntriesByUser(user.getId());
    }

    public List<JournalEntry> searchEntries(User user, String keyword) throws SQLException {
        return entryDAO.searchEntries(user.getId(), keyword);
    }

    /**
     * Counts consecutive-day journaling streak up to today.
     */
    public int calculateStreak(User user) throws SQLException {
        List<JournalEntry> entries = entryDAO.findEntriesByUser(user.getId());
        Set<LocalDate> dates = entries.stream()
                .map(e -> e.getCreatedAt().toLocalDate())
                .collect(Collectors.toSet());
        int streak = 0;
        LocalDate today = LocalDate.now();
        while (dates.contains(today.minusDays(streak))) {
            streak++;
        }
        return streak;
    }
}
