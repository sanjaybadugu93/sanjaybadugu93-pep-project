package Service;

import DAO.AccountDAO;
import Model.Account;

import java.util.List;

/**
 * The purpose of a Service class is to contain "business logic" that sits between the web layer (controller) and
 * persistence layer (DAO). That means that the Service class performs tasks that aren't done through the web or
 * SQL: programming tasks like checking that the input is valid, conducting additional security checks, or saving the
 * actions undertaken by the API to a logging file.
 *
 * It's perfectly normal to have Service methods that only contain a single line that calls a DAO method. An
 * application that follows best practices will often have unnecessary code, but this makes the code more
 * readable and maintainable in the long run!
 */
public class AccountService {
    public  AccountDAO AccountDAO;

    /**
     * No-args constructor for bookService which creates a BookDAO.
     * There is no need to change this constructor.
     */
    public AccountService(){
        AccountDAO = new AccountDAO();
    }
    /**
     * Constructor for a BookService when a BookDAO is provided.
     * This is used for when a mock BookDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of BookService independently of BookDAO.
     * There is no need to modify this constructor.
     * 
     */
    public AccountService(AccountDAO AccountDAO){
        this.AccountDAO = AccountDAO;
    }
    /**
     *
     *
     */
    // public List<Book> getAllBooks() {
    //     return bookDAO.getAllBooks();
    // }
    // /**
    //  
    //  * An ISBN will be provided in Book. Method should check if the book ISBN already exists before it attempts to
    //  * persist it.
    //  * @param book a book object.
    //  * @return book if it was successfully persisted, null if it was not successfully persisted (eg if the book primary
    //  * key was already in use.)
    //  */
    
     public Account getUserByUserName(Account acc)
      {
        if(AccountDAO.isUsernameAvailable(acc)){
            return null;
        }
        
        return AccountDAO.getAccountByUsername(acc);
      }


     public Account addAccount(Account acc) {
        if(acc==null)
        {
            return null;
        }
         return AccountDAO.addAccount(acc);
     }
    // public Account getUserByUserName(Account acc, String username) {
    //     return AccountDAO.getAccountByUsername(username);
    // }

  
    
    //  */
   

}
