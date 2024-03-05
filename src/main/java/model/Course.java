/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.util.ArrayList;

public class Course {
    private int id;
    private User seller;
    private String title, description;
    private ArrayList<Category> categories = new ArrayList<>();
    
    public Course(int id, User seller, String title, String description) {
        this.id = id;
        this.seller = seller;
        this.title = title;
        this.description = description;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
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
