package Controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.MessageDAO;
import Model.Message;
import Model.Account;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        // app.get("example-endpoint", this::exampleHandler);
        app.post("/register",this::newUserHandler);
        app.post("/login", this::UserLoginHandler);
        app.post("/messages",this::newMessageHandler);
        app.get("/messages",this::getAllMessagesHandler);
        app.get("/messages/{message_id}",this::getMessagesByMessageId);
        app.delete("/messages/{message_id}",this::deleteMessageByMessageId);
        app.patch("/messages/{message_id}",this::updateByMessageId);
        app.get("/accounts/{account_id}/messages",this::getAllMessagesByUserIdHandler);
        return app;
    }
    AccountService AccountService;
    MessageService MessageService;
    private Object username;

    public SocialMediaController(){
        this.AccountService=new AccountService();
        this.MessageService=new MessageService();
       }
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    
    private void newUserHandler(Context ctx) throws JsonMappingException, JsonProcessingException
     {
      ObjectMapper om=new ObjectMapper();
      Account acc=om.readValue(ctx.body(),Account.class);
      System.out.println(acc); 
      if(acc.getUsername().isEmpty()){
           ctx.status(400);
           return;
         }
      else if(acc.getPassword().length() <4){
           ctx.status(400);
           return;
         }else if (AccountService.getUserByUserName(acc)!=null)
         {
           ctx.status(400);
           return;
         }
        Account addedUser=AccountService.addAccount(acc);
        ctx.json(om.writeValueAsString(addedUser));
        ctx.status(200); //OK
        System.out.println((addedUser));
    }


    private void UserLoginHandler(Context ctx) throws JsonMappingException, JsonProcessingException
    {
        ObjectMapper om=new ObjectMapper();
        Account acc=om.readValue(ctx.body(),Account.class);
        Account verifyUser=AccountService.getUserByUserName(acc);           
         if(verifyUser==null)
        {
          ctx.status(401);
          return;
        }
        else if(!verifyUser.getPassword().contains(acc.getPassword())){
            ctx.status(401);
            return;
        }
        else{
            ctx.status(200);
            ctx.json(om.writeValueAsString(verifyUser));
            return;
        }
    }
        

    private void newMessageHandler(Context ctx) throws JsonMappingException, JsonProcessingException{
        ObjectMapper om=new ObjectMapper();
        Message msg=om.readValue(ctx.body(),Message.class);
        MessageDAO md=new MessageDAO();
        if(md.isExist(msg.posted_by) && !msg.message_text.isBlank() && msg.message_text.length()< 255)
        {
            Message addedMsg=MessageService.addMessage(msg);
            ctx.json(addedMsg);
            ctx.status(200);
        }
        else
        {
           ctx.status(400);
           return;
        }
    }


    private void getAllMessagesHandler(Context ctx)
    {
        List<Message> msg=new ArrayList<Message>();
        msg.addAll(MessageService.getAllMessages());
        ctx.json(msg);
        ctx.status(200);
        System.out.println(msg);
    }


    private void getMessagesByMessageId(Context ctx) throws JsonMappingException, JsonProcessingException{
        int msg_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message msg2=MessageService.getmessageByMessageId(msg_id);
        if(msg2==null)
        {
                  ctx.status(200);//OK
                  return;
        }else
        ctx.json(msg2);
        ctx.status(200);//OK
       
    }


    private void deleteMessageByMessageId(Context ctx) 
    {
        int messageId=ctx.pathParamAsClass("message_id", Integer.class).get();
        Message to_be_deleted_message=MessageService.deleteMessageByMessageId(messageId);
        if(to_be_deleted_message !=null)
        {
            ctx.status(400);
            ctx.json(to_be_deleted_message);
        }
        else
        {
            ctx.status(400);
        }
        ctx.status(200);
    }
  

    private void updateByMessageId(Context ctx) throws JsonMappingException, JsonProcessingException
    {
        ObjectMapper om=new ObjectMapper();
        Message msg=om.readValue(ctx.body(),Message.class);
        int msg_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message updatedMessage=MessageService.updateByMessageId(msg_id,msg);
        System.out.println(updatedMessage);
        if(updatedMessage==null)
        {
          ctx.status(400);
        }else if(msg.message_text.length()>255){
          ctx.status(400);
        }else if(msg.message_text.isBlank()){
          ctx.status(400);
        }
        else
        {
          ctx.json(om.writeValueAsString(updatedMessage));
        ctx.status(200);//OK
        }
    }

    private void getAllMessagesByUserIdHandler(Context ctx) throws JsonMappingException, JsonProcessingException
    {
        List<Message> messages=new ArrayList<>();
        int account_id = Integer.parseInt(ctx.pathParam("account_id"));
        messages.addAll(MessageService.getAllMessagesByUserId(account_id));
        ctx.status(200);
        ctx.json(messages);
        System.out.println(messages);
    }
      
    
}





