package data.responses;

import data.types.Order;

public class GetOrderResponse {
    private Order order;

    public GetOrderResponse(Builder builder) {
        this.order = builder.order;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Order order;
        public Builder withOrder(Order order) {
            this.order = order;
            return this;
        }
        public GetOrderResponse build() {return new GetOrderResponse(this);}
    }
}
