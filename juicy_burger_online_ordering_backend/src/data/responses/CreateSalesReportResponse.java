package data.responses;

import data.types.Order;
import data.types.models.OrderModel;

import java.util.List;

public class CreateSalesReportResponse {

    private List<Order> salesReport;

    public CreateSalesReportResponse() {

    }

    private CreateSalesReportResponse(Builder builder) {
        this.salesReport = builder.sales;
    }

    public List<Order> getSalesReport() {
        return salesReport;
    }

    public void setSalesReport(List<Order> salesReport) {
        this.salesReport = salesReport;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<Order> sales;

        public Builder withSales(List<Order> withSales) {
            this.sales = withSales;
            return this;
        }

        public CreateSalesReportResponse build() {
            return new CreateSalesReportResponse(this);
        }
    }
}
