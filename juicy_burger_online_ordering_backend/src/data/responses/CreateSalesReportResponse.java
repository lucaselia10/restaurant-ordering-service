package data.responses;

import data.types.models.OrderModel;

public class CreateSalesReportResponse {

    private OrderModel orderModel;

    public CreateSalesReportResponse() {

    }

    private CreateSalesReportResponse(Builder builder) {
        this.orderModel = builder.orderModel;
    }

    public OrderModel getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private OrderModel orderModel;

        public Builder withOrderModel(OrderModel withOrderModel) {
            this.orderModel = withOrderModel;
            return this;
        }

        public CreateSalesReportResponse build() {
            return new CreateSalesReportResponse(this);
        }
    }
}
