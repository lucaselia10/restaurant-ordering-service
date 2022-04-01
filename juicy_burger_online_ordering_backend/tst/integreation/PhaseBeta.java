package integreation;

import data.requests.PlaceOrderRequest;
import data.responses.PlaceOrderResponse;
import integreation.providers.TestDataProvider;

import java.util.Map;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.MethodOrderer.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class PhaseBeta {
    private TestDataProvider testDataProvider;
    private String generatedOrderId = null;

    @BeforeEach
    void setup() {
        testDataProvider = new TestDataProvider();
    }

    @Test
    @Order(1)
    void placeOrderActivity_withPlaceOrderRequest_returnsPlaceOrderResponse() {
        // GIVEN
        Map<String, Integer> orderDescription = Map.of(
                "Juicy Burger", 12,
                "Juicy Cheeseburger", 34,
                "Chicken Nuggets", 56,
                "Sprite", 78
        );

        PlaceOrderRequest request = testDataProvider.createPlaceOrderRequest(orderDescription);

        // WHEN
        PlaceOrderResponse response = testDataProvider.createOrder(request);
        generatedOrderId = response.getOrderModel().getOrderId();

        // THEN

    }

    @Test
    @Order(2)
    void updateOrderActivity_withUpdateOrderRequest_returnsUpdateOrderResponse() {
        // GIVEN
        Map<String, Integer> orderDescription = Map.of(
                "Juicy Burger", 12,
                "Juicy Cheeseburger", 34,
                "Chicken Nuggets", 56,
                "Sprite", 78
        );
    }
}
