package activites;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import daos.OrderDao;
import data.requests.PlaceOrderRequest;
import data.responses.PlaceOrderResponse;

import javax.inject.Inject;

public class PlaceOrderActivity implements RequestHandler<PlaceOrderRequest, PlaceOrderResponse> {
    private OrderDao orderDao;

    @Inject
    public PlaceOrderActivity(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public PlaceOrderResponse handleRequest(PlaceOrderRequest request, Context context) {
        return null;
    }
}
