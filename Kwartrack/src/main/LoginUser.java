package main;

import db.UserDAO;

public class LoginUser {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        String username = "user123";
        String password = "password123";
        
        boolean loginSuccess = userDAO.loginUser(username, password);
        System.out.println("Login successful: " + loginSuccess);
    }
}
