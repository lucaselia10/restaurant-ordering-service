package activites;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import daos.MenuItemDao;
import daos.OrderDao;
import data.requests.UpdateOrderRequest;
import data.responses.UpdateOrderResponse;
import data.types.MenuItem;
import exceptions.InvalidOrderException;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Defines the behavior of updating an Order. Accepts a UpdateOrderRequest and
 * returns a UpdateOrderResponse. Updates an Order within the persistent layer.
 */
public class UpdateOrderActivity implements RequestHandler<UpdateOrderRequest, UpdateOrderResponse> {
    private final OrderDao orderDao;
    private final MenuItemDao menuItemDao;

    @Inject
    public UpdateOrderActivity(OrderDao orderDao, MenuItemDao menuItemDao) {
        this.orderDao = orderDao;
        this.menuItemDao = menuItemDao;
    }

    @Override
    public UpdateOrderResponse handleRequest(UpdateOrderRequest request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(request.toString());

        Map<String, MenuItem> menuItemsMap = menuItemDao.getMapOfMenuItems();

        Map<MenuItem, Integer> orderMenuItems = new HashMap<>();
        for (Map.Entry<String, Integer> entry : request.getOrderDescription().entrySet()) {
            if (!menuItemsMap.containsKey(entry.getKey())) {
                logger.log("Unable to process request: menuItem does not exist");
                throw new InvalidOrderException();
            }
            if (entry.getValue() <= 0) {
                logger.log("Unable to process request: 0 or negative quantity value");
                throw new InvalidOrderException();
            }
            orderMenuItems.put(menuItemsMap.get(entry.getKey()), entry.getValue());
        }

        return null;
    }
}
