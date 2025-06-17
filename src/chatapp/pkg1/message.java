package chatapp.pkg1;

import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.*;

public class message {
    // Static list to hold all sent messages in memory
private static  final List<message> messages = new ArrayList<>();

// Static variable to track the total number of sent messages
private static int messageCount = 0;

    private final String recipient;
    private final String content;
    private final String messageId;
    private final String hash;

    // Constructor
    public message(String recipient, String content) {
        this.recipient = recipient;
        this.content = content;
        this.messageId = generateMessageId();
        this.hash = generateHash(content);
    }

    // Send or store a message
    public static String sendMessage(message msg, String action) {
        switch (action.toLowerCase()) {
            case "send":
            case "store":
                messages.add(msg);
                messageCount++;
                return "Message " + action + "ed successfully.";
            case "disregard":
                return "Message disregarded.";
            default:
                return "Invalid action.";
        }
    }

    // Return all messages
    public static String printMessages() {
        if (messages.isEmpty()) return "No messages found.";
        StringBuilder sb = new StringBuilder("All Messages:\n\n");
        for (message msg : messages) {
            sb.append("To: ").append(msg.recipient)
              .append(" | ID: ").append(msg.messageId)
              .append(" | Msg: ").append(msg.content)
              .append(" | Hash: ").append(msg.hash)
              .append("\n");
        }
        return sb.toString();
    }

    // Delete message by hash
    public static String deleteByHash(String hash) {
        Iterator<message> iterator = messages.iterator();
        while (iterator.hasNext()) {
            message msg = iterator.next();
            if (msg.hash.equals(hash)) {
                iterator.remove();
                return "Message deleted successfully.";
            }
        }
        return "No message found with that hash.";
    }

    public static String generateReport() {
        return "Message Report:\nTotal messages: " + messageCount;
    }

    public static int returnTotalMessages() {
        return messageCount;
    }

    public static String displaySendersAndRecipients() {
        Set<String> recipients = new HashSet<>();
        for (message msg : messages) {
            recipients.add(msg.recipient);
        }
        return "Recipients:\n" + String.join("\n", recipients);
    }

    public static String findLongestMessage() {
        message longest = null;
        for (message msg : messages) {
            if (longest == null || msg.content.length() > longest.content.length()) {
                longest = msg;
            }
        }
        return (longest != null)
                ? "Longest Message:\n" + longest.content + "\nTo: " + longest.recipient
                : "No messages available.";
    }

    public static String searchByMessageId(String id) {
        for (message msg : messages) {
            if (msg.messageId.equals(id)) {
                return "Found:\nTo: " + msg.recipient + "\nMessage: " + msg.content + "\nHash: " + msg.hash;
            }
        }
        return "Message not found.";
    }

    public static String searchByRecipient(String name) {
        StringBuilder result = new StringBuilder();
        for (message msg : messages) {
            if (msg.recipient.equalsIgnoreCase(name)) {
                result.append("ID: ").append(msg.messageId)
                      .append(" | Msg: ").append(msg.content)
                      .append(" | Hash: ").append(msg.hash)
                      .append("\n");
            }
        }
        return result.length() > 0 ? result.toString() : "No messages found for recipient.";
    }
    
    public static void displayMessageReport() {
        if (messages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages have been sent yet.");
            return;
        }

        StringBuilder report = new StringBuilder();
        report.append("=== Message Report ===\n\n");
        report.append(String.format("%-20s %-15s %s\n", "Message Hash", "Recipient", "Message"));
        report.append("--------------------------------------------------\n");

        for (message msg : messages) {
            String shortHash = msg.getHash().length() > 8 ?
                msg.getHash().substring(0, 8) + "..." :
                msg.getHash();

            String shortContent = msg.getContent().length() > 30 ?
                msg.getContent().substring(0, 30) + "..." :
                msg.getContent();

            report.append(String.format("%-20s %-15s %s\n",
                shortHash,
                msg.getRecipient(),
                shortContent));
        }

        JTextArea textArea = new JTextArea(report.toString(), 20, 60);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(null, scrollPane, "Message Report", JOptionPane.INFORMATION_MESSAGE);
    }


    // Getters
    public String getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getHash() {
        return hash;
    }

    // Private helpers
    private String generateMessageId() {
        return "MSG" + System.currentTimeMillis();
    }

    private String generateHash(String content) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(content.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return "HASH_ERROR";
        }
    }
}