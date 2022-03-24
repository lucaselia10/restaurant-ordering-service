package data.types.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Defines the characteristics of a MenuItem object. A MenuItem consists
 * of String name, Integer price in cents, and String description of the
 * MenuItem (such as ingredients)
 * @author willi
 */
public class OrderModel {
    private String orderId;
    private String placedDateTime;
    private String processDateTime;
    private String completedDateTime;
    private List<MenuItemModel> orderMenuItemsList;
    private int totalPrice;

    public OrderModel() {}

    private OrderModel(Builder builder) {
        this.orderId = builder.orderId;
        this.placedDateTime = builder.placedDateTime;
        this.processDateTime = builder.processDateTime;
        this.completedDateTime = builder.completedDateTime;
        this.orderMenuItemsList = builder.orderMenuItemsList;
        this.totalPrice = builder.totalPrice;
    }

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

    public List<MenuItemModel> getOrderMenuItemsList() {
        return orderMenuItemsList;
    }

    public void setOrderMenuItemsList(List<MenuItemModel> orderMenuItemsList) {
        this.orderMenuItemsList = orderMenuItemsList;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static Builder builder() {
        return new Builder();
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
                orderMenuItemsList.equals(that.orderMenuItemsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, placedDateTime, processDateTime, completedDateTime, orderMenuItemsList, totalPrice);
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "orderId='" + orderId + '\'' +
                ", placedDateTime='" + placedDateTime + '\'' +
                ", processDateTime='" + processDateTime + '\'' +
                ", completedDateTime='" + completedDateTime + '\'' +
                ", orderMenuItems=" + orderMenuItemsList +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public static class Builder {
        private String orderId;
        private String placedDateTime;
        private String processDateTime;
        private String completedDateTime;
        private List<MenuItemModel> orderMenuItemsList;
        private int totalPrice;

        public Builder withOrderId(String withOrderId) {
            this.orderId = withOrderId;
            return this;
        }

        public Builder withPlacedDateTime(String withPlaceDateTime) {
            this.placedDateTime = withPlaceDateTime;
            return this;
        }

        public Builder withProcessDateTime(String withProcessDateTime) {
            this.processDateTime = withProcessDateTime;
            return this;
        }

        public Builder withCompletedDateTime(String withCompletedDateTime) {
            this.completedDateTime = withCompletedDateTime;
            return this;
        }

        public Builder withOrderMenuItemsList(List<MenuItemModel> withOrderMenuItemsList) {
            this.orderMenuItemsList = new ArrayList<>(withOrderMenuItemsList);
            return this;
        }

        public Builder withTotalPrice(int withTotalPrice) {
            this.totalPrice = withTotalPrice;
            return this;
        }

        public OrderModel build() {
            return new OrderModel(this);
        }
    }
}
