package daos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dependencies.Order;
import exceptions.OrderDoesNotExistException;

public class OrderDao {
    private final DynamoDBMapper dynamoDBMapper;

    public OrderDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Order getOrder(String id) {
        Order order = this.dynamoDBMapper.load(Order.class, id);
        if (order == null) {
            throw new OrderDoesNotExistException("Could not find order with id " + id);
        }
        return order;
    }
}
