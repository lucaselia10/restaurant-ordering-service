package providers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import data.requests.GetMenuItemsRequest;
import data.responses.GetMenuItemsResponse;

import dependencies.DaggerServices;
import dependencies.Services;

public class GetMenuItemsActivityProvider implements RequestHandler<GetMenuItemsRequest, GetMenuItemsResponse> {
    private Services services;

    public GetMenuItemsActivityProvider() {}

    @Override
    public GetMenuItemsResponse handleRequest(GetMenuItemsRequest request, Context context) {
        return getServices().providesGetMenuItemsActivity().handleRequest(request, context);
    }

    private Services getServices() {
        if (services == null) {
            services = DaggerServices.create();
        }
        return services;
    }
}
