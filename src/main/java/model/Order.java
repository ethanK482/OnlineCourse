package model;

import java.util.Date;
import java.util.List;

public class Order {

    private int id;
    private double totalPrice;
    private Date orderTime;
    private int isPay;
    private int uid;
    private List<OrderItem> items;

    public Order(int id, double totalPrice, Date orderTime, int isPay, int uid) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
        this.isPay = isPay;
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", totalPrice=" + totalPrice + ", orderTime=" + orderTime + ", isPay=" + isPay + ", uid=" + uid + '}';
    }

}
