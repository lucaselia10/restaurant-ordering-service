package activites;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import converters.ModelConverter;
import daos.MenuItemDao;
import daos.OrderDao;
import data.requests.UpdateOrderRequest;
import data.responses.UpdateOrderResponse;
import data.types.MenuItem;
import data.types.Order;
import exceptions.InvalidOrderException;
import utilities.OrderUtilities;

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
                throw new InvalidOrderException("Bad request!");
            }
            if (entry.getValue() <= 0 || entry.getValue() > 99) {
                logger.log("Unable to process request: 0 or negative quantity value");
                throw new InvalidOrderException("Bad request!");
            }
            orderMenuItems.put(menuItemsMap.get(entry.getKey()), entry.getValue());
        }

        Order orderToUpdate = orderDao.getOrder(request.getOrderId());

        orderToUpdate.setOrderMenuItemsMap(orderMenuItems);
        orderToUpdate.setTotalPrice(OrderUtilities.calculateTotalPrice(orderMenuItems));

        Order order = orderDao.saveOrder(orderToUpdate);

        return UpdateOrderResponse.builder()
                .withOrderModel(ModelConverter.orderModelConverter(order))
                .build();
    }
}
