/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.util.ArrayList;

public class Course {
    private int id, sellerId;
    private String title, description;
    private ArrayList<Category> categories = new ArrayList<>();
    
    public Course(int id, int sellerId, String title, String description) {
        this.id = id;
        this.sellerId = sellerId;
        this.title = title;
        this.description = description;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
    
    

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public int getId() {
        return id;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
