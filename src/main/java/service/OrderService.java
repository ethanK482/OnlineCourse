package service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Order;
import static service.UserService.c;
import static service.UserService.pre;
import static service.UserService.rs;
import util.ConnectDB;

public class OrderService {

    public static Order createOrder(double totalPrice, int uid) {
        String createOrderQuery = "insert into [order]( totalPrice, orderTime, isPaid, uid)"
                + "values(?, ?, ?, ?)"
                + "select * from [order] where id=  SCOPE_IDENTITY()";
        try {
            c = ConnectDB.getConnection();
            pre = c.prepareStatement(createOrderQuery);
            pre.setDouble(1, totalPrice);
            pre.setDate(2, new Date(System.currentTimeMillis()));
            pre.setInt(3, 0);
            pre.setInt(4, uid);
            rs = pre.executeQuery();
            if (rs.next()) {
                Order order = new Order(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getInt(4), rs.getInt(5));
                return order;
            }
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createOrderItem(int courseId, int orderId) {
        String createOrderItemQuery = "insert into [orderItem]( orderId, productId )"
                + "values(?, ?)";
        try {
            c = ConnectDB.getConnection();
            pre = c.prepareStatement(createOrderItemQuery);
            pre.setInt(1, orderId);
            pre.setInt(2, courseId);
            pre.executeUpdate();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updatePayStatus(int orderId) {
        String updateOrderQuery = "update [order] set isPaid=1 where id= ? ";
        try {
            c = ConnectDB.getConnection();
            pre = c.prepareStatement(updateOrderQuery);
            pre.setInt(1, orderId);
            pre.executeUpdate();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList findOrderItemByOrder(int orderId) throws SQLException {
        String createUserQuery = "select * from orderItem where orderId = ? ";
        try {
            c = ConnectDB.getConnection();
            pre = c.prepareStatement(createUserQuery);
            pre.setInt(1, orderId);
            rs = pre.executeQuery();
            ArrayList courseIds = new ArrayList();
            while (rs.next()) {
                courseIds.add(rs.getInt(3));
            }

            return courseIds;

        } catch (Exception e) {
            e.printStackTrace();
            c.close();
            return null;
        }

    }

    public static void addUserCourse(int uid, int courseID) throws SQLException {
        String addUserCourseQuery = "insert into [userCourse]( courseID, uid )"
                + "values(?, ?)";
        c = ConnectDB.getConnection();
        pre = c.prepareStatement(addUserCourseQuery);
        pre.setInt(1, courseID);
        pre.setInt(2, uid);
        pre.executeUpdate();
    }

    
    
     public static ArrayList findUserCourse(int uid) throws SQLException {
        String createUserQuery = "select * from [userCourse] where uid = ? ";
        try {
            c = ConnectDB.getConnection();
            pre = c.prepareStatement(createUserQuery);
            pre.setInt(1, uid);
            rs = pre.executeQuery();
            ArrayList courseIds = new ArrayList();
            while (rs.next()) {
                courseIds.add(rs.getInt(1));
            }

            return courseIds;

        } catch (Exception e) {
            e.printStackTrace();
            c.close();
            return null;
        }

    }
    public static void main(String[] args) throws SQLException {
        System.out.println(OrderService.findUserCourse(10));
    }
}
