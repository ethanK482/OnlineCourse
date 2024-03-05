/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

public class User {
    private int uId, accountId;
    private String firstName, lastName;
    private double balance;
    private String role;
    
    public User(int uId) {
        this.uId = uId;
        this.accountId = 0;
        this.firstName = null;
        this.lastName = null;
        this.balance = 0;
        this.role = null;
    }
    
    public User(int uId, int accountId, String firstName, String lastName, double balance, String role) {
        this.uId = uId;
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.role = role;
    }

    public int getuId() {
        return uId;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getBalance() {
        return balance;
    }

    public String getRole() {
        return role;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "uId=" + uId + ", accountId=" + accountId + ", firstName=" + firstName + ", lastName=" + lastName + ", balance=" + balance + ", role=" + role + '}';
    }
    
    
}
