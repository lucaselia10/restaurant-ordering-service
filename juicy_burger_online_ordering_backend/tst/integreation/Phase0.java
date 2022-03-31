package integreation;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
public class Phase0 {
    private static final String TABLE_NAME = "OrderHistory";

    private final DynamoDB client = new DynamoDB(AmazonDynamoDBClientBuilder
            .standard()
            .withRegion(Regions.US_WEST_2)
            .build());

    @ParameterizedTest
    @ValueSource(strings = {TABLE_NAME})
    void expectedTable_exists(String tableName) {
        assertTableExists(tableName);
    }

    private void assertTableExists(String tableName) {
        for (Table table : client.listTables()) {
            if (table.getTableName().equals(tableName)) {
                return;
            }
        }
        fail(String.format("Did not find expected table: %s", tableName));
    }
}
