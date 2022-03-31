package activites;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import converters.ModelConverter;
import daos.OrderDao;
import data.requests.CreateSalesReportRequest;
import data.responses.CreateSalesReportResponse;
import data.types.Order;

import javax.inject.Inject;
import java.util.List;

public class CreateSalesReportActivity implements RequestHandler<CreateSalesReportRequest, CreateSalesReportResponse> {

    private final OrderDao orderDao;

    @Inject
    public CreateSalesReportActivity(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public CreateSalesReportResponse handleRequest(CreateSalesReportRequest request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(request.toString());

        List<Order> salesReport = orderDao.getOrderByPlacedDate(request.getSalesReport());
        return CreateSalesReportResponse.builder()
                .withSales(salesReport)
                .build();



    }
}
