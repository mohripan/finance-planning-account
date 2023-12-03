package org.financePlanning.services;

import org.financePlanning.models.User;
import org.financePlanning.utilities.DatabaseConnectionUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegistrationService {

    public boolean registerUser(User user) {
        if (doesUserExist(user.getUsername())) {
            return false; // User already exists
        }

        String hashedPassword = hashPassword(user.getPasswordHash());
        user.setPasswordHash(hashedPassword);

        try(Connection conn = DatabaseConnectionUtil.getConnection()) {
            String sql = "INSERT INTO users(username, email, password_hash, created_at) VALUES (?, ?, ?, NOW())";
            try(PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getPasswordHash());

                int affectedRows = stmt.executeUpdate();
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    private boolean doesUserExist(String username) {
        try (Connection conn = DatabaseConnectionUtil.getConnection()) {
            String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
