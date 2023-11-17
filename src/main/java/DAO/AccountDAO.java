package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
 import java.util.List;

import Model.Account;
 import Model.Message;
 import Util.ConnectionUtil;

 public class AccountDAO {  
     public Account addAccount(Account account){
         Connection con=ConnectionUtil.getConnection();
     try
     {
         String sql="INSERT INTO account (username, password) VALUES (?,?)";
         PreparedStatement ps=con.prepareStatement (sql, Statement. RETURN_GENERATED_KEYS); 
          ps.setString(1, account.getUsername());
          ps.setString(2, account.getPassword());
          ps.executeUpdate();
          ResultSet keyrs=ps.getGeneratedKeys();
         if (keyrs.next())
         {
             int generated_account_key= (int) keyrs.getInt("account_id");
             return new Account (generated_account_key, account.getUsername(), account.getPassword());
         }
     }
     catch(SQLException e)
     {
         System.out.println(e.getMessage());
     }
     return null;   
 }
  //verify user login
     public Account verifyAccount (Account account)
     {
             Connection con=ConnectionUtil.getConnection(); 
         try
         {
              String sql="select * from Account where username=?";
             PreparedStatement ps=con.prepareStatement(sql);
             ps.setString(1, account.getUsername());
             ResultSet rs=ps.executeQuery();
            while(rs.next())
             {
                 return new Account(rs.getInt("account_id"),rs.getString("username"),rs.getString("password"));
             }
            }
         catch(SQLException e)
       {
             System.out.println(e.getMessage());
         }
         return null;
     }
  //retrive account by username
  public Account getAccountByUsername(Account acc) { 
     Connection con=ConnectionUtil.getConnection();
     try
     {
         String sql="select * from Account where username=(?)"; 
         PreparedStatement ps=con.prepareStatement (sql); 
         ps.setString(1, Account.acc);
         ResultSet rs=ps.executeQuery();
         while(rs.next())
         {
         return new Account (rs.getInt("account_id"), rs.getString("username"),rs.getString("password"));
         } 
     }
     catch (Exception e) 
     {
          System.out.println(e.getMessage());
     }
     return null;
     }
}     
     //retrive account by password
     //  public Account getAccountByPassword(String name) { 
     // Connection con=ConnectionUtil.getConnection();
//     // try
//     // {
//     //     String sql="select * from Account where Password=(?)"; 
//     //     PreparedStatement ps=con.prepareStatement (sql); 
//     //     ps.setString(1, name);
//     //     ResultSet rs=ps.executeQuery();
//     //     while(rs.next())
//     //     {
//     //     return new Account (rs.getInt("account_id"), rs.getString("username"),rs.getString("password"));
//     //     } 
//     // }
//     // catch (Exception e) 
//     // {
//     //      System.out.println(e.getMessage());
//     // }
//     // return null;
 
 
// public class AccountDAO {
//     // Existing methods for accountExists, createAccount, and getAccountByUsername...

//     public  boolean createAccount(String username, String password) {
//         Connection connection = ConnectionUtil.getConnection();
//         try {
//             String sql = "INSERT INTO account(username, password) VALUES (?, ?)";
//             try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                 preparedStatement.setString(1, username);
//                 preparedStatement.setString(2, password);
                
//                 int affectedRows = preparedStatement.executeUpdate();
//                 return affectedRows > 0;
//             }
//         } catch (SQLException e) {
//             // Log or handle the exception according to your application's requirements
//             e.printStackTrace();
//         } finally {
//             ConnectionUtil.closeConnection(connection);
//         }
//         return false;
//     }

//     public boolean validateLogin(String username, String password) {
//         Connection connection = ConnectionUtil.getConnection();
//         try {
//             String sql = "SELECT COUNT(*) FROM account WHERE username=? AND password=?";
//             try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                 preparedStatement.setString(1, username);
//                 preparedStatement.setString(2, password);
//                 ResultSet rs = preparedStatement.executeQuery();

//                 if (rs.next()) {
//                     int count = rs.getInt(1);
//                     return count > 0;
//                 }
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         } finally {
//             ConnectionUtil.closeConnection(connection);
//         }
//         return false;
//     }

//     public static boolean isUsernameAvailable(String username) {
//         Connection connection = ConnectionUtil.getConnection();
//         try {
//             String sql = "SELECT COUNT(*) FROM account WHERE username=?";
//             try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                 preparedStatement.setString(1, username);
//                 ResultSet rs = preparedStatement.executeQuery();

//                 if (rs.next()) {
//                     int count = rs.getInt(1);
//                     return count == 0; // If count is 0, the username is available
//                 }
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         } finally {
//             ConnectionUtil.closeConnection(connection);
//         }
//         return false;
//     }

//     public  Account getAccountByUsername(Account acc) {
//         try (Connection connection = ConnectionUtil.getConnection()) {
//             String sql = "SELECT * FROM account WHERE username=?";
//             try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                 preparedStatement.setString(1, Account.getUsername());
//                 try (ResultSet rs = preparedStatement.executeQuery()) {
//                     if (rs.next()) {
//                         int accountId = rs.getInt("account_id");
//                         String password = rs.getString("password");
//                         return new Account(accountId, acc, password);
//                     }
//                 }
//             }
//         } catch (SQLException e) {
//             // Log or handle the exception according to your application's requirements
//             e.printStackTrace();
//         }
//         return null;
//     }
//     public static boolean accountExists(int accountId) {
//         Connection connection = ConnectionUtil.getConnection();
//         try {
//             String sql = "SELECT COUNT(*) FROM account WHERE account_id=?";
//             try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                 preparedStatement.setInt(1, accountId);
//                 ResultSet rs = preparedStatement.executeQuery();

//                 if (rs.next()) {
//                     int count = rs.getInt(1);
//                     return count > 0;
//                 }
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         } finally {
//             ConnectionUtil.closeConnection(connection);
//         }
//         return false;
//     }

//     public Account addAccount(Account acc) {
//         return null;
//     }


