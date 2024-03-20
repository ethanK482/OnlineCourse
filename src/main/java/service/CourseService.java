package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Category;
import model.Course;
import model.User;
import model.Video;
import util.ConnectDB;

public class CourseService {
    public static boolean checkUserCourse(String courseId, String userId) {
        String sql = "select * from userCourse where courseID = " + courseId + " and \"uid\" = " + userId;
        boolean ok = false;
        
        try {
            Connection database = ConnectDB.getConnection();
            PreparedStatement stmt = database.prepareStatement(sql);
            
            ResultSet result = stmt.executeQuery();
            
            if (result.next()) ok = true;
            
            database.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return ok;
    }
    
    public static void updateCourse(String courseId, String courseTitle, String courseDescription, String courseBannerUrl, String coursePrice) {
        String sql = "update course set title = '" + courseTitle + "', \"description\" = '" + courseDescription + "', banner_url = '" + courseBannerUrl + "', price = " + coursePrice + " where id = " + courseId;
        
        try {
            Connection database = ConnectDB.getConnection();
            PreparedStatement stmt = database.prepareStatement(sql);
            
            stmt.executeUpdate();
            
            database.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void updateVideo(String videoId, String videoTitle, String videoUrl) {
        String sql = "update video set title = '" + videoTitle + "', video_url = '"+ videoUrl +"' where id = " + videoId;
        
        try {
            Connection database = ConnectDB.getConnection();
            PreparedStatement stmt = database.prepareStatement(sql);
            
            stmt.executeUpdate();
            
            database.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void deleteCourseCategoryRelationShipByCourseId(String courseId) {
        String sql = "delete from courseCategory where courseId = " + courseId;
        
        try {
            Connection database = ConnectDB.getConnection();
            PreparedStatement stmt = database.prepareStatement(sql);
            
            stmt.executeUpdate();
            
            database.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static ArrayList<Course> fetchCourseByCreatorId(String userId) {
        String sql = "select * from course where \"uid\" = " + userId;
        ArrayList<Course> courses = new ArrayList<>();
        
        try {
            Connection database = ConnectDB.getConnection();   
            PreparedStatement stmt = database.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            
            while (result.next()) {
                courses.add(new Course(
                    Integer.parseInt(result.getString(1)),
                    new User(Integer.parseInt(userId)),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getDouble(6)
                ));
            }
            
            stmt.close();
            database.close();
            
            for (int i = 0; i < courses.size(); ++i) {
                Course course = courses.get(i);
                course.setCategories(fetchCategoryByCourseId(Integer.toString(course.getId())));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return courses;
    }
    
    public static ArrayList<Category> fetchListCategory() {
        String sql = "select * from category";
        ArrayList<Category> categories = new ArrayList<>();
        
        try {
            Connection database = ConnectDB.getConnection();
            PreparedStatement stmt = database.prepareStatement(sql);
            
            ResultSet result = stmt.executeQuery();
            
            while (result.next()) categories.add(new Category(result.getInt(1), result.getString(2)));
            
            database.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return categories;
    }
    
    public static String createCourse(
            String title,
            String description,
            String price,
            String bannerUrl,
            String category
    ) {
// --------------------------------------------------------------------
        String tmp_uid = "1";
        String sql = "insert into course(\"uid\", title, \"description\", banner_url, price) values ("+tmp_uid+", '"+title+"', '"+description+"', '"+bannerUrl+"', "+price+")";
        String courseId = "";
        
        try {
            Connection database = ConnectDB.getConnection();
            PreparedStatement stmt = database.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            try {
                stmt.executeUpdate();
                try (ResultSet result = stmt.getGeneratedKeys()) {
                    if (result.next()) courseId = result.getString(1);
                }
            } catch (Exception e) {
                System.out.println("course service 45" + e);
            }
            
            stmt.close();
            database.close();
        } catch (Exception e) {
            System.err.println("course service 48" + e);
        }
        
        return courseId;
    }
    
    public static Category createCategory(String name) {
        String sql = "insert into category(\"name\") values ('"+name+"')";
        Category category = null;
        
        try {
            Connection database = ConnectDB.getConnection();   
            PreparedStatement stmt = database.prepareStatement(sql);

            try {
                stmt.executeUpdate();
                try (ResultSet result = stmt.getGeneratedKeys()) {
                    if (result.next()) {
                        category = new Category(result.getInt(1), result.getString(2));
                    }
                }
            } catch (Exception e) {
                category = fetchCategoryByName(name);
            }

            stmt.close();
            database.close();
        } catch (Exception e) {
            System.err.println("course service 50 " + e);
        }
        
        return category;
    }
    
    public static Category fetchCategoryByName(String name) {
        String sql = "select * from category where \"name\" like '%"+name+"%'";
        Category category = null;
        
        try {
            Connection database = ConnectDB.getConnection();   
            PreparedStatement stmt = database.prepareStatement(sql);
            
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                category = new Category(
                        result.getInt(1), 
                        result.getString(2)
                );
            }

            stmt.close();
            database.close();
        } catch (Exception e) {
            System.err.println("course service 72");
        }
        
        return category;
    }
    
    public static void createCourseCategoryRelationship(String courseId, String categoryId) {
        String sql = "insert into courseCategory(categoryId, courseId) values ("+categoryId+", "+courseId+")";
        
        
        try {
            Connection database = ConnectDB.getConnection();   
            PreparedStatement stmt = database.prepareStatement(sql);
            
            int result = stmt.executeUpdate();

            stmt.close();
            database.close();
        } catch (Exception e) {
            System.err.println("course service 96");
        }
    }

    public static void createVideo(String courseId, String name, String url) {
        String sql = "insert into video(courseID, title, video_url) values ("+courseId+", '"+name+"', '"+url+"')";
        
        try {
            Connection database = ConnectDB.getConnection();   
            PreparedStatement stmt = database.prepareStatement(sql);

            int result = stmt.executeUpdate();

            stmt.close();
            database.close();
        } catch (Exception e) {
            System.err.println("course service 131" + e);
        }
    }
    
    public static ArrayList<Course> fetchCourses(String title, String categoryId, String userId) {
        String sql = "select * from course join \"user\" on course.\"uid\" = \"user\".\"uid\"";
        if (title.length() > 0) sql = sql + " where title like '%" + title + "%'";
        if (categoryId.length() > 0) sql = sql + " join courseCategory on course.id = courseCategory.courseId where categoryId = " + categoryId;
        if (userId.length() > 0) sql = sql + " join userCourse on course.id = userCourse.courseId where userCourse.uid = " + userId;
        ArrayList<Course> courses = new ArrayList<>();
        
//        System.out.println(sql);
        
        try {
            Connection database = ConnectDB.getConnection();   
            PreparedStatement stmt = database.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            
            while (result.next()) {
                courses.add(new Course(
                    Integer.parseInt(result.getString(1)),
                    new User(
                            result.getInt(7), 
                            result.getInt(8), 
                            result.getString(9), 
                            result.getString(10), 
                            result.getDouble(11), 
                            result.getString(12)
                    ),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getDouble(6)
                ));
            }
            
            stmt.close();
            database.close();
            
            for (int i = 0; i < courses.size(); ++i) {
                Course course = courses.get(i);
                course.setCategories(fetchCategoryByCourseId(Integer.toString(course.getId())));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return courses;
    }
    
    public static ArrayList<Video> fetchVideoByCourseId(String courseId) {
        String sql = "select * from video where courseID = " + courseId;
        
        ArrayList<Video> videos = new ArrayList<>();
        
        try {
            Connection database = ConnectDB.getConnection();   
            PreparedStatement stmt = database.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            
            while (result.next()) {
                videos.add(new Video(
                        result.getInt(1),
                        result.getInt(2),
                        result.getString(3),
                        result.getString(4)
                ));
            }
            
            stmt.close();
            database.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return videos;
    }
    
    public static Course fetchCourseById(int id) {
        String sql = "select * from course where id = ?";
        
        try {
            Connection database = ConnectDB.getConnection();   
            PreparedStatement stmt = database.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            
            while (result.next()) {
            return new Course(
                        Integer.parseInt(result.getString(1)),
                        new User(Integer.parseInt(result.getString(2))),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getDouble(6)
                );
            }
            
            stmt.close();
            database.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        
       return null;
    }
    
    public static ArrayList<Category> fetchCategoryByCourseId(String courseId) {
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
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return categories;
    }
    
    public static void main(String[] args) {
        
    }
}
