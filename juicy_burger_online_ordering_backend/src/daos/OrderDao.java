package daos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import data.types.Order;

import exceptions.OrderDoesNotExistException;
import exceptions.OrderException;

import javax.inject.Inject;

/**
 * OrderDao defines the characteristics and behavior of a read and write
 * data access object for Order objects.
 * @author willi
 */
public class OrderDao {
    private DynamoDBMapper dynamoDBMapper;

    @Inject
    public OrderDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     * Adds an Order to the persistent layer
     * @param order the Order object to add to the persistent layer
     * @return Order the order within the persistent layer
     * @throws OrderException when Order cannot be saved to DynamoDB
     */
    public Order saveOrder(Order order) {
        try {
            this.dynamoDBMapper.save(order);
        } catch (Exception e) {
            throw new OrderException("Could not save to DynamoDB", e);
        }
        return order;
    }

    /**
     * Retrieves an Order from the persistent layer
     * @param partitionKey String the orderId
     * @return Order the object retrieved
     * @throws OrderDoesNotExistException when an Order does not exist
     */
    public Order getOrder(String partitionKey) {
        Order orderToReturn = this.dynamoDBMapper.load(Order.class, partitionKey);
        if (orderToReturn == null) {
            throw new OrderDoesNotExistException();
        }
        return orderToReturn;
    }

    /**
     * Removes an Order from the persistent layer
     * @param partitionKey String the orderId
     * @throws OrderDoesNotExistException when an Order does not exist
     */
    public boolean deleteOrder(String partitionKey) {
        Order orderToDelete = Order.builder()
                .withOrderId(partitionKey)
                .build();

        DynamoDBQueryExpression deleteExpression = new DynamoDBQueryExpression();
        return false;

    }

    /**
     * Updates an Order within the persistent layer
     * @param order The updated Order object
     * @throws OrderDoesNotExistException when an Order does not exist
     */
    public void updateOrder(Order order) {
        Order orderToUpdate = this.getOrder(order.getOrderId());

        this.saveOrder(order);
    }
}
