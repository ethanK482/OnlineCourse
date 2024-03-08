package model;

/**
 *
 * @author HuyHK
 */
public class OrderItem {

    private String id;
    private Course Course;

    public OrderItem() {
    }

    public OrderItem(String id, Course Course) {
        this.id = id;
        this.Course = Course;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Course getCourse() {
        return Course;
    }

    public void setCourseID(Course Course) {
        this.Course = Course;
    }

}
