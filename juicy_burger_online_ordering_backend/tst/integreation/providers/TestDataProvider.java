package integreation.providers;

import activites.DeleteOrderActivity;
import activites.PlaceOrderActivity;
import activites.UpdateOrderActivity;

import com.amazonaws.services.lambda.runtime.Context;
import data.requests.DeleteOrderRequest;
import data.requests.PlaceOrderRequest;
import data.requests.UpdateOrderRequest;
import data.responses.PlaceOrderResponse;
import data.responses.UpdateOrderResponse;
import integreation.contexts.TestContext;

import java.util.Map;

public class TestDataProvider {
    private final PlaceOrderActivity placeOrderActivity = TestServiceProvider.providePlaceOrderActivity();
    private final DeleteOrderActivity deleteOrderActivity = TestServiceProvider.provideDeleteOrderActivity();
    private final UpdateOrderActivity updateOrderActivity = TestServiceProvider.provideUpdateOrderActivity();
    private final Context testContext = new TestContext();

    public PlaceOrderResponse createOrder(PlaceOrderRequest placeOrderRequest) {
        return placeOrderActivity.handleRequest(placeOrderRequest, testContext);
    }

    public PlaceOrderRequest createPlaceOrderRequest(Map<String, Integer> orderDescription) {
        return PlaceOrderRequest.builder()
                .withOrderItems(orderDescription)
                .build();
    }

    public UpdateOrderResponse updateOrder(UpdateOrderRequest updateOrderRequest) {
        return updateOrderActivity.handleRequest(updateOrderRequest, testContext);
    }




    public UpdateOrderRequest createUpdateOrderRequest(String orderId, Map<String, Integer> orderDescription) {
        return UpdateOrderRequest.builder()
                .withOrderId(orderId)
                .withOrderItems(orderDescription)
                .build();
    }

    public DeleteOrderRequest createDeleteOrderRequest(String orderId) {
        return null;
    }
}
