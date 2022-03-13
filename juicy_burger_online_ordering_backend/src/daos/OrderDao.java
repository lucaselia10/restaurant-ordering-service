package daos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import data.types.Order;

import javax.inject.Inject;

/**
 *
 */
public class OrderDao {
    private DynamoDBMapper dynamoDBMapper;

    @Inject
    public OrderDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void saveOrder(Order order) {
        this.dynamoDBMapper.save(order);
    }

    public Order getOrder(String partitionKey) {
        return this.dynamoDBMapper.load(Order.class, partitionKey);
    }
}
