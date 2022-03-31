package data.responses;

import data.types.Order;
import data.types.models.OrderModel;

import java.util.List;

public class CreateSalesReportResponse {

    private List<Order> salesReport;

    public CreateSalesReportResponse() {

    }

    private CreateSalesReportResponse(Builder builder) {
        this.orderModel = builder.orderModel;
    }

    public  getOrderModel() {
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
