/**
 DECLARATION LIST
 inbox - saves all received messages
 outbox  - saves all sent messages
 inboxCount - Traces the number of messages in the inbox
 outboxCount - Traces the number of messages in the outbox
 firstName - the first name entered at registration
 lastName  - the last name entered at registration
 username  - the username entered at registration
 password - the password entered at registration
 cellPhone  - the cell phone entered at registration
 loginUser  - the username entered at login
 loginPass - the password entered at login
 loggedIn  - used as a flag to show if login was successful
 messages  - Array of hard coded messages for the chat system
 choice   - keeps the user's menu selection
 scanner - Reads user input from the console
 user  - Login object representing the registered user
 label  - Label used when printing inbox or outbox heading
 count - Number of messages to display in printMessages()
 genderChoice - Stores the user's gender selection
 */

import java.util.Scanner;

public class Main {

    // Arrays to store messages
    static Messages[] inbox     = new Messages[10];
    static Messages[] outbox    = new Messages[10];
    static int inboxCount       = 0;
    static int outboxCount      = 0;

    // send message - marks message as sent, adds to outbox
    public static String sendMessage(Messages msg) {
        if (!msg.checkMessageLength()) {
            return "Message is over 10 words in length. Please reduce the message length.";
        }
        if (outboxCount < outbox.length) {
            msg.setMessageSent(true);
            outbox[outboxCount] = msg;
            outboxCount++;
            return "Message successfully sent to " + msg.getRecipient() + " (" + msg.getRecipientPhone() + ").";
        }
        return "Outbox is full. Cannot send more messages.";
    }

    // receiveMessage - marks message as received, adds to inbox
    public static String receiveMessage(Messages msg) {
        if (inboxCount < inbox.length) {
            msg.setMessageReceived(true);
            inbox[inboxCount] = msg;
            inboxCount++;
            return "Message received from " + msg.getSender() + ".";
        }
        return "Inbox is full.";
    }

    // mark message as read - finds message by ID, marks as read
    public static String markMessageAsRead(int messageId) {
        for (int i = 0; i < inboxCount; i++) {
            if (inbox[i].getMessageId() == messageId) {
                inbox[i].setMessageRead(true);
                return "Message #" + messageId + " marked as read.";
            }
        }
        return "Message ID " + messageId + " not found in inbox.";
    }

    // print messages - prints all messages in a given array
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

        // Welcome message
        System.out.println("Get familiar with Webchat!"); 
        
        // Gender selection
        System.out.println("Are you Male or Female?");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Other");
        System.out.print("Enter your choice: ");

        String genderChoice = scanner.nextLine().trim();

        if (genderChoice.equals("1")) {
            System.out.println("-Handsome men use Webchat.... HI HANDSOME! Welcome to Webchat");
        } else if (genderChoice.equals("2")) {
            System.out.println("-Beautiful women use Webchat.... HI BEAUTIFUL! Welcome to Webchat");
        } else {
            System.out.println(" Welcome to Webchat!");
        }

        System.out.println();

        // REGISTRATION
        System.out.println("REGISTRATION");
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
            System.out.println("Registration failed. Please restart and try again.");
            scanner.close();
            return;
        }

        // LOGIN
        System.out.println("LOGIN");
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

        // MESSAGING - hardcoded messages
        Messages[] messages = {
            new Messages(1, "mickayla",  "ameera",    "+27821234567", "Hey ameera are you coming tonight?",      "2025-03-28 08:00"),
            new Messages(2, "ameera",    "mickayla",  "+27839876543", "Yes I will be there at 7pm.",             "2025-03-28 08:05"),
            new Messages(3, "mickayla",  "sbahle",    "+27761112233", "Sbahle please bring snacks.",             "2025-03-28 08:10"),
            new Messages(4, "kumbukani", "mickayla",  "+27839876543", "Mickayla what time does it start?",       "2025-03-28 08:15"),
            new Messages(5, "mickayla",  "kumbukani", "+27724445566", "It starts at 7 see you then kumbukani.", "2025-03-28 08:20"),
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
            System.out.println("MENU");
            System.out.println("1. View Inbox");
            System.out.println("2. View Outbox");
            System.out.println("3. Mark a message as read");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

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
                    case 4: System.out.println("goodbye, see you next time"); break;
                    default: System.out.println("Invalid option. Please choose 1-4.");
                }
            } else {
                System.out.println("Please enter a number.");
                scanner.next();
            }
        }

        scanner.close();
    }
}
