
package chatapp.pkg1;

import java.io.*;
import java.util.Scanner;

public class Login {
    // Define the path to the file where user credentials will be stored
private final String USER_FILE = "users.txt";

 //Validates the username.
 public boolean checkUsername(String username) {
    return username.contains("_") && username.length() <= 5;
}

//Checks if the password meets complexity requirements.
 public boolean checkPasswordComplexity(String password) {
    return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,}$");
}

 //Validates the phone number format.
 public boolean checkCellNumber(String phone) {
    return phone.matches("^\\+\\d{9,15}$");
}

//Registers a new user after validating their credentials.
 public String registerUser(String firstName, String lastName, String username, String password, String phone) {
    StringBuilder errors = new StringBuilder();

    // Validate username
    if (!checkUsername(username))
        errors.append("Username must contain an underscore and be max 5 characters.\n");

    // Validate password complexity
    if (!checkPasswordComplexity(password))
        errors.append("Password must be at least 8 chars, 1 uppercase, 1 number, 1 special char.\n");

    // Validate phone number
    if (!checkCellNumber(phone))
        errors.append("Phone number must include country code (e.g. +27...).\n");

    // If any validation fails, return errors
    if (errors.length() > 0) {
        return "Registration failed:\n" + errors;
    }

    // If all validations pass, save user to file
    saveUserToFile(username, password);
    return "Welcome to QuickChat... ";
}

 //Saves a new user's credentials to the users.txt file.

private void saveUserToFile(String username, String password) {
    try (FileWriter writer = new FileWriter(USER_FILE, true)) {
        writer.write(username + "," + password + "\n");
    } catch (IOException e) {
        System.out.println("Error saving user: " + e.getMessage());
    }
}


 
 //Returns true if a matching username and password are found.

public boolean loginUser(String username, String password) {
    try (Scanner scanner = new Scanner(new File(USER_FILE))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String fileUsername = parts[0];
                String filePassword = parts[1];

                // Check if entered credentials match a stored user
                if (fileUsername.equals(username) && filePassword.equals(password)) {
                    return true;
                }
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("User file not found.");
    }

    // Return false if no matching credentials are found
    return false;
}


 //Returns a login status message depending on whether login was successful.
 
public String returnLoginStatus(String username, String password) {
    if (loginUser(username, password)) {
        return "Welcome to QuickChat... ";
    } else {
        return "Username or password is incorrect.";
    }
  }
}
