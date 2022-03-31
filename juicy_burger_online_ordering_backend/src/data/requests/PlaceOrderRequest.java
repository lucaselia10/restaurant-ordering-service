package data.requests;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class PlaceOrderRequest {
    private Map<String, Integer> orderDescription;

    public PlaceOrderRequest() {}

    private PlaceOrderRequest(Builder builder) {
        this.orderDescription = builder.orderDescription;
    }

    public Map<String, Integer> getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(Map<String, Integer> orderDescription) {
        this.orderDescription = new HashMap<>(orderDescription);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "PlaceOrderRequest{" +
                "orderItems=" + orderDescription +
                '}';
    }

    public static class Builder {
        private Map<String, Integer> orderDescription;

        public Builder withOrderItems(Map<String, Integer> withOrderDescription) {
            this.orderDescription = new HashMap<>(withOrderDescription);
            return this;
        }

        public PlaceOrderRequest build() {
            return new PlaceOrderRequest(this);
        }
    }
}
