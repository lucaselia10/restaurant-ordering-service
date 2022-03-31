package data.requests;

import data.types.Order;

import java.util.List;
import java.util.Objects;

public class CreateSalesReportRequest {

    private List<Order> salesReport;

    public CreateSalesReportRequest() {

    }

    public CreateSalesReportRequest(Builder builder) {
        this.salesReport = builder.

    }

    public List<Order> getSalesReport() {
        return salesReport;
    }

    public void setSalesReport(List<Order> salesReport) {
        this.salesReport = salesReport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateSalesReportRequest that = (CreateSalesReportRequest) o;
        return getSalesReport().equals(that.getSalesReport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSalesReport());
    }

    @Override
    public String toString() {
        return "CreateSalesReportRequest{" +
                "salesReport=" + salesReport +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private List<Order> sales;


        public Builder withSales(List<Order> withSales) {
            this.sales = withSales;
            return this;
        }

        public CreateSalesReportRequest build() {
            return new CreateSalesReportRequest(this);
        }
    }
}
