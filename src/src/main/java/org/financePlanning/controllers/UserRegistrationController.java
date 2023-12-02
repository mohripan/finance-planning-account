package org.financePlanning.controllers;

import org.financePlanning.models.User;
import org.financePlanning.services.UserRegistrationService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRegistrationController extends HttpServlet {
    private UserRegistrationService registrationService = new UserRegistrationService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User newUser = new User(username, email, password);

        boolean isRegistered = registrationService.registerUser(newUser);

        if (isRegistered) {
            // Handle successful registration
            resp.getWriter().write("User registered successfully");
        } else {
            // Handle registration failure
            resp.getWriter().write("Registration failed");
        }
    }
}
