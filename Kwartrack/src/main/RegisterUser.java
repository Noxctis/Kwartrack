package main;

import db.UserDAO;

public class RegisterUser {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        
        String username = "user123";
        String email = "user@example.com";
        String password = "password123";
        
        //boolean registrationSuccess = userDAO.registerUser(username, email, password);
        //System.out.println("Registration successful: " + registrationSuccess);
    }
}
