package com.example.journalapp.dao;

import com.example.journalapp.model.JournalEntry;
import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object interface for journal entry operations.
 */
public interface JournalEntryDAO {
    /**
     * Persist a new journal entry.
     */
    void createEntry(JournalEntry entry) throws SQLException;

    /**
     * Update an existing journal entry.
     */
    void updateEntry(JournalEntry entry) throws SQLException;

    /**
     * Remove a journal entry by its ID.
     */
    void deleteEntry(int entryId) throws SQLException;

    /**
     * List all entries for a specific user.
     */
    List<JournalEntry> findEntriesByUser(int userId) throws SQLException;

    /**
     * Search entries by keyword in title or content.
     */
    List<JournalEntry> searchEntries(int userId, String keyword) throws SQLException;

    /**
     * Retrieve a single entry by its ID.
     */
    JournalEntry findById(int entryId) throws SQLException;
}
