package integreation;

import data.requests.DeleteOrderRequest;
import data.requests.GetOrderRequest;
import data.requests.PlaceOrderRequest;
import data.requests.UpdateOrderRequest;
import data.responses.DeleteOrderResponse;
import data.responses.GetOrderResponse;
import data.responses.PlaceOrderResponse;
import data.responses.UpdateOrderResponse;
import exceptions.OrderDoesNotExistException;
import integreation.providers.TestDataProvider;

import java.util.Map;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.MethodOrderer.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PhaseBeta tests runs through all the base case scenarios
 * for all the endpoints
 */
@TestMethodOrder(OrderAnnotation.class)
public class PhaseBeta {
    private TestDataProvider testDataProvider;
    private static String generatedOrderId = null;

    @BeforeEach
    void setup() {
        testDataProvider = new TestDataProvider();
    }

    @Test
    @Order(1)
    void placeOrderActivity_withPlaceOrderRequest_returnsPlaceOrderResponse() {
        // GIVEN
        Map<String, Integer> orderDescription = Map.of(
                "Juicy Cheeseburger", 12,
                "Juicy Bacon Cheeseburger", 34,
                "Chicken Nuggets", 56,
                "Sprite", 78
        );

        PlaceOrderRequest request = testDataProvider.createPlaceOrderRequest(orderDescription);

        // WHEN
        PlaceOrderResponse response = testDataProvider.placeOrder(request);
        generatedOrderId = response.getOrderModel().getOrderId();

        // THEN
        assertEquals("Chicken Nuggets", response.getOrderModel().getOrderMenuItemsList().get(0).getName());
        assertEquals(56, response.getOrderModel().getOrderMenuItemsList().get(0).getQuantity());
        assertEquals("Juicy Bacon Cheeseburger", response.getOrderModel().getOrderMenuItemsList().get(1).getName());
        assertEquals(34, response.getOrderModel().getOrderMenuItemsList().get(1).getQuantity());
        assertEquals("Juicy Cheeseburger", response.getOrderModel().getOrderMenuItemsList().get(2).getName());
        assertEquals(12, response.getOrderModel().getOrderMenuItemsList().get(2).getQuantity());
        assertEquals("Sprite", response.getOrderModel().getOrderMenuItemsList().get(3).getName());
        assertEquals(78, response.getOrderModel().getOrderMenuItemsList().get(3).getQuantity());
    }

    @Test
    @Order(2)
    void updateOrderActivity_withUpdateOrderRequest_returnsUpdateOrderResponse() {
        // GIVEN
        Map<String, Integer> orderDescription = Map.of(
                "Juicy Cheeseburger", 1,
                "Juicy Bacon Cheeseburger", 3,
                "Chicken Nuggets", 5,
                "Sprite", 7
        );

        UpdateOrderRequest updateOrderRequest =
                testDataProvider.createUpdateOrderRequest(generatedOrderId, orderDescription);

        // WHEN
        UpdateOrderResponse updateOrderResponse = testDataProvider.updateOrder(updateOrderRequest);

        // THEN
        assertEquals("Chicken Nuggets", updateOrderResponse.getOrderModel().getOrderMenuItemsList().get(0).getName());
        assertEquals(5, updateOrderResponse.getOrderModel().getOrderMenuItemsList().get(0).getQuantity());
        assertEquals("Juicy Bacon Cheeseburger", updateOrderResponse.getOrderModel().getOrderMenuItemsList().get(1).getName());
        assertEquals(3, updateOrderResponse.getOrderModel().getOrderMenuItemsList().get(1).getQuantity());
        assertEquals("Juicy Cheeseburger", updateOrderResponse.getOrderModel().getOrderMenuItemsList().get(2).getName());
        assertEquals(1, updateOrderResponse.getOrderModel().getOrderMenuItemsList().get(2).getQuantity());
        assertEquals("Sprite", updateOrderResponse.getOrderModel().getOrderMenuItemsList().get(3).getName());
        assertEquals(7, updateOrderResponse.getOrderModel().getOrderMenuItemsList().get(3).getQuantity());
    }

    @Test
    @Order(3)
    void getOrderActivity_withGetOrderRequest_returnsGetOrderResponse() {
        // GIVEN
        GetOrderRequest getOrderRequest = testDataProvider.createGetOrderRequest(generatedOrderId);

        // WHEN
        GetOrderResponse getOrderResponse = testDataProvider.getOrder(getOrderRequest);

        // THEN
        assertEquals("Chicken Nuggets", getOrderResponse.getOrderModel().getOrderMenuItemsList().get(0).getName());
        assertEquals(5, getOrderResponse.getOrderModel().getOrderMenuItemsList().get(0).getQuantity());
        assertEquals("Juicy Bacon Cheeseburger", getOrderResponse.getOrderModel().getOrderMenuItemsList().get(1).getName());
        assertEquals(3, getOrderResponse.getOrderModel().getOrderMenuItemsList().get(1).getQuantity());
        assertEquals("Juicy Cheeseburger", getOrderResponse.getOrderModel().getOrderMenuItemsList().get(2).getName());
        assertEquals(1,getOrderResponse.getOrderModel().getOrderMenuItemsList().get(2).getQuantity());
        assertEquals("Sprite", getOrderResponse.getOrderModel().getOrderMenuItemsList().get(3).getName());
        assertEquals(7, getOrderResponse.getOrderModel().getOrderMenuItemsList().get(3).getQuantity());
    }

    @Test
    @Order(4)
    void deleteOrderActivity_withDeleteOrderRequest_returnsDeleteOrderResponse() {
        // GIVEN
        DeleteOrderRequest deleteOrderRequest = testDataProvider.createDeleteOrderRequest(generatedOrderId);

        // WHEN
        DeleteOrderResponse deleteOrderResponse = testDataProvider.deleteOrder(deleteOrderRequest);

        // THEN
        assertTrue(deleteOrderResponse.isDidItWork());
    }

    @Test
    @Order(5)
    void getOrderActivity_withGetOrderRequest_throwsOrderDoesNotExistException() {
        // GIVEN
        GetOrderRequest getOrderRequest = testDataProvider.createGetOrderRequest(generatedOrderId);

        // WHEN - THEN
        assertThrows(OrderDoesNotExistException.class, () -> testDataProvider.getOrder(getOrderRequest));
    }
}
