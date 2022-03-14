package data.types;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import utilities.OrderUtilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines the characteristics of an Order object. An Order consists of a
 * String orderId, MenuItems mapped to a Long quantity, and Long price in
 * cents.
 * @author willi
 */
@DynamoDBTable(tableName = "OrderHistory")
public class Order {
    private final String orderId;
    private final String placedDateTime;
    private final String processDateTime;
    private final String completedDateTime;
    private Map<MenuItem, Long> orderMenuItems;
    private Long totalPrice;

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
    }

    @DynamoDBHashKey(attributeName = "orderId")
    public String getOrderId() {
        return orderId;
    }

    @DynamoDBRangeKey(attributeName = "placed_dateTime")
    public String getPlacedDateTime() {
        return placedDateTime;
    }

    @DynamoDBAttribute(attributeName = "process_dateTime")
    public String getProcessDateTime() {
        return processDateTime;
    }

    @DynamoDBAttribute(attributeName = "completed_dateTime")
    public String getCompletedDateTime() {
        return completedDateTime;
    }

    @DynamoDBAttribute(attributeName = "menuItem_quantity_map")
    public Map<MenuItem, Long> getOrderMenuItems() {
        return orderMenuItems;
    }

    public void setOrderMenuItems(Map<MenuItem, Long> orderMenuItems) {
        this.orderMenuItems = new HashMap<>(orderMenuItems);
        this.totalPrice = OrderUtilities.calculatePrice(orderMenuItems);
    }

    @DynamoDBAttribute(attributeName = "total_price_cents")
    public Long getTotalPrice() {
        return totalPrice;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "PlaceOrderRequest{" +
                "orderId='" + orderId + '\'' +
                ", orderItems=" + orderMenuItems +
                '}';
    }

    public static class Builder {
        private String orderId;
        private String placedDateTime;
        private String processDateTime;
        private String completedDateTime;
        private Map<MenuItem, Long> orderMenuItems;
        private Long totalPrice;

        public Builder withOrderId(String withOrderId) {
            this.orderId = withOrderId;
            return this;
        }

        public Builder withPlacedDateTime(String withPlacedDateTime) {
            this.placedDateTime = withPlacedDateTime;
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

        public Builder withOrderMenuItems(Map<MenuItem, Long> withOrderItems) {
            this.orderMenuItems = new HashMap<>(withOrderItems);
            this.totalPrice = OrderUtilities.calculatePrice(withOrderItems);
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
