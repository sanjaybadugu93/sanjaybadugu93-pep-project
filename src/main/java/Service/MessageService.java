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
   
    
    public Message createMessage(Message message) 
    {
         if(MessageDAO.getMessagesByMessageId(1)!=null)
         {
            return MessageDAO.createMessage(message);
         }
        return null;
        // return createMessage(message);
    }
   

    public Message updateByMessageId(int message_id,Message msg)
     {
        if(MessageDAO.getMessagesByMessageId(message_id)!=null){
            MessageDAO.updateMessageById(message_id, msg);
        return MessageDAO.getMessagesByMessageId(message_id);
        }
        return null;
    }

    

    public Message deleteMessageByMessageId(int message_id)
    {
        return MessageDAO.deleteBymessageId(message_id);
    }


    public List<Message> getAllMessagesByUserId(int message_id)
    {
        return MessageDAO.getAllMessagesByUserId(message_id);
    }

}
