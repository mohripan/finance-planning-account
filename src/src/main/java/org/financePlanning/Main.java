package org.financePlanning;

import org.financePlanning.models.User;
import org.financePlanning.services.UserRegistrationService;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        UserRegistrationService service = new UserRegistrationService();
        User newUser = new User("username", "email@example.com", "password123");

        boolean isRegistered = service.registerUser(newUser);
        if (isRegistered) {
            System.out.println("User registered successfully with hashed password: " + newUser.getPasswordHash());
        } else {
            System.out.println("Registration failed.");
        }
    }
}