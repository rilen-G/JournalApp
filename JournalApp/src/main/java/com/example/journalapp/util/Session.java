package com.example.journalapp.util;

import com.example.journalapp.model.User;

/**
 * Holds the currently authenticated user for the running application.
 */
public class Session {
    private static User currentUser;

    /** Call once upon successful login. */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    /** Retrieve the logged-in user in any controller or service. */
    public static User getCurrentUser() {
        return currentUser;
    }

    /** Clear session on logout. */
    public static void clear() {
        currentUser = null;
    }
}