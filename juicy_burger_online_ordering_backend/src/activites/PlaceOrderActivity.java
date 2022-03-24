package activites;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import converters.ModelConverter;
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
import java.util.Locale;
import java.util.Map;

/**
 * Defines the behavior of placing an Order. Accepts a PlaceOrderRequest and returns
 * a PlaceOrderResponse. Updates the persistent layer with a new Order.
 */
public class PlaceOrderActivity implements RequestHandler<PlaceOrderRequest, PlaceOrderResponse> {
    private final OrderDao orderDao;
    private final MenuItemDao menuItemDao;

    @Inject
    public PlaceOrderActivity(OrderDao orderDao, MenuItemDao menuItemDao) {
        this.orderDao = orderDao;
        this.menuItemDao = menuItemDao;
    }

    @Override
    public PlaceOrderResponse handleRequest(PlaceOrderRequest request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(request.toString());

        Map<String, MenuItem> menuItemsMap = menuItemDao.getMapOfMenuItems();

        Map<MenuItem, Integer> orderMenuItems = new HashMap<>();
        for (Map.Entry<String, Integer> entry : request.getOrderDescription().entrySet()) {
            if (!menuItemsMap.containsKey(entry.getKey().trim().toLowerCase(Locale.ROOT))) {
                logger.log("Unable to process request: menuItem does not exist");
                throw new InvalidOrderException();
            }
            orderMenuItems.put(menuItemsMap.get(entry.getKey()), entry.getValue());
        }

        Order order = orderDao.saveOrder(
                Order.builder()
                        .withOrderId(OrderUtilities.generateOrderId())
                        .withPlacedDateTime(LocalDateTime.now())
                        .withProcessDateTime(null)
                        .withCompletedDateTime(null)
                        .withOrderMenuItems(orderMenuItems)
                        .withTotalPrice(OrderUtilities.calculateTotalPrice(orderMenuItems))
                        .build()
        );

        return PlaceOrderResponse.builder()
                .withOrderModel(ModelConverter.orderModelConverter(order))
                .build();
    }
}
