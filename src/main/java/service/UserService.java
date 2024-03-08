package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Account;
import model.User;
import util.ConnectDB;

public class UserService {

    static Connection c = null;
    static PreparedStatement pre = null;
    static ResultSet rs = null;

    private static Account createAccount(String email, String password) {

        String createAccountQuery = "insert into account( email, password )"
                + "values(?, ?)"
                + "select * from account where id=  SCOPE_IDENTITY()";
        try {
            c = ConnectDB.getConnection();
            pre = c.prepareStatement(createAccountQuery);
            pre.setString(1, email);
            pre.setString(2, password);
            rs = pre.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4) == 1);
                return account;
            }
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean findAccountByEmail(String email) {
        String createUserQuery = "select * from account where email = ? ";
        try {
            c = ConnectDB.getConnection();
            pre = c.prepareStatement(createUserQuery);
            pre.setString(1, email);
            rs = pre.executeQuery();
            boolean isFound = rs.next();
            c.close();
            return isFound;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private static User createUser(String firstName, String lastName, int accountId) {
        String createUserQuery = "insert into [user]( accountID, firstName,lastName,balance,role)"
                + "values(?, ?, ?, ?, ?)"
                + "select * from [user] where uid=  SCOPE_IDENTITY()";
        try {
            c = ConnectDB.getConnection();
            pre = c.prepareStatement(createUserQuery);
            pre.setInt(1, accountId);
            pre.setString(2, firstName);
            pre.setString(3, lastName);
            pre.setDouble(4, 0);
            pre.setString(5, "user");
            rs = pre.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getString(6));
                return user;
            }
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateVerifyStatus(String email) {
        String updateVerifyStatusQuery = "UPDATE account SET isVerifyEmail = ? WHERE email = ?";
        try {
            c = ConnectDB.getConnection();
            pre = c.prepareStatement(updateVerifyStatusQuery);
            pre.setInt(1, 1);
            pre.setString(2, email);
            int rowChange = pre.executeUpdate();
            c.close();
            return rowChange > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean register(String email, String password, String firstName, String lastName) {
        Account account = createAccount(email, password);
        User user = createUser(firstName, lastName, account.getId());
        return user != null;
    }

    public static ArrayList login(String email, String password) {
        String findUserQuery = "select * from account where email = ? and password=? ";
        try {
            c = ConnectDB.getConnection();
            pre = c.prepareStatement(findUserQuery);
            pre.setString(1, email);
            pre.setString(2, password);
            rs = pre.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4) == 1);
                User user = getUserInfo(account.getId());
                if (user != null) {
                    ArrayList userInfo = new ArrayList();
                    userInfo.add(account);
                    userInfo.add(user);
                    return userInfo;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static User getUserById(int uid) {
        String getUserInfoQuery = "select * from [user] where uid = ? ";
        try {
            c = ConnectDB.getConnection();
            pre = c.prepareStatement(getUserInfoQuery);
            pre.setInt(1, uid);
            rs = pre.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getString(6));
                return user;
            }
            c.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }
    public static User getUserInfo(int accountID) {
        String getUserInfoQuery = "select * from [user] where accountID = ? ";
        try {
            c = ConnectDB.getConnection();
            pre = c.prepareStatement(getUserInfoQuery);
            pre.setInt(1, accountID);
            rs = pre.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getString(6));
                return user;
            }
            c.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }

    public static void main(String[] args) {
        System.out.println(getUserInfo(15));
    }

}
