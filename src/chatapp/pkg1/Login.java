
package chatapp.pkg1;

import java.io.*;
import java.util.Scanner;

public class Login {
    // Define the path to the file where user credentials will be stored
private final String USER_FILE = "users.txt";
 private String currentUser = null;

    // Register a new user
    public String registerUser(String firstName, String lastName, String username, String password, String phone) {
        if (!checkUsername(username)) {
            return "Username must contain '_' and be at most 5 characters.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password must be at least 8 characters with 1 capital letter, 1 number, and 1 special character.";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(username + "," + password + "," + firstName + "," + lastName + "," + phone);
            writer.newLine();
            return "Registration successful!";
        } catch (IOException e) {
            return "Error saving user: " + e.getMessage();
        }
    }

    // Login validation
    public String returnLoginStatus(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    currentUser = username;
                    return "Login successful";
                }
            }
        } catch (IOException e) {
            return "Error reading user data: " + e.getMessage();
        }
        return "Login failed: Invalid username or password.";
    }

    public boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,}$");
    }

    public String getCurrentUser() {
        return currentUser != null ? currentUser : "Unknown";
    }
}
