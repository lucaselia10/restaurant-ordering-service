package activites;

<<<<<<< HEAD
// TODO: This class needs to be implemented
public class UpdateOrderActivity {
=======
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import daos.MenuItemDao;
import daos.OrderDao;
import data.requests.UpdateOrderRequest;
import data.responses.UpdateOrderResponse;

import javax.inject.Inject;

/**
 * Defines the behavior of updating an Order. Accepts a UpdateOrderRequest and
 * returns a UpdateOrderResponse. Updates an Order within the persistent layer.
 */
public class UpdateOrderActivity implements RequestHandler<UpdateOrderRequest, UpdateOrderResponse> {
    private OrderDao orderDao;
    private MenuItemDao menuItemDao;

    @Inject
    public UpdateOrderActivity(OrderDao orderDao, MenuItemDao menuItemDao) {
        this.orderDao = orderDao;
        this.menuItemDao = menuItemDao;
    }

    // TODO: This method needs to be implemented
    @Override
    public UpdateOrderResponse handleRequest(UpdateOrderRequest input, Context context) {
        return null;
    }
>>>>>>> origin/PlaceOrderActivityFeature
}
