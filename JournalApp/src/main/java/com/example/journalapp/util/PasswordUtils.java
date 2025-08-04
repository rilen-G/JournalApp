package com.example.journalapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for hashing and verifying passwords.
 */
public class PasswordUtils {
    private static final String ALGORITHM = "SHA-256";

    private PasswordUtils() {
    }

    /**
     * Hashes the given raw password using SHA-256 and returns the hex-encoded hash.
     * @param rawPassword the plain-text password
     * @return hex-encoded SHA-256 hash
     */
    public static String hash(String rawPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            byte[] digest = md.digest(rawPassword.getBytes());
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found: " + ALGORITHM, e);
        }
    }

    /**
     * Verifies a raw password against the stored hex-encoded hash.
     * @param rawPassword the plain-text password to check
     * @param storedHash the hex-encoded hash to compare against
     * @return true if the password matches, false otherwise
     */
    public static boolean verify(String rawPassword, String storedHash) {
        String computed = hash(rawPassword);
        return computed.equalsIgnoreCase(storedHash);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
