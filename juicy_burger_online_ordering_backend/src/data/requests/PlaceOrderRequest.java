package data.requests;

import data.types.MenuItem;

import java.util.HashMap;
import java.util.Map;

public class PlaceOrderRequest {
    private Map<MenuItem, Long> orderItems;

    public PlaceOrderRequest() {}

    public PlaceOrderRequest(Builder builder) {
        this.orderItems = builder.orderItems;
    }

    public Map<MenuItem, Long> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Map<MenuItem, Long> orderItems) {
        this.orderItems = orderItems;
    }

    public Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "PlaceOrderRequest{" +
                "orderItems=" + orderItems +
                '}';
    }

    public static class Builder {
        private Map<MenuItem, Long> orderItems;

        public Builder withOrderItems(Map<MenuItem, Long> withOrderItems) {
            this.orderItems = new HashMap<>(withOrderItems);
            return this;
        }

        public PlaceOrderRequest build() {
            return new PlaceOrderRequest(this);
        }
    }
}
