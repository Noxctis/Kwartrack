package main;

import db.UserDAO;
import java.util.Scanner;

public class KwartrackApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        System.out.println("Welcome to Kwartrack!");

        System.out.print("1. Register\n2. Login\nChoose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter username: ");
                String regUsername = scanner.nextLine();

                System.out.print("Enter email: ");
                String regEmail = scanner.nextLine();

                System.out.print("Enter password: ");
                String regPassword = scanner.nextLine();

                boolean registrationSuccess = userDAO.registerUser(regUsername, regEmail, regPassword);
                System.out.println("Registration successful: " + registrationSuccess);
                break;

            case 2:
                System.out.print("Enter username: ");
                String loginUsername = scanner.nextLine();

                System.out.print("Enter password: ");
                String loginPassword = scanner.nextLine();

                boolean loginSuccess = userDAO.loginUser(loginUsername, loginPassword);
                System.out.println("Login successful: " + loginSuccess);
                break;

            default:
                System.out.println("Invalid option.");
        }

        scanner.close();
    }
}
