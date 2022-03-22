package data.requests;

import data.types.MenuItem;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 *
 */
// TODO: This class needs to be implemented and coordinated with Front End
public class PlaceOrderRequest {
    private Map<String, Integer> orderDescription;

    public PlaceOrderRequest() {}

    public PlaceOrderRequest(Builder builder) {
        this.orderDescription = builder.orderItems;
    }

    public Map<String, Integer> getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(Map<String, Integer> orderDescription) {
        this.orderDescription = orderDescription;
    }

    public Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "PlaceOrderRequest{" +
                "orderItems=" + orderDescription +
                '}';
    }

    public static class Builder {
        private Map<String, Integer> orderItems;

        public Builder withOrderItems(Map<String, Integer> withOrderItems) {
            this.orderItems = new HashMap<>(withOrderItems);
            return this;
        }

        public PlaceOrderRequest build() {
            return new PlaceOrderRequest(this);
        }
    }
}
