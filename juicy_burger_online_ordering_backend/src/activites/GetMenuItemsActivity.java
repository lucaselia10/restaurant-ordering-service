package activites;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import daos.MenuItemDao;
import data.requests.GetMenuItemsRequest;
import data.responses.GetMenuItemsResponse;

import javax.inject.Inject;

/**
 * Defines the behavior of returning MenuItems. Accepts a GetMenuItemsRequest and
 * returns a GetMenuItemsResponse.
 */
public class GetMenuItemsActivity implements RequestHandler<GetMenuItemsRequest, GetMenuItemsResponse> {
    private MenuItemDao menuItemDao;

    @Inject
    public GetMenuItemsActivity(MenuItemDao menuItemDao) {
        this.menuItemDao = menuItemDao;
    }

    // TODO: This method needs to be implemented (Possibly out of Scope)
    @Override
    public GetMenuItemsResponse handleRequest(GetMenuItemsRequest request, Context context) {
        return null;
    }
}
