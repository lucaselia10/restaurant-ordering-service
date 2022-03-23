package data.types;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
<<<<<<< HEAD
import converters.dynamodbtypeconverters.MenuItemsMapConverter;
import utilities.OrderUtilities;

=======
import converters.dynamodbtypeconverters.LocalDateTimeConverter;
import converters.dynamodbtypeconverters.MenuItemsQuantityMapConverter;

import exceptions.InvalidOrderException;

import java.time.LocalDateTime;
>>>>>>> origin/PlaceOrderActivityFeature
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Defines the characteristics of an Order object. An Order consists of a
<<<<<<< HEAD
 * String orderId, MenuItems mapped to a Long quantity, and Long price in
 * cents.
=======
 * String orderId, LocalDateTimes of the order place datetime, the order
 * process datetime, the order completed datetime, MenuItems mapped to a
 * Long quantity, and Long price in cents.
 *
 * Invariants: orderId must not be null, placeDateTime must not be null,
 * orderMenuItems must not be null nor empty, and totalPrice must not be
 * null nor zero.
>>>>>>> origin/PlaceOrderActivityFeature
 * @author willi
 */
@DynamoDBTable(tableName = "OrderHistory")
public class Order {
    private String orderId;
<<<<<<< HEAD
    private String placedDateTime;
    private String processDateTime;
    private String completedDateTime;
    private Map<MenuItem, Long> orderMenuItems;
    private Long totalPrice;
=======
    private LocalDateTime placedDateTime;
    private LocalDateTime processDateTime;
    private LocalDateTime completedDateTime;
    private Map<MenuItem, Integer> orderMenuItems;
    private Integer totalPrice;
>>>>>>> origin/PlaceOrderActivityFeature

    public Order() {}

    /**
     * A private Order constructor used in conjunction with the inner
     * Builder class.
     * @param builder The parsed Builder to build an Order Object
<<<<<<< HEAD
     */
    public Order(Builder builder) {
=======
     * @throws InvalidOrderException when an Order invariant is broken
     */
    private Order(Builder builder) {
>>>>>>> origin/PlaceOrderActivityFeature
        this.orderId = builder.orderId;
        this.placedDateTime = builder.placedDateTime;
        this.processDateTime = builder.processDateTime;
        this.completedDateTime = builder.completedDateTime;
        this.orderMenuItems = builder.orderMenuItems;
        this.totalPrice = builder.totalPrice;
<<<<<<< HEAD
=======
        this.validateOrderState();
>>>>>>> origin/PlaceOrderActivityFeature
    }

    @DynamoDBHashKey(attributeName = "orderId")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @DynamoDBAttribute(attributeName = "placed_dateTime")
<<<<<<< HEAD
    public String getPlacedDateTime() {
        return placedDateTime;
    }

    public void setPlacedDateTime(String placedDateTime) {
=======
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    public LocalDateTime getPlacedDateTime() {
        return placedDateTime;
    }

    public void setPlacedDateTime(LocalDateTime placedDateTime) {
>>>>>>> origin/PlaceOrderActivityFeature
        this.placedDateTime = placedDateTime;
    }

    @DynamoDBAttribute(attributeName = "process_dateTime")
<<<<<<< HEAD
    public String getProcessDateTime() {
        return processDateTime;
    }

    public void setProcessDateTime(String processDateTime) {
=======
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    public LocalDateTime getProcessDateTime() {
        return processDateTime;
    }

    public void setProcessDateTime(LocalDateTime processDateTime) {
>>>>>>> origin/PlaceOrderActivityFeature
        this.processDateTime = processDateTime;
    }

    @DynamoDBAttribute(attributeName = "completed_dateTime")
<<<<<<< HEAD
    public String getCompletedDateTime() {
        return completedDateTime;
    }

    public void setCompletedDateTime(String completedDateTime) {
        this.completedDateTime = completedDateTime;
    }

    @DynamoDBTypeConverted(converter = MenuItemsMapConverter.class)
    @DynamoDBAttribute(attributeName = "menuItem_quantity_map")
    public Map<MenuItem, Long> getOrderMenuItems() {
        return orderMenuItems;
    }

    // TODO: May need to move the price calculation outside of POJO (to dedicated Activity)
    public void setOrderMenuItems(Map<MenuItem, Long> orderMenuItems) {
        this.orderMenuItems = new HashMap<>(orderMenuItems);
        this.totalPrice = OrderUtilities.calculatePrice(orderMenuItems);
    }

    @DynamoDBAttribute(attributeName = "total_price_cents")
    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

=======
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    public LocalDateTime getCompletedDateTime() {
        return completedDateTime;
    }

    public void setCompletedDateTime(LocalDateTime completedDateTime) {
        this.completedDateTime = completedDateTime;
    }

    @DynamoDBAttribute(attributeName = "menuItem_quantity_map")
    @DynamoDBTypeConverted(converter = MenuItemsQuantityMapConverter.class)
    public Map<MenuItem, Integer> getOrderMenuItems() {
        return orderMenuItems;
    }

    public void setOrderMenuItems(Map<MenuItem, Integer> orderMenuItems) {
        this.orderMenuItems = new HashMap<>(orderMenuItems);
    }

    @DynamoDBAttribute(attributeName = "total_price_cents")
    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Private helper method that enforces the Order invariants
     * @throws InvalidOrderException when an Order invariant is broken
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
        if (this.totalPrice.compareTo(0) <= 0) {
            throw new InvalidOrderException(
                    "totalPrice must not be 0 or negative",
                    new IllegalArgumentException()
            );
        }
    }

>>>>>>> origin/PlaceOrderActivityFeature
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
<<<<<<< HEAD
        private String placedDateTime;
        private String processDateTime;
        private String completedDateTime;
        private Map<MenuItem, Long> orderMenuItems;
        private Long totalPrice;
=======
        private LocalDateTime placedDateTime;
        private LocalDateTime processDateTime;
        private LocalDateTime completedDateTime;
        private Map<MenuItem, Integer> orderMenuItems;
        private Integer totalPrice;
>>>>>>> origin/PlaceOrderActivityFeature

        public Builder withOrderId(String withOrderId) {
            this.orderId = withOrderId;
            return this;
        }

<<<<<<< HEAD
        public Builder withPlacedDateTime(String withPlacedDateTime) {
=======
        public Builder withPlacedDateTime(LocalDateTime withPlacedDateTime) {
>>>>>>> origin/PlaceOrderActivityFeature
            this.placedDateTime = withPlacedDateTime;
            return this;
        }

<<<<<<< HEAD
        public Builder withProcessDateTime(String withProcessDateTime) {
=======
        public Builder withProcessDateTime(LocalDateTime withProcessDateTime) {
>>>>>>> origin/PlaceOrderActivityFeature
            this.processDateTime = withProcessDateTime;
            return this;
        }

<<<<<<< HEAD
        public Builder withCompletedDateTime(String withCompletedDateTime) {
=======
        public Builder withCompletedDateTime(LocalDateTime withCompletedDateTime) {
>>>>>>> origin/PlaceOrderActivityFeature
            this.completedDateTime = withCompletedDateTime;
            return this;
        }

<<<<<<< HEAD
        public Builder withOrderMenuItems(Map<MenuItem, Long> withOrderItems) {
            this.orderMenuItems = new HashMap<>(withOrderItems);
            this.totalPrice = OrderUtilities.calculatePrice(withOrderItems);
=======
        public Builder withOrderMenuItems(Map<MenuItem, Integer> withOrderItems) {
            this.orderMenuItems = new HashMap<>(withOrderItems);
            return this;
        }

        public Builder withTotalPrice(Integer withTotalPrice) {
            this.totalPrice = withTotalPrice;
>>>>>>> origin/PlaceOrderActivityFeature
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
