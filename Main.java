/**
 DECLARATION LIST
 inbox           - stores all received messages
 outbox          - stores all sent messages
 inboxCount      - Tracks the number of messages in the inbox
 outboxCount     - Tracks the number of messages in the outbox
 firstName       - Stores the user's first name during registration
 lastName        - Stores the user's last name during registration
 username        - Stores the username entered during registration
 password        - Stores the password entered during registration
 cellPhone       - Stores the cell number entered during registration loginUser  
 username        - Stores the username entered during login
 loginPass       - Stores the password entered during login
 loggedIn        - Flag to indicate if login was successful
 messages        - Array of hard coded messages for the chat system
 choice          - Stores the user's menu selection
 scanner         - Reads user input from the console
 user            - Login object representing the registered user
 label           - Label used when printing inbox or outbox heading
 count           - Number of messages to display in printMessages()
 */

import java.util.Scanner;

public class Main {

    // Arrays to store messages
    static Messages[] inbox    = new Messages[10];
    static Message[] outbox   = new Message[10];
    static int inboxCount     = 0;
    static int outboxCount    = 0;

    // sendMessage - marks message as sent, adds to outbox
    public static String sendMessage(Message msg) {
        if (!msg.checkMessageLength()) {
            return "Message exceeds 10 words. Please reduce the message length.";
        }
        if (outboxCount < outbox.length) {
            msg.setMessageSent(true);
            outbox[outboxCount] = msg;
            outboxCount++;
            return "Message successfully sent to " + msg.getRecipient() + " (" + msg.getRecipientPhone() + ").";
        }
        return "Outbox is full. Cannot send more messages.";
    }

    // receiveMessage() - marks message as received, adds to inbox
    public static String receiveMessage(Message msg) {
        if (inboxCount < inbox.length) {
            msg.setMessageReceived(true);
            Messages messages = null;
            inbox[inboxCount] = messages;
            inboxCount++;
            return "Message received from " + msg.getSender() + ".";
        }
        return "Inbox is full.";
    }

    // markMessageAsRead() - finds message by ID, marks as read
    public static String markMessageAsRead(int messageId) {
        for (int i = 0; i < inboxCount; i++) {
            if (inbox[i].getMessageId() == messageId) {
                inbox[i].setMessageRead(true);
                return "Message #" + messageId + " marked as read.";
            }
        }
        return "Message ID " + messageId + " not found in inbox.";
    }

    // printMessages() - prints all messages in a given array
    public static void printMessages(Messages[] messages, int count, String label) {
        System.out.println("\n========== " + label + " (" + count + " messages) ==========");
        if (count == 0) {
            System.out.println("  (empty)");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println(messages[i].printMessagePayload());
            }
        }
    }

    // MAIN METHOD
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //welcome message
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║    Get fimiliar with Webchat!   ║");
        System.out.println("╚══════════════════════════════════════╝\n");
        
        System.out.println("Are you Male or Female?");
        System.out.print("Enter your choice: ");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.print("3. other");

            String genderChoice = scanner.nextLine().trim();

        if (genderChoice.equals("1")) {
        System.out.println("\n -Handsome men use Webchat.... HI HANDSOME! care to stay?");
        
            } else if (genderChoice.equals("2")) {
        System.out.println("\n -Beautiful women use Webchat.... HI BEAUTIFUL! care to have a chat?");
        
            } else if (genderChoice.equals("3")) {
        System.out.println("\n Welcome to Webchat!");
}

System.out.println();


        //REGISTRATION
        System.out.println("=== REGISTRATION ===");
        System.out.print("First Name    : ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name     : ");
        String lastName  = scanner.nextLine();
        System.out.print("Username      : ");
        String username  = scanner.nextLine();
        System.out.print("Password      : ");
        String password  = scanner.nextLine();
        System.out.print("Cell Number   : ");
        String cellPhone = scanner.nextLine();

        Login user = new Login(firstName, lastName, username, password, cellPhone);
        System.out.println("\n" + user.registerUser());

        if (!user.registerUser().startsWith("Username successfully")) {
            System.out.println("\nRegistration failed. Please restart and try again.");
            scanner.close();
            return;
        }

        // LOGIN
        System.out.println("\n=== LOGIN ===");
        System.out.print("Enter username : ");
        String loginUser = scanner.nextLine();
        System.out.print("Enter password : ");
        String loginPass = scanner.nextLine();

        boolean loggedIn = user.loginUser(loginUser, loginPass);
        System.out.println(user.returnLoginStatus(loggedIn));

        if (!loggedIn) {
            System.out.println("Access denied. Exiting.");
            scanner.close();
            return;
        }

        // MESSAGING
        Message[] messages = {
            new Message(1, "mickayla", "ameera",     "+27821234567", "Hey ameera are you coming tonight?",   "2025-03-28 08:00"),
            new Message(2, "ameera",   "mickayla",   "+27839876543", "Yes I will be there at 7pm.",       "2025-03-28 08:05"),
            new Message(3, "mickayla", "sbahle", "+27761112233", "sbahle please bring snacks.",      "2025-03-28 08:10"),
            new Message(4, "kumbukani",  "mickayla",   "+27839876543", "mickayla what time does it start?",    "2025-03-28 08:15"),
            new Message(5, "mickayla", "kumbukani",    "+27724445566", "It starts at 7,see you then kumbukani.", "2025-03-28 08:20"),
        };

        System.out.println("SENDING MESSAGES");
        System.out.println(sendMessage(messages[0]));
        System.out.println(sendMessage(messages[2]));
        System.out.println(sendMessage(messages[4]));

        System.out.println("RECEIVING MESSAGES");
        System.out.println(receiveMessage(messages[1]));
        System.out.println(receiveMessage(messages[3]));

        System.out.println("MARKING AS READ");
        System.out.println(markMessageAsRead(2));

        printMessages(inbox,  inboxCount,  "INBOX");
        printMessages(outbox, outboxCount, "OUTBOX");

        // INTERACTIVE MENU
        int choice = 0;
        while (choice != 4) {
            System.out.println("========== MENU ==========");
            System.out.print("Choose an option: ");
            System.out.println("1. View Inbox");
            System.out.println("2. View Outbox");
            System.out.println("3. Mark a message as read");
            System.out.println("4. Exit");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1: printMessages(inbox,  inboxCount,  "INBOX");  break;
                    case 2: printMessages(outbox, outboxCount, "OUTBOX"); break;
                    case 3:
                        System.out.print("Enter message ID to mark as read: ");
                        if (scanner.hasNextInt()) {
                            System.out.println(markMessageAsRead(scanner.nextInt()));
                        } else {
                            System.out.println("Please enter a valid number.");
                            scanner.next();
                        }
                        break;
                    case 4: System.out.println("Goodbye!"); break;
                    default: System.out.println("Invalid option. Please choose 1-4.");
                }
            } else {
                System.out.println("Please enter a number.");
                scanner.next();
            }
        }

        scanner.close();
    }

    private static void printMessages(Message[] outbox, int outboxCount, String outbox0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
