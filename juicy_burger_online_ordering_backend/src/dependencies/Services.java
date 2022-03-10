package dependencies;

import activites.PlaceOrderActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component
public interface Services {

    PlaceOrderActivity providesPlaceOrderActivity();
}
