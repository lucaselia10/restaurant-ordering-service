package activites;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import daos.MenuItemDao;
import daos.OrderDao;
import data.requests.PlaceOrderRequest;
import data.responses.PlaceOrderResponse;

import dependencies.JSONParserModule;
import org.json.simple.parser.JSONParser;

import javax.inject.Inject;

public class PlaceOrderActivity implements RequestHandler<PlaceOrderRequest, PlaceOrderResponse> {
    private OrderDao orderDao;
    private MenuItemDao menuItemDao;

    @Inject
    public PlaceOrderActivity(OrderDao orderDao, MenuItemDao menuItemDao) {
        this.orderDao = orderDao;
        this.menuItemDao = menuItemDao;
    }

    @Override
    public PlaceOrderResponse handleRequest(PlaceOrderRequest request, Context context) {
        return null;
    }
}
