package integreation.providers;

import activites.DeleteOrderActivity;
import activites.PlaceOrderActivity;
import activites.UpdateOrderActivity;
import dependencies.DaggerServices;
import dependencies.Services;

public class TestServiceProvider {
    private static final Services DAGGER = DaggerServices.create();

    private TestServiceProvider() {}

    public static PlaceOrderActivity providePlaceOrderActivity() {
        return DAGGER.providesPlaceOrderActivity();
    }

    public static DeleteOrderActivity provideDeleteOrderActivity() {
        return DAGGER.providesDeleteOrderActivity();
    }

    public static UpdateOrderActivity provideUpdateOrderActivity() {
        return DAGGER.providesUpdateOrderActivity();
    }
}
