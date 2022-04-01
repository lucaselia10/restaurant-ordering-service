package integreation.providers;

import activites.*;

import com.amazonaws.services.lambda.runtime.Context;
import data.requests.DeleteOrderRequest;
import data.requests.GetOrderRequest;
import data.requests.PlaceOrderRequest;
import data.requests.UpdateOrderRequest;
import data.responses.DeleteOrderResponse;
import data.responses.GetOrderResponse;
import data.responses.PlaceOrderResponse;
import data.responses.UpdateOrderResponse;
import integreation.contexts.TestContext;

import java.util.Map;

public class TestDataProvider {
    private final PlaceOrderActivity placeOrderActivity = TestServiceProvider.providePlaceOrderActivity();
    private final DeleteOrderActivity deleteOrderActivity = TestServiceProvider.provideDeleteOrderActivity();
    private final UpdateOrderActivity updateOrderActivity = TestServiceProvider.provideUpdateOrderActivity();
    private final GetOrderActivity getOrderActivity = TestServiceProvider.provideGetOrderActivity();
    private final Context testContext = new TestContext();

    public PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest) {
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

    public GetOrderResponse getOrder(GetOrderRequest getOrderRequest) {
        return getOrderActivity.handleRequest(getOrderRequest, testContext);
    }

    public GetOrderRequest createGetOrderRequest(String orderId) {
        return new GetOrderRequest().builder()
                .withOrderId(orderId)
                .build();
    }

    public DeleteOrderResponse deleteOrder(DeleteOrderRequest deleteOrderRequest) {
        return deleteOrderActivity.handleRequest(deleteOrderRequest, testContext);
    }

    public DeleteOrderRequest createDeleteOrderRequest(String orderId) {
        return new DeleteOrderRequest().builder()
                .withOrderId(orderId)
                .build();
    }
}
