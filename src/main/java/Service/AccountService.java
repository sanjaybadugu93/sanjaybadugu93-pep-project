package Service;

import DAO.AccountDAO;
import Model.Account;




public class AccountService {
    public  AccountDAO AccountDAO;

    
    public AccountService(){
        AccountDAO = new AccountDAO();
    }
    
     
    public AccountService(AccountDAO AccountDAO){
        this.AccountDAO = AccountDAO;
    }
    
    
     public Account getUserByUserName(String string)
      {
        if(string==null)
        {
            return null;
        }
        
        return AccountDAO.getAccountByUsername(string);
      }


     public Account createAccount(Account acc) {
        if(acc==null)
        {
            return null;
        }
         return AccountDAO.createAccount(acc);
     }
    
   

}
