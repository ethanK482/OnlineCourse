/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

public class Course {
    private int id, sellerId;
    private String title, description;
    
    public Course(int id, int sellerId, String title, String description) {
        this.id = id;
        this.sellerId = sellerId;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
