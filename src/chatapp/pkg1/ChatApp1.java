package chatapp.pkg1;
import javax.swing.JOptionPane;
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

           switch (choice.trim()) {
                case "1":
                    registerFlow(loginSystem);
                    break;
                case "2":
                    loginFlow(loginSystem);
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private static void registerFlow(Login loginSystem) {
        String firstName = JOptionPane.showInputDialog("Enter First Name:");
        String lastName = JOptionPane.showInputDialog("Enter Last Name:");
        String username = JOptionPane.showInputDialog("Enter Username (must include _ and be <= 5 characters):");
        String phone = JOptionPane.showInputDialog("Enter Phone Number (e.g. +27XXXXXXXXX):");
        String password = JOptionPane.showInputDialog("Create Password (8+ chars, 1 capital, 1 number, 1 special):");

        String result = loginSystem.registerUser(firstName, lastName, username, password, phone);
        JOptionPane.showMessageDialog(null, result);
        
    }

    private static void loginFlow(Login loginSystem) {
        String username = JOptionPane.showInputDialog("Enter Username:");
        String password = JOptionPane.showInputDialog("Enter Password:");

        String status = loginSystem.returnLoginStatus(username, password);
        JOptionPane.showMessageDialog(null, status);

        if (status.equals("Login successful")) {
            showMainMenu(loginSystem);
        }
    }

    private static void showMainMenu(Login loginSystem) {
        while (true) {
            String input = JOptionPane.showInputDialog(
                    "=== Main Menu ===\n" +
                    "Logged in as: " + loginSystem.getCurrentUser() + "\n\n" +
                    "1. Send Messages\n" +
                    "2. Show Sent Messages\n" +
                    "3. Message Statistics\n" +
                    "4. Search Messages\n" +
                    "5. Delete Message\n" +
                    "6. Generate Report\n" +
                    "7. Logout\n" +
                    "Enter your choice:");

            if (input == null || input.isEmpty()) continue;

            switch (input.trim()) {
                case "1":
                    sendMessages();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, message.printMessages());
                    break;
                case "3":
                    showStatisticsMenu();
                    break;
                case "4":
                    showSearchMenu();
                    break;
                case "5":
                    String hash = JOptionPane.showInputDialog("Enter message hash to delete:");
                    if (hash != null) {
                        JOptionPane.showMessageDialog(null, message.deleteByHash(hash));
                    }
                    break;
                case "6":
                    message.displayMessageReport();
                    break;
                case "7":
                    JOptionPane.showMessageDialog(null, "Logged out.");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Try again.");
            }
        }
    }

    private static void sendMessages() {
        int numMessages;
        try {
            numMessages = Integer.parseInt(JOptionPane.showInputDialog("How many messages do you want to send?"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number.");
            return;
        }

        for (int i = 0; i < numMessages; i++) {
            String content;
            do {
                content = JOptionPane.showInputDialog("Enter message " + (i + 1) + " (max 150 characters):");
                if (content == null) return;
                if (content.length() > 150) {
                    JOptionPane.showMessageDialog(null, "Message too long. Please limit to 150 characters.");
                }
            } while (content.length() > 150);

            String recipient = JOptionPane.showInputDialog("Enter recipient for message " + (i + 1) + "(e.g. +27XXXXXXXXX):");
            if (recipient == null) return;

            message msg = new message(recipient, content);
            String[] options = {"Send", "Disregard", "Store"};
            int action = JOptionPane.showOptionDialog(null,
                    "Choose an action for the message:",
                    "Message Options",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            String result;
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

            JOptionPane.showMessageDialog(null,
                    "Message ID: " + msg.getMessageId() + "\n" +
                    "Recipient: " + msg.getRecipient() + "\n" +
                    "Message: " + msg.getContent() + "\n" +
                    "Hash: " + msg.getHash() + "\n" +
                    result);
        }

        JOptionPane.showMessageDialog(null, "Total messages sent: " + message.returnTotalMessages());
    }

    private static void showStatisticsMenu() {
        String choice = JOptionPane.showInputDialog(
                "=== Message Statistics ===\n" +
                "1. Display senders and recipients\n" +
                "2. Find longest message\n" +
                "3. Back to main menu\n" +
                "Enter your choice:");

        if (choice == null) return;

        switch (choice.trim()) {
            case "1":
                JOptionPane.showMessageDialog(null, message.displaySendersAndRecipients());
                break;
            case "2":
                JOptionPane.showMessageDialog(null, message.findLongestMessage());
                break;
            case "3":
                return;
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice.");
        }
    }

    private static void showSearchMenu() {
        String choice = JOptionPane.showInputDialog(
                "=== Search Messages ===\n" +
                "1. Search by message ID\n" +
                "2. Search by recipient\n" +
                "3. Back to main menu\n" +
                "Enter your choice:");

        if (choice == null) return;

        switch (choice.trim()) {
            case "1": {
                String id = JOptionPane.showInputDialog("Enter message ID:");
                if (id != null) {
                    JOptionPane.showMessageDialog(null, message.searchByMessageId(id));
                }
                break;
            }
            case "2": {
                String recipient = JOptionPane.showInputDialog("Enter recipient:");
                if (recipient != null) {
                    JOptionPane.showMessageDialog(null, message.searchByRecipient(recipient));
                }
                break;
            }
            case "3":
                return;
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice.");
        }
    }
}  