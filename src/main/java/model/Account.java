/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

public class Account {
    private int id;
    private String email, password;
    private boolean isVerifyEmail;

    public Account(int id, String email, String password, boolean isVerifyEmail) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.isVerifyEmail = isVerifyEmail;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isIsVerifyEmail() {
        return isVerifyEmail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsVerifyEmail(boolean isVerifyEmail) {
        this.isVerifyEmail = isVerifyEmail;
    }
    
    
}
