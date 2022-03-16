package dependencies;

import activites.PlaceOrderActivity;
import dagger.Component;

import javax.inject.Singleton;

/**
 *
 */
@Singleton
@Component(modules = {DynamoDBModule.class, FileReaderModule.class, JSONParserModule.class})
public interface Services {

    PlaceOrderActivity providesPlaceOrderActivity();
}
