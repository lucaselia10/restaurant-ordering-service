package data.types;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import converters.dynamodbtypeconverters.LocalDateTimeConverter;
import converters.dynamodbtypeconverters.MenuItemsQuantityMapConverter;

import exceptions.InvalidOrderException;
import utilities.OrderUtilities;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Defines the characteristics of an Order object. An Order consists of a
 * String orderId, LocalDateTimes of the order place datetime, the order
 * process datetime, the order completed datetime, MenuItems mapped to a
 * Long quantity, and Long price in cents.
 *
 * Class invariants: orderId must not be null, placeDateTime must not be
 * @author willi
 */
@DynamoDBTable(tableName = "OrderHistory")
public class Order {
    private String orderId;
    private LocalDateTime placedDateTime;
    private LocalDateTime processDateTime;
    private LocalDateTime completedDateTime;
    private Map<MenuItem, Long> orderMenuItems;
    private Long totalPrice;

    public Order() {}

    /**
     * A private Order constructor used in conjunction with the inner
     * Builder class.
     * @param builder The parsed Builder to build an Order Object
     */
    private Order(Builder builder) {
        this.orderId = builder.orderId;
        this.placedDateTime = builder.placedDateTime;
        this.processDateTime = builder.processDateTime;
        this.completedDateTime = builder.completedDateTime;
        this.orderMenuItems = builder.orderMenuItems;
        this.totalPrice = builder.totalPrice;
        this.validateOrderState();
    }

    @DynamoDBHashKey(attributeName = "orderId")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @DynamoDBAttribute(attributeName = "placed_dateTime")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    public LocalDateTime getPlacedDateTime() {
        return placedDateTime;
    }

    public void setPlacedDateTime(LocalDateTime placedDateTime) {
        this.placedDateTime = placedDateTime;
    }

    @DynamoDBAttribute(attributeName = "process_dateTime")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    public LocalDateTime getProcessDateTime() {
        return processDateTime;
    }

    public void setProcessDateTime(LocalDateTime processDateTime) {
        this.processDateTime = processDateTime;
    }

    @DynamoDBAttribute(attributeName = "completed_dateTime")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    public LocalDateTime getCompletedDateTime() {
        return completedDateTime;
    }

    public void setCompletedDateTime(LocalDateTime completedDateTime) {
        this.completedDateTime = completedDateTime;
    }

    @DynamoDBAttribute(attributeName = "menuItem_quantity_map")
    @DynamoDBTypeConverted(converter = MenuItemsQuantityMapConverter.class)
    public Map<MenuItem, Long> getOrderMenuItems() {
        return orderMenuItems;
    }

    public void setOrderMenuItems(Map<MenuItem, Long> orderMenuItems) {
        this.orderMenuItems = new HashMap<>(orderMenuItems);
    }

    @DynamoDBAttribute(attributeName = "total_price_cents")
    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Private helper method that enforces the Order class invariants
     */
    private void validateOrderState() {
        if (this.orderId == null) {
            throw new InvalidOrderException(
                    "orderId must not be null",
                    new IllegalArgumentException()
            );
        }
        if (this.placedDateTime == null) {
            throw new InvalidOrderException(
                    "placedDateTime must not be null",
                    new IllegalArgumentException()
            );
        }
        if (this.orderMenuItems == null) {
            throw new InvalidOrderException(
                    "orderMenuItems must not be null",
                    new IllegalArgumentException()
            );
        }
        if (this.orderMenuItems.size() == 0) {
            throw new InvalidOrderException(
                    "orderMenuItems must not be empty",
                    new IllegalArgumentException()
            );
        }
        if (this.totalPrice == null) {
            throw new InvalidOrderException(
                    "totalPrice must not be null",
                    new IllegalArgumentException()
            );
        }
        if (this.totalPrice.compareTo(0L) <= 0) {
            throw new InvalidOrderException(
                    "totalPrice must not be 0 or negative",
                    new IllegalArgumentException()
            );
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", placedDateTime='" + placedDateTime + '\'' +
                ", processDateTime='" + processDateTime + '\'' +
                ", completedDateTime='" + completedDateTime + '\'' +
                ", orderMenuItems=" + orderMenuItems +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId.equals(order.orderId) &&
                placedDateTime.equals(order.placedDateTime) &&
                Objects.equals(processDateTime, order.processDateTime) &&
                Objects.equals(completedDateTime, order.completedDateTime) &&
                orderMenuItems.equals(order.orderMenuItems) &&
                totalPrice.equals(order.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, placedDateTime, processDateTime, completedDateTime, orderMenuItems, totalPrice);
    }

    public static class Builder {
        private String orderId;
        private LocalDateTime placedDateTime;
        private LocalDateTime processDateTime;
        private LocalDateTime completedDateTime;
        private Map<MenuItem, Long> orderMenuItems;
        private Long totalPrice;

        public Builder withOrderId(String withOrderId) {
            this.orderId = withOrderId;
            return this;
        }

        public Builder withPlacedDateTime(LocalDateTime withPlacedDateTime) {
            this.placedDateTime = withPlacedDateTime;
            return this;
        }

        public Builder withProcessDateTime(LocalDateTime withProcessDateTime) {
            this.processDateTime = withProcessDateTime;
            return this;
        }

        public Builder withCompletedDateTime(LocalDateTime withCompletedDateTime) {
            this.completedDateTime = withCompletedDateTime;
            return this;
        }

        public Builder withOrderMenuItems(Map<MenuItem, Long> withOrderItems) {
            this.orderMenuItems = new HashMap<>(withOrderItems);
            return this;
        }

        public Builder withTotalPrice(Long withTotalPrice) {
            this.totalPrice = withTotalPrice;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
