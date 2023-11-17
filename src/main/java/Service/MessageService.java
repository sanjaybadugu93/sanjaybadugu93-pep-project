package Service;

import DAO.MessageDAO;

import Model.Message;

import java.util.List;



public class MessageService {
    public  MessageDAO MessageDAO;

    public MessageService(){
        MessageDAO = new MessageDAO();
    }
    
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
