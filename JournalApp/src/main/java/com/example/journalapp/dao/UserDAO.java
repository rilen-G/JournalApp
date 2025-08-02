package com.example.journalapp.dao;

import com.example.journalapp.model.User;
import java.sql.SQLException;

/**
 * Data Access Object interface for user operations.
 */
public interface UserDAO {
    /**
     * Persist a new user in the database.
     */
    void createUser(User user) throws SQLException;

    /**
     * Retrieve a user by their unique username.
     */
    User findByUsername(String username) throws SQLException;

    /**
     * Retrieve a user by their ID.
     */
    User findById(int id) throws SQLException;
}
