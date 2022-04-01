package integreation;

import activites.PlaceOrderActivity;
import data.requests.PlaceOrderRequest;
import data.responses.PlaceOrderResponse;
import integreation.providers.TestDataProvider;
import integreation.providers.TestServiceProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PhaseBeta {
    private PlaceOrderActivity placeOrderActivity;
    private TestDataProvider testDataProvider;

    @BeforeEach
    void setup() {
        placeOrderActivity = TestServiceProvider.providePlaceOrderActivity();
        testDataProvider = new TestDataProvider();
    }

    @Test
    void placeOrderActivity_withPlaceOrderRequest_returnsPlaceOrderResponse() {
        // GIVEN
        PlaceOrderRequest expected = testDataProvider.createPlaceOrderRequest();

        // WHEN
        PlaceOrderResponse actual = testDataProvider.createOrder();

        // THEN
    }
}
