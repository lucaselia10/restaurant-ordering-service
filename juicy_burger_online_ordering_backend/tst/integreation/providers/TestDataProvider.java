package integreation.providers;

import activites.DeleteOrderActivity;
import activites.PlaceOrderActivity;
import activites.UpdateOrderActivity;

import com.amazonaws.services.lambda.runtime.Context;
import data.requests.PlaceOrderRequest;
import data.responses.PlaceOrderResponse;
import integreation.contexts.TestContext;

import java.util.Map;

public class TestDataProvider {
    private final PlaceOrderActivity placeOrderActivity = TestServiceProvider.providePlaceOrderActivity();
    private final DeleteOrderActivity deleteOrderActivity = TestServiceProvider.provideDeleteOrderActivity();
    private final UpdateOrderActivity updateOrderActivity = TestServiceProvider.provideUpdateOrderActivity();
    private final Context testContext = new TestContext();

    public PlaceOrderResponse createOrder() {
        return createOrder(createPlaceOrderRequest());
    }

    public PlaceOrderResponse createOrder(PlaceOrderRequest placeOrderRequest) {
        return placeOrderActivity.handleRequest(placeOrderRequest, testContext);
    }

    public PlaceOrderRequest createPlaceOrderRequest() {
        return PlaceOrderRequest.builder()
                .withOrderItems(
                        Map.of("Juicy Cheeseburger", 10_000)
                )
                .build();
    }
}
