package data.requests;

import java.util.Objects;

public class DeleteOrderRequest {
    private String orderId;

    public DeleteOrderRequest() { }

    public DeleteOrderRequest(Builder builder) {
        this.orderId = builder.orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteOrderRequest that = (DeleteOrderRequest) o;
        return orderId.equals(that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    public static class Builder {
        private String orderId;

        public Builder withOrderId(String withOrderId) {
            this.orderId = withOrderId;
            return this;
        }

        public DeleteOrderRequest build() {
            return new DeleteOrderRequest(this);
        }
    }
}
