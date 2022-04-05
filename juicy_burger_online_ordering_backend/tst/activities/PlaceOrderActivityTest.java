package activities;

import activites.PlaceOrderActivity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import daos.MenuItemDao;
import daos.OrderDao;

import data.requests.PlaceOrderRequest;
import data.responses.PlaceOrderResponse;
import data.types.MenuItem;
import data.types.Order;
import data.types.models.MenuItemModel;
import exceptions.InvalidOrderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import utilities.OrderUtilities;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PlaceOrderActivityTest {
    private PlaceOrderActivity placeOrderActivity;

    @Mock
    private OrderDao orderDao;

    @Mock
    private MenuItemDao menuItemDao;

    @Mock
    private Context context;

    @Mock
    private LambdaLogger lambdaLogger;

    @BeforeEach
    void setup() {
        initMocks(this);
        placeOrderActivity = new PlaceOrderActivity(orderDao, menuItemDao);
    }

    @Test
    void handleRequest_withValidPlaceOrderRequest_addsAnOrderToPersistentLayerAndReturnsPlaceOrderResponse() {
        // GIVEN
        MenuItem menuItem1 = MenuItem.builder()
                .withName("MenuItem1")
                .withPrice(350)
                .withDescription("MenuItemDescription1")
                .build();

        MenuItem menuItem2 = MenuItem.builder()
                .withName("MenuItem2")
                .withPrice(250)
                .withDescription("MenuItemDescription2")
                .build();

        Map<String, MenuItem> menuItemMap = new HashMap<>(
                Map.of(menuItem1.getName(), menuItem1, menuItem2.getName(), menuItem2)
        );

        Map<MenuItem, Integer> mapOfOrderMenuItems = Map.of(menuItem1, 1, menuItem2, 99);

        List<MenuItemModel> listOfMenuItemModels = List.of(
                MenuItemModel.builder()
                        .withName(menuItem1.getName())
                        .withPrice(menuItem1.getPrice())
                        .withDescription(menuItem1.getDescription())
                        .withQuantity(1)
                        .build(),
                MenuItemModel.builder()
                        .withName(menuItem2.getName())
                        .withPrice(menuItem2.getPrice())
                        .withDescription(menuItem2.getDescription())
                        .withQuantity(99)
                        .build()
        );

        LocalDateTime placedDateTime = LocalDateTime.now();

        Order order = Order.builder()
                .withOrderId(OrderUtilities.generateOrderId())
                .withPlacedDate(placedDateTime.toLocalDate())
                .withPlacedTime(placedDateTime.toLocalTime())
                .withOrderMenuItems(mapOfOrderMenuItems)
                .withTotalPrice(OrderUtilities.calculateTotalPrice(mapOfOrderMenuItems))
                .build();


        PlaceOrderRequest placeOrderRequest = PlaceOrderRequest.builder()
                .withOrderItems(
                        Map.of(menuItem1.getName(), 1, menuItem2.getName(), 99)
                ).build();

        when(menuItemDao.getMapOfMenuItems()).thenReturn(menuItemMap);
        when(orderDao.saveOrder(any(Order.class))).thenReturn(order);
        when(context.getLogger()).thenReturn(lambdaLogger);

        // WHEN
        PlaceOrderResponse placeOrderResponse = placeOrderActivity.handleRequest(placeOrderRequest, context);

        // THEN
        assertEquals(placeOrderResponse.getOrderModel().getOrderId(), order.getOrderId());
        assertEquals(placeOrderResponse.getOrderModel().getPlacedDate(), placedDateTime.toLocalDate().toString());
        assertEquals(placeOrderResponse.getOrderModel().getPlacedTime(), placedDateTime.toLocalTime().toString());
        assertEquals(placeOrderResponse.getOrderModel().getOrderMenuItemsList(), listOfMenuItemModels);
        assertEquals(placeOrderResponse.getOrderModel().getTotalPrice(), order.getTotalPrice());
    }

    @Test
    void handleRequest_withInvalidNameInRequest_addsAnOrderToPersistentLayerAndReturnsPlaceOrderResponse() {
        // GIVEN
        MenuItem menuItem1 = MenuItem.builder()
                .withName("MenuItem1")
                .withPrice(350)
                .withDescription("MenuItemDescription1")
                .build();

        MenuItem menuItem2 = MenuItem.builder()
                .withName("MenuItem2")
                .withPrice(250)
                .withDescription("MenuItemDescription2")
                .build();

        Map<String, MenuItem> menuItemMap = new HashMap<>(
                Map.of("menuItem", menuItem1, menuItem2.getName(), menuItem2)
        );

        PlaceOrderRequest placeOrderRequest = PlaceOrderRequest.builder()
                .withOrderItems(
                        Map.of(menuItem1.getName(), 1, menuItem2.getName(), 0)
                ).build();

        when(menuItemDao.getMapOfMenuItems()).thenReturn(menuItemMap);
        when(context.getLogger()).thenReturn(lambdaLogger);

        // WHEN - THEN
        assertThrows(InvalidOrderException.class, () -> placeOrderActivity.handleRequest(placeOrderRequest, context));
    }

    @Test
    void handleRequest_withZeroQuantityInRequest_addsAnOrderToPersistentLayerAndReturnsPlaceOrderResponse() {
        // GIVEN
        MenuItem menuItem1 = MenuItem.builder()
                .withName("MenuItem1")
                .withPrice(350)
                .withDescription("MenuItemDescription1")
                .build();

        MenuItem menuItem2 = MenuItem.builder()
                .withName("MenuItem2")
                .withPrice(250)
                .withDescription("MenuItemDescription2")
                .build();

        Map<String, MenuItem> menuItemMap = new HashMap<>(
                Map.of(menuItem1.getName(), menuItem1, menuItem2.getName(), menuItem2)
        );

        PlaceOrderRequest placeOrderRequest = PlaceOrderRequest.builder()
                .withOrderItems(
                        Map.of(menuItem1.getName(), 1, menuItem2.getName(), 0)
                ).build();

        when(menuItemDao.getMapOfMenuItems()).thenReturn(menuItemMap);
        when(context.getLogger()).thenReturn(lambdaLogger);

        // WHEN - THEN
        assertThrows(InvalidOrderException.class, () -> placeOrderActivity.handleRequest(placeOrderRequest, context));
    }

    @Test
    void handleRequest_withNegativeQuantityInRequest_addsAnOrderToPersistentLayerAndReturnsPlaceOrderResponse() {
        // GIVEN
        MenuItem menuItem1 = MenuItem.builder()
                .withName("MenuItem1")
                .withPrice(350)
                .withDescription("MenuItemDescription1")
                .build();

        MenuItem menuItem2 = MenuItem.builder()
                .withName("MenuItem2")
                .withPrice(250)
                .withDescription("MenuItemDescription2")
                .build();

        Map<String, MenuItem> menuItemMap = new HashMap<>(
                Map.of(menuItem1.getName(), menuItem1, menuItem2.getName(), menuItem2)
        );

        PlaceOrderRequest placeOrderRequest = PlaceOrderRequest.builder()
                .withOrderItems(
                        Map.of(menuItem1.getName(), 1, menuItem2.getName(), -1)
                ).build();

        when(menuItemDao.getMapOfMenuItems()).thenReturn(menuItemMap);
        when(context.getLogger()).thenReturn(lambdaLogger);

        // WHEN - THEN
        assertThrows(InvalidOrderException.class, () -> placeOrderActivity.handleRequest(placeOrderRequest, context));
    }

    @Test
    void handleRequest_withMaxQuantityInRequest_addsAnOrderToPersistentLayerAndReturnsPlaceOrderResponse() {
        // GIVEN
        MenuItem menuItem1 = MenuItem.builder()
                .withName("MenuItem1")
                .withPrice(350)
                .withDescription("MenuItemDescription1")
                .build();

        MenuItem menuItem2 = MenuItem.builder()
                .withName("MenuItem2")
                .withPrice(250)
                .withDescription("MenuItemDescription2")
                .build();

        Map<String, MenuItem> menuItemMap = new HashMap<>(
                Map.of(menuItem1.getName(), menuItem1, menuItem2.getName(), menuItem2)
        );

        PlaceOrderRequest placeOrderRequest = PlaceOrderRequest.builder()
                .withOrderItems(
                        Map.of(menuItem1.getName(), 1, menuItem2.getName(), 100)
                ).build();

        when(menuItemDao.getMapOfMenuItems()).thenReturn(menuItemMap);
        when(context.getLogger()).thenReturn(lambdaLogger);

        // WHEN - THEN
        assertThrows(InvalidOrderException.class, () -> placeOrderActivity.handleRequest(placeOrderRequest, context));
    }
}
