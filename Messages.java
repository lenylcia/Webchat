/**
 DECLARATION LIST
 message Id - Unique identifier for each message
 sender - The name of the person sending the message
 recipient - The name of the person receiving the message
 recipientPhone - The phone number of the recipient
 messagePayload - The actual text content of the message
 timestamp - The date and time the message was sent
 messageSent - Used a flag verify if the message has been sent
 messageReceived - Used as a Flag to verify if the message has been received
 messageRead  - Used as a Flag to verify if the message has been read
 words  - Array used to split and count words in the message
 */
 
public class Messages {

    //Instance variables
    private int     messageId;
    private String  sender;
    private String  recipient;
    private String  recipientPhone;
    private String  messagePayload;
    private String  timestamp;
    private boolean messageSent;
    private boolean messageReceived;
    private boolean messageRead;

    //Constructor
    public Messages(int messageId, String sender, String recipient, String recipientPhone, String messagePayload, String timestamp) {
        this.messageId       = messageId;
        this.sender          = sender;
        this.recipient       = recipient;
        this.recipientPhone  = recipientPhone;
        this.messagePayload  = messagePayload;
        this.timestamp       = timestamp;
        this.messageSent     = false;
        this.messageReceived = false;
        this.messageRead     = false;
    }

    //Getters
    public int     getMessageId()      { return messageId; }
    public String  getSender()         { return sender; }
    public String  getRecipient()      { return recipient; }
    public String  getRecipientPhone() { return recipientPhone; }
    public String  getMessagePayload() { return messagePayload; }
    public String  getTimestamp()      { return timestamp; }
    public boolean isMessageSent()     { return messageSent; }
    public boolean isMessageReceived() { return messageReceived; }
    public boolean isMessageRead()     { return messageRead; }

    // Setters
    public void setMessageSent(boolean sent)          { this.messageSent = sent; }
    public void setMessageReceived(boolean received)  { this.messageReceived = received; }
    public void setMessageRead(boolean read)          { this.messageRead = read; }

    // check message length - message must be no more than 10 words
    public boolean checkMessageLength() {
        String[] words = messagePayload.trim().split("\\s+");
        return words.length <= 10;
    }

    // print message payload - returns full message details as a string
    public String printMessagePayload() {
        return "--------------------------------------------\n"
             + "  Message ID   : " + messageId + "\n"
             + "  From         : " + sender + "\n"
             + "  To           : " + recipient + " (" + recipientPhone + ")\n"
             + "  Message      : " + messagePayload + "\n"
             + "  Timestamp    : " + timestamp + "\n"
             + "  Sent         : " + (messageSent     ? "Yes" : "No") + "\n"
             + "  Received     : " + (messageReceived ? "Yes" : "No") + "\n"
             + "  Read         : " + (messageRead     ? "Yes" : "No") + "\n"
             + "--------------------------------------------";
    }
}
