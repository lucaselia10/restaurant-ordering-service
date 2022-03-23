package dependencies;

import activites.DeleteOrderActivity;
import activites.GetMenuItemsActivity;
import activites.PlaceOrderActivity;
import activites.UpdateOrderActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
<<<<<<< HEAD
 *
=======
 * Services is the interface for Dagger dependency injection
>>>>>>> origin/PlaceOrderActivityFeature
 */
@Singleton
@Component(modules = {DynamoDBModule.class, FileReaderModule.class, JSONParserModule.class})
public interface Services {

    PlaceOrderActivity providesPlaceOrderActivity();

    UpdateOrderActivity providesUpdateOrderActivity();

    DeleteOrderActivity providesDeleteOrderActivity();

    GetMenuItemsActivity providesGetMenuItemsActivity();
}
