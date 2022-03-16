package activites;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import daos.OrderDao;
import data.requests.GetOrderRequest;
import data.responses.GetOrderResponse;

import javax.inject.Inject;


public class GetOrderActivity implements RequestHandler<GetOrderRequest, GetOrderResponse> {
    private final OrderDao orderDao;

    @Inject
    public GetOrderActivity(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    @Override
    public GetOrderResponse handleRequest(GetOrderRequest orderRequest, Context context) {
        return null;
//        GetOrderResponse orderResponse = orderDao.getOrder(orderRequest.getId());
//        return orderResponse;

    }
}
