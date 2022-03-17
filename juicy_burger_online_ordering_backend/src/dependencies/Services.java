package dependencies;

import activites.PlaceOrderActivity;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Services is the interface for Dagger dependency injection
 */
@Singleton
@Component(modules = {DynamoDBModule.class, FileReaderModule.class, JSONParserModule.class})
public interface Services {

    PlaceOrderActivity providesPlaceOrderActivity();
}
