package data.types.models;

import data.types.MenuItem;

import java.util.Map;
import java.util.Objects;

public class OrderModel {
    private String orderId;
    private String placedDateTime;
    private String processDateTime;
    private String completedDateTime;
    private Map<MenuItem, Integer> orderMenuItems;
    private int totalPrice;

    public OrderModel() {}

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPlacedDateTime() {
        return placedDateTime;
    }

    public void setPlacedDateTime(String placedDateTime) {
        this.placedDateTime = placedDateTime;
    }

    public String getProcessDateTime() {
        return processDateTime;
    }

    public void setProcessDateTime(String processDateTime) {
        this.processDateTime = processDateTime;
    }

    public String getCompletedDateTime() {
        return completedDateTime;
    }

    public void setCompletedDateTime(String completedDateTime) {
        this.completedDateTime = completedDateTime;
    }

    public Map<MenuItem, Integer> getOrderMenuItems() {
        return orderMenuItems;
    }

    public void setOrderMenuItems(Map<MenuItem, Integer> orderMenuItems) {
        this.orderMenuItems = orderMenuItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderModel that = (OrderModel) o;
        return totalPrice == that.totalPrice &&
                orderId.equals(that.orderId) &&
                placedDateTime.equals(that.placedDateTime) &&
                processDateTime.equals(that.processDateTime) &&
                completedDateTime.equals(that.completedDateTime) &&
                orderMenuItems.equals(that.orderMenuItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, placedDateTime, processDateTime, completedDateTime, orderMenuItems, totalPrice);
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "orderId='" + orderId + '\'' +
                ", placedDateTime='" + placedDateTime + '\'' +
                ", processDateTime='" + processDateTime + '\'' +
                ", completedDateTime='" + completedDateTime + '\'' +
                ", orderMenuItems=" + orderMenuItems +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
