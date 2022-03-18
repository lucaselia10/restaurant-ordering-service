package activites;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import daos.OrderDao;
import data.requests.DeleteOrderRequest;
import data.responses.DeleteOrderResponse;

import javax.inject.Inject;

/**
 * Defines the behavior of removing an Order. Accepts a DeleteOrderRequest and returns
 * a DeleteOrderResponse. Updates the persistent layer by removing an existing Order.
 */
public class DeleteOrderActivity implements RequestHandler<DeleteOrderRequest, DeleteOrderResponse> {
    private OrderDao orderDao;

    @Inject
    public DeleteOrderActivity(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    // TODO: This method needs to be implemented
    @Override
    public DeleteOrderResponse handleRequest(DeleteOrderRequest input, Context context) {
        return null;
    }
}
