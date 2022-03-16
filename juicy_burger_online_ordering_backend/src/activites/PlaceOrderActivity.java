package activites;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import daos.MenuItemDao;
import daos.OrderDao;

import data.requests.PlaceOrderRequest;
import data.responses.PlaceOrderResponse;
import data.types.MenuItem;
import data.types.Order;
import exceptions.InvalidOrderException;
import utilities.OrderUtilities;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines the activity-process of generating an Order
 */
public class PlaceOrderActivity implements RequestHandler<PlaceOrderRequest, PlaceOrderResponse> {
    private final OrderDao orderDao;
    private final MenuItemDao menuItemDao;

    @Inject
    public PlaceOrderActivity(OrderDao orderDao, MenuItemDao menuItemDao) {
        this.orderDao = orderDao;
        this.menuItemDao = menuItemDao;
    }

    // TODO: Activity is incomplete
    @Override
    public PlaceOrderResponse handleRequest(PlaceOrderRequest request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(request.toString());

        List<MenuItem> menuItems = menuItemDao.getMenuItems();
        Map<MenuItem, Long> orderMenuItems = new HashMap<>();

        for (Map.Entry<MenuItem, Long> entry : request.getOrderItems().entrySet()) {
            if (!menuItems.contains(entry.getKey())) {
                logger.log("Unable to process request");
                throw new InvalidOrderException();
            }
            orderMenuItems.put(entry.getKey(), entry.getValue());
        }

        orderDao.saveOrder(
                Order.builder()
                        .withOrderId(OrderUtilities.generateOrderId())
                        .withPlacedDateTime(LocalDateTime.now())
                        .withProcessDateTime(null)
                        .withCompletedDateTime(null)
                        .withOrderMenuItems(orderMenuItems)
                        .build()
        );

        return null;
    }
}
