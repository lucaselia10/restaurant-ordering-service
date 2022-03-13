package dependencies;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DynamoDBModule {

    /**
     * Dagger module to provide a DynamoDBMapper Object
     * @return DynamoDBMapper the mapper to be returned
     */
    @Singleton
    @Provides
    public DynamoDBMapper dynamoDBMapperProvider() {
        return new DynamoDBMapper(
                AmazonDynamoDBClient.builder()
                        .withRegion(Regions.US_WEST_2)
                        .build()
        );
    }
}
