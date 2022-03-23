package daos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import data.types.Order;
import exceptions.OrderDoesNotExistException;
<<<<<<< HEAD
=======

>>>>>>> origin/PlaceOrderActivityFeature
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
<<<<<<< HEAD
     * Adds an Order to the database
=======
     * Adds an Order to the persistent layer
>>>>>>> origin/PlaceOrderActivityFeature
     * @param order the Order object to add to the persistent layer
     */
    public void saveOrder(Order order) {
        this.dynamoDBMapper.save(order);
    }

    /**
<<<<<<< HEAD
     * Retrieves an Order from the database
=======
     * Retrieves an Order from the persistent layer
>>>>>>> origin/PlaceOrderActivityFeature
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
<<<<<<< HEAD
     * Removes an Order from the database
=======
     * Removes an Order from the persistent layer
>>>>>>> origin/PlaceOrderActivityFeature
     * @param partitionKey String the orderId
     * @throws OrderDoesNotExistException when an Order does not exist
     */
    public void deleteOrder(String partitionKey) {
        Order toDelete = this.getOrder(partitionKey);
        this.dynamoDBMapper.delete(toDelete);
    }

    // TODO: Need to flush out this function
    /**
<<<<<<< HEAD
     * Updates an Order from the database
=======
     * Updates an Order within the persistent layer
>>>>>>> origin/PlaceOrderActivityFeature
     * @param order The updated Order object
     * @throws OrderDoesNotExistException when an Order does not exist
     */
    public void updateOrder(Order order) {
        Order orderToUpdate = this.getOrder(order.getOrderId());

        this.saveOrder(order);
    }
}
