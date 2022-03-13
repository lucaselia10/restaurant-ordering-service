package data.types;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

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
    private String orderId;
    private Map<MenuItem, Long> orderItems;
    private Long totalPrice;

    /**
     * A private Order constructor used in conjunction with the inner
     * Builder class.
     * @param builder The parsed Builder to build an Order Object
     */
    private Order(Builder builder) {
        this.orderId = builder.orderId;
        this.orderItems = builder.orderItems;
        this.totalPrice = calculatePrice(this.orderItems);
    }

    @DynamoDBAttribute(attributeName = "order_id")
    public String getOrderId() {
        return orderId;
    }

    @DynamoDBAttribute(attributeName = "menu_item_quantity_map")
    public Map<MenuItem, Long> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Map<MenuItem, Long> orderItems) {
        this.orderItems = new HashMap<>(orderItems);
        this.totalPrice = calculatePrice(this.orderItems);
    }

    @DynamoDBAttribute(attributeName = "total_price_cents")
    public Long getTotalPrice() {
        return totalPrice;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Private helper method that calculates the price of an Order
     * Object
     * @param orderItems MenuItem
     * @return
     */
    private static Long calculatePrice(Map<MenuItem, Long> orderItems) {
        long calculatedPrice = 0L;
        for (Map.Entry<MenuItem, Long> entry : orderItems.entrySet()) {
            calculatedPrice += entry.getKey().getPrice() * entry.getValue();
        }
        return calculatedPrice;
    }

    @Override
    public String toString() {
        return "PlaceOrderRequest{" +
                "orderId='" + orderId + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }

    public static class Builder {
        private String orderId;
        private Map<MenuItem, Long> orderItems;

        public Builder withOrderId(String withOrderId) {
            this.orderId = withOrderId;
            return this;
        }

        public Builder withOrderItems(Map<MenuItem, Long> withOrderItems) {
            this.orderItems = new HashMap<>(withOrderItems);
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
