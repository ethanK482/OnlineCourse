/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Category;
import model.Course;
import util.ConnectDB;

public class CourseService {
    
    public static ArrayList<Course> getCourse() {
        String sql = "select * from course";
        ArrayList<Course> courses = new ArrayList<>();
        
        try {
            Connection database = ConnectDB.getConnection();
            
            PreparedStatement stmt = database.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            
            while (result.next()) {
                courses.add(new Course(
                        Integer.parseInt(result.getString(1)),
                        Integer.parseInt(result.getString(2)),
                        result.getString(3),
                        result.getString(4)
                ));
            }
            
            stmt.close();
            database.close();
        } catch (Exception e) { System.err.println(e); }
        
        return courses;
    }
    
    public static ArrayList<Category> getCategory(String courseId) {
        String sql = "select * from courseCategory join category on courseCategory.categoryId = category.id where courseId = " + courseId;
        ArrayList<Category> categories = new ArrayList<>();
        
        try {
            Connection database = ConnectDB.getConnection();
            
            PreparedStatement stmt = database.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            
            while (result.next()) {
                categories.add(new Category(
                        Integer.parseInt(result.getString(3)),
                        result.getString(4)
                ));
            }
            
            stmt.close();
            database.close();
        } catch (Exception e) { System.err.println(e); }
        
        return categories;
    }
    
    public static void main(String[] args) {
        try {
            ArrayList<Category> categories = getCategory("2");
            for (int i = 0; i < categories.size(); ++i) System.out.println(categories.get(i).getName());
        } catch (Exception e) { System.out.println(e); }
    }
}
