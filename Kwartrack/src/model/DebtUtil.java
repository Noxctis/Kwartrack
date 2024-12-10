/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Chrys Sean Sevilla
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DebtUtil {

    // Method to get the default due date (3 days from the current date)
    public static String getDefaultDueDate() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Add 3 days to the current date
        LocalDate dueDate = currentDate.plusDays(3);

        // Format the date as a string in the desired format (e.g., yyyy-MM-dd)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dueDate.format(formatter);
    }
}

