package daos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import data.types.Order;
import exceptions.OrderDoesNotExistException;

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
        Order orderToReturn = this.dynamoDBMapper.load(Order.class, partitionKey);
        if (orderToReturn == null) {
            throw new OrderDoesNotExistException();
        }
        return orderToReturn;
    }

    public void deleteOrder(String partitionKey) {
        Order toDelete = this.getOrder(partitionKey);
        this.dynamoDBMapper.delete(toDelete);
    }

    public void updateOrder(Order order) {
        Order orderToUpdate = this.getOrder(order.getOrderId());
        this.saveOrder(order);
    }
}
