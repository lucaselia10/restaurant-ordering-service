package daos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import data.types.Order;

import exceptions.OrderDoesNotExistException;
import exceptions.OrderException;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
            throw new OrderException("Could not save to DynamoDB!", e);
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
            throw new OrderDoesNotExistException("Order does not exist!");
        }
        return orderToReturn;
    }

    /**
     * Retrieves a List of Orders by specified placed Date
     * @param placedDate String of Date in (YYYY-MM-DD) format
     * @return List of Orders
     */
    public List<Order> getOrdersByPlacedDate(String placedDate) {
        Order order = Order.builder()
                .withPlacedDate(LocalDate.parse(placedDate))
                .build();

        DynamoDBQueryExpression<Order> queryExpression = new DynamoDBQueryExpression<Order>()
                .withHashKeyValues(order)
                .withConsistentRead(false)
                .withIndexName(Order.PLACED_DATE_TIME_INDEX);

        return new ArrayList<>(dynamoDBMapper.query(Order.class, queryExpression));
    }

    /**
     * Removes an Order from the persistent layer
     * @param partitionKey String the orderId
     * @throws OrderDoesNotExistException when an Order does not exist
     */
    public void deleteOrder(String partitionKey) {
        Order toDelete = this.getOrder(partitionKey);
        this.dynamoDBMapper.delete(toDelete);
    }
}
