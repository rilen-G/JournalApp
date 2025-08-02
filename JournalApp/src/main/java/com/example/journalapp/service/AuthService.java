package com.example.journalapp.service;

import com.example.journalapp.dao.UserDAO;
import com.example.journalapp.dao.SQLiteUserDAO;
import com.example.journalapp.model.User;
import com.example.journalapp.util.PasswordUtils;

import java.sql.SQLException;

public class AuthService {
    private final UserDAO userDAO;

    public AuthService() throws SQLException {
        this.userDAO = new SQLiteUserDAO();
    }

    public User signup(String username, String rawPassword) throws SQLException, IllegalArgumentException {
        if (userDAO.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already taken");
        }
        String hash = PasswordUtils.hash(rawPassword);
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(hash);
        userDAO.createUser(user);
        return user;
    }

    public User login(String username, String rawPassword) throws SQLException, IllegalArgumentException {
        User user = userDAO.findByUsername(username);
        if (user == null || !PasswordUtils.verify(rawPassword, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return user;
    }
}
