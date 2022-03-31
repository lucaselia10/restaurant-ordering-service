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
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
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

        ZonedDateTime placedDateTime = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));

        Order order = orderDao.saveOrder(
                Order.builder()
                        .withOrderId(OrderUtilities.generateOrderId())
                        .withPlacedDate(placedDateTime.toLocalDate())
                        .withPlacedTime(placedDateTime.toLocalTime())
                        .withOrderMenuItems(orderMenuItems)
                        .withTotalPrice(OrderUtilities.calculateTotalPrice(orderMenuItems))
                        .build()
        );
        logger.log(order.toString());

        return PlaceOrderResponse.builder()
                .withOrderModel(ModelConverter.orderModelConverter(order))
                .build();
    }
}
