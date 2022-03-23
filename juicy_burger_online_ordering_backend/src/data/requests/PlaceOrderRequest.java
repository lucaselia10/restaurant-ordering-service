package data.requests;

import data.types.MenuItem;

import java.util.HashMap;
import java.util.Map;
<<<<<<< HEAD

// TODO: This class needs to be implemented and coordinated with Front End
public class PlaceOrderRequest {
    private Map<MenuItem, Long> orderItems;
=======
import java.util.List;

/**
 *
 */
// TODO: This class needs to be implemented and coordinated with Front End
public class PlaceOrderRequest {
    private Map<String, Integer> orderDescription;
>>>>>>> origin/PlaceOrderActivityFeature

    public PlaceOrderRequest() {}

    public PlaceOrderRequest(Builder builder) {
<<<<<<< HEAD
        this.orderItems = builder.orderItems;
    }

    public Map<MenuItem, Long> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Map<MenuItem, Long> orderItems) {
        this.orderItems = orderItems;
=======
        this.orderDescription = builder.orderItems;
    }

    public Map<String, Integer> getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(Map<String, Integer> orderDescription) {
        this.orderDescription = orderDescription;
>>>>>>> origin/PlaceOrderActivityFeature
    }

    public Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "PlaceOrderRequest{" +
<<<<<<< HEAD
                "orderItems=" + orderItems +
=======
                "orderItems=" + orderDescription +
>>>>>>> origin/PlaceOrderActivityFeature
                '}';
    }

    public static class Builder {
<<<<<<< HEAD
        private Map<MenuItem, Long> orderItems;

        public Builder withOrderItems(Map<MenuItem, Long> withOrderItems) {
=======
        private Map<String, Integer> orderItems;

        public Builder withOrderItems(Map<String, Integer> withOrderItems) {
>>>>>>> origin/PlaceOrderActivityFeature
            this.orderItems = new HashMap<>(withOrderItems);
            return this;
        }

        public PlaceOrderRequest build() {
            return new PlaceOrderRequest(this);
        }
    }
}
