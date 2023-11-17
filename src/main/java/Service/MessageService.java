package Service;

import DAO.AccountDAO;
import DAO.MessageDAO;
// import Model.Account;
import Model.Message;

import java.util.List;



public class MessageService {
    public  MessageDAO MessageDAO;

    /**
     * No-args constructor for bookService which creates a BookDAO.
     * There is no need to change this constructor.
     */
    public MessageService(){
        MessageDAO = new MessageDAO();
    }
    /**
     * Constructor for a BookService when a BookDAO is provided.
     * This is used for when a mock BookDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of BookService independently of BookDAO.
     * There is no need to modify this constructor.
     * @param bookDAO
     */
    public MessageService(MessageDAO MessageDAO){
        this.MessageDAO = MessageDAO;
    }
    
    public List<Message> getAllMessages()
    {
        return MessageDAO.getAllMessages();
    }


    public Message getmessageByMessageId(int message_id)
    {
        return MessageDAO.getMessagesByMessageId(message_id);
    }
   
    
    public Message addMessage(Message message) 
    {
        if(MessageDAO.getMessagesByMessageId(0)!=null)
        {
           return MessageDAO.addMessage(message);
        }
        return null;
    }
   

    public Message updateByMessageId(int message_id,Message msg)
     {
        Message existingMessage = MessageDAO.getMessagesByMessageId(message_id);

        // Check if the message exists
        if (existingMessage == null) {
            // Message not found
            return null;  // updatemessagemessagenotfound
        }
       return  Message.updateByMessageId(message_id,msg);
    }

    //     Message updatedMessage;
    //     // Check if the updated message text is not blank and not too long
    //     String newMessageText = updatedMessage.getMessage_text();
    //     if (newMessageText == null || newMessageText.trim().isEmpty()) {
    //         return null;  // updatemessagemessagestringempty
    //     }

    //     if (newMessageText.length() > 255) {
    //         return null;  // updatemessagemessagetoolong
    //     }

    //    // Update the message text
    //    existingMessage.setMessage_text(newMessageText);

    //    // Update the message in the database
    //    boolean updateSuccessful = MessageDAO.updateByMessageId(existingMessage);

    //    if (updateSuccessful) {
    //        return existingMessage;  // updatemessagesuccessful
    //    } else {
    //        return null;  // Handle update failure if necessary
    //    }
        // return ((DAO.MessageDAO) MessageDAO).updateByMessageId(,message_id,msg);
    //     if(message_id==0){
    //         return null;
    //     }
    //    return MessageDAO.updateByMessageId(message_id,msg);
      // return msg;
    


    public Message deleteMessageByMessageId(int message_id)
    {
        return MessageDAO.deleteMessageByMessageId(message_id);
    }


    public List<Message> getAllMessagesByUserId(int message_id)
    {
        return MessageDAO.getAllMessagesByUserId(message_id);
    }

}
