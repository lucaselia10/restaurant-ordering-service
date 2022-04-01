package activites;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import converters.ModelConverter;
import daos.OrderDao;
import data.requests.GetOrderRequest;
import data.responses.GetOrderResponse;
import data.types.Order;
import data.types.models.OrderModel;

import javax.inject.Inject;


public class GetOrderActivity implements RequestHandler<GetOrderRequest, GetOrderResponse> {
    private final OrderDao orderDao;

    @Inject
    public GetOrderActivity(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    @Override
    public GetOrderResponse handleRequest(GetOrderRequest orderRequest, Context context) {

        Order order = orderDao.getOrder(orderRequest.getOrderId());
        OrderModel orderModel = ModelConverter.orderModelConverter(order);
        return GetOrderResponse.builder()
                .withOrderModel(orderModel)
                .build();
    }
}
