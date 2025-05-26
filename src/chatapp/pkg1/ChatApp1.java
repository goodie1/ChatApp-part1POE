package chatapp.pkg1;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

public class ChatApp1 {
    public static void main(String[] args) {
        // Create an instance of the Login class to handle registration and login
        Login loginSystem = new Login();
        boolean isLogin = false;

        // Loop to display the initial menu until the user logs in or exits
        while (true) {
            String choice = JOptionPane.showInputDialog(
                    "Welcome to ChatApp\n\n" +
                    "1. Register\n" +
                    "2. Login\n" +
                    "3. Exit\n\n" +
                    "Please Login or Create an account...");
            if (choice == null) return;

            // Option 1: Register a new user
            if (choice.equals("1")) {
                String firstName = JOptionPane.showInputDialog("Enter First Name:");
                String lastName = JOptionPane.showInputDialog("Enter Last Name:");
                String username = JOptionPane.showInputDialog("Enter Username (must include _ and be <= 5 characters):");
                String phone = JOptionPane.showInputDialog("Enter Phone Number (e.g. +27XXXXXXXXX):");
                String password = JOptionPane.showInputDialog("Create Password (must include 8+ characters, 1 capital, 1 number, 1 special):");

                // Call method to register user and show result
                String registrationResult = loginSystem.registerUser(firstName, lastName, username, password, phone);
                JOptionPane.showMessageDialog(null, registrationResult);
                // Show the main menu after registration
                showMainMenu();
                break;
                
               // Option 2: User login  
            } else if (choice.equals("2")) {
                String loginUsername = JOptionPane.showInputDialog("Enter Username:");
                String loginPassword = JOptionPane.showInputDialog("Enter Password:");
                String loginMessage = loginSystem.returnLoginStatus(loginUsername, loginPassword);
                JOptionPane.showMessageDialog(null, loginMessage);
                showMainMenu();
                // If login successful, set flag and exit loop
                if (loginMessage.equals("Login successful")) {
                    isLogin = true;
                    break;
                }
                
              // Option 3: Exit the application  
            } else if (choice.equals("3")) {
                JOptionPane.showMessageDialog(null, "Goodbye!");
                return;
            } else {
               // Invalid input 
                JOptionPane.showMessageDialog(null, "Invalid choice. Please enter 1, 2, or 3.");
            }
        }

        // If user successfully logged in, show the main menu again
        if (isLogin) {
            showMainMenu();
        }
    }

    private static void showMainMenu() {
        while (true) {
            // Display main menu options
            String input = JOptionPane.showInputDialog(
                    "=== Main Menu ===\n" +
                    "1. Send Messages\n" +
                    "2. Show Sent Messages\n" +
                    "3. Logout\n" +
                    "Enter your choice:");
            if (input == null) return;

            switch (input) {
                case "1":
                    // Call method to send messages
                    sendMessages();
                    break;
                case "2":
                    // Show all sent/stored messages
                    JOptionPane.showMessageDialog(null, message.printMessages());
                    break;
                case "3":
                    // Logout and return to exit main menu loop
                    JOptionPane.showMessageDialog(null, "You have been logged out.");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Try again.");
            }
        }
    }

    private static void sendMessages() {
        int numMessages = 0;
        // Prompt user for number of messages to send
        try {
            numMessages = Integer.parseInt(JOptionPane.showInputDialog("How many messages do you want to send?"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number of messages.");
            return;
        }

        // Loop for each message
        for (int i = 0; i < numMessages; i++) {
            String content;
            
            // Prompt and validate message length
            do {
                content = JOptionPane.showInputDialog("Enter message " + (i + 1) + " (max 50 characters):");
                if (content == null) return;
                if (content.length() > 50) {
                    JOptionPane.showMessageDialog(null, "Message too long. Please keep it within 50 characters.");
                }
            } while (content.length() > 50);
            
            // Prompt for recipient number
            String recipient = JOptionPane.showInputDialog("Enter recipient for message " + (i + 1) + ":");
            if (recipient == null) return;

            message msg = new message(recipient, content);

            // Provide action options: send, disregard, store
            String[] options = {"Send", "Disregard", "Store"};
            int action = JOptionPane.showOptionDialog(null, "Choose what to do with the message:",
                    "Message Option", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            String result = "";
            switch (action) {
                case 0:
                    result = message.sendMessage(msg, "send");
                    break;
                case 1:
                    result = message.sendMessage(msg, "disregard");
                    break;
                case 2:
                    result = message.sendMessage(msg, "store");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "No action selected.");
                    continue;
            }
 
             // Show message details and action result
            JOptionPane.showMessageDialog(null,
                    "Message ID: " + msg.getMessageId() + "\n" +
                    "Recipient: " + msg.getRecipient() + "\n" +
                    "Message: " + msg.getContent() + "\n" +
                    "Hash: " + msg.getHash() + "\n" +
                    result);
        }

        // Show total number of messages processed
        JOptionPane.showMessageDialog(null, "Total messages sent: " + message.returnTotalMessages());
    }
}