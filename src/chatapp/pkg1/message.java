package chatapp.pkg1;

import java.util.*;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.io.FileWriter;
import java.io.IOException;

public class message {
    // Static list to hold all sent messages in memory
private static List<message> sentMessages = new ArrayList<>();

// Static variable to track the total number of sent messages
private static int totalMessages = 0;

// Instance variables for each message
private String messageId;
private String recipient;
private String content;
private String hash;

 //Constructor initializes a new message with recipient and content.
 public message(String recipient, String content) {
    this.messageId = UUID.randomUUID().toString().substring(0, 10); // Generate a unique 10-char ID
    this.recipient = recipient;
    this.content = content;
    this.hash = createMessageHash(); // Hash the content
}

// Getters for message fields
public String getMessageId() {
    return messageId;
}

public String getRecipient() {
    return recipient;
}

public String getContent() {
    return content;
}

public String getHash() {
    return hash;
}

//Checks if the message ID is valid (≤ 10 characters).
 public boolean checkMessageID() {
    return messageId.length() <= 10;
}

//Validates the recipient's phone number.
 public int checkRecipientCell() {
    if (recipient.length() <= 10 && recipient.matches("^\\+?\\d+$")) {
        return 1;
    }
    return 0;
}

 //Generates a SHA-256 hash for the message content.
 public String createMessageHash() {
    try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(content.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString().substring(0, 16); // Shorten hash for display
    } catch (Exception e) {
        return "HASH_ERROR";
    }
}

 //Handles sending, storing, or discarding a message based on the user's choice.
 public static String sendMessage(message message, String option) {
    switch (option.toLowerCase()) {
        case "send":
            sentMessages.add(message);
            totalMessages++;
            return "Message sent!";
        case "store":
            storeMessage(message);
            return "Message stored.";
        case "disregard":
            return "Message disregarded.";
        default:
            return "Invalid option.";
    }
}

 //Prints all messages that have been sent.
 public static String printMessages() {
    StringBuilder sb = new StringBuilder();
    for (message m : sentMessages) {
        sb.append("To: ").append(m.getRecipient())
          .append(", Content: ").append(m.getContent())
          .append(", Hash: ").append(m.getHash())
          .append("\n");
    }
    return sb.toString();
}

//Returns the total number of messages that have been sent.
 public static int returnTotalMessages() {
    return totalMessages;
}

//Stores a message in a local JSON file.
 public static void storeMessage(message message) {
    String json = "{\n" +
            "  \"messageId\": \"" + message.getMessageId() + "\",\n" +
            "  \"recipient\": \"" + message.getRecipient() + "\",\n" +
            "  \"content\": \"" + message.getContent() + "\",\n" +
            "  \"hash\": \"" + message.getHash() + "\"\n" +
            "}";
    try (FileWriter file = new FileWriter("messages.json", true)) {
        file.write(json + ",\n"); // Appends the message JSON to the file
    } catch (IOException e) {
        e.printStackTrace(); // Prints the error stack trace if file writing fails
    }
}
}