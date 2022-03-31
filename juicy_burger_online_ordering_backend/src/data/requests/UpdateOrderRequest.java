package data.requests;

import java.util.HashMap;
import java.util.Map;

public class UpdateOrderRequest {
    private String orderId;
    private Map<String, Integer> orderDescription;

    public UpdateOrderRequest() {}

    private UpdateOrderRequest(Builder builder) {
        this.orderId = builder.orderId;
        this.orderDescription = builder.orderItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Map<String, Integer> getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(Map<String, Integer> orderDescription) {
        this.orderDescription = orderDescription;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "UpdateOrderRequest{" +
                "orderId='" + orderId + '\'' +
                ", orderDescription=" + orderDescription +
                '}';
    }

    public static class Builder {
        private String orderId;
        private Map<String, Integer> orderItems;

        public Builder withOrderId(String withOrderId) {
            this.orderId = withOrderId;
            return this;
        }

        public Builder withOrderItems(Map<String, Integer> withOrderDescription) {
            this.orderItems = new HashMap<>(withOrderDescription);
            return this;
        }

        public UpdateOrderRequest build() {
            return new UpdateOrderRequest(this);
        }
    }
}
