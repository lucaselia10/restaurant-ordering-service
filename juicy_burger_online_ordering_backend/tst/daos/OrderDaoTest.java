package daos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import converters.dynamodbtypeconverters.MenuItemsQuantityMapConverter;
import data.types.MenuItem;
import data.types.Order;
import dependencies.DynamoDBModule;
import dependencies.FileReaderModule;
import dependencies.JSONParserModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import utilities.OrderUtilities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.jupiter.api.Assertions.*;

public class OrderDaoTest {
    private OrderDao orderDao;

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @BeforeEach
    void setup() {
        initMocks(this);
        orderDao = new OrderDao(dynamoDBMapper);
    }

    @Test

    public static void main(String[] args) {
        OrderDao orderDao = new OrderDao(new DynamoDBModule().dynamoDBMapperProvider());
        MenuItemDao menuItemDao = null;

        try {
            menuItemDao = new MenuItemDao(
                    new JSONParserModule().JSONParseProvider(),
                    new FileReaderModule().fileReaderProvider()
            );
        } catch (Exception e) {
            System.err.println("Bad File Path: " + e.getMessage());
        }

        List<MenuItem> menuItems = menuItemDao.getListOfMenuItems();

        LocalDateTime placedDateTime = LocalDateTime.now();

        Order order1 = Order.builder()
                .withOrderId(OrderUtilities.generateOrderId())
                .withOrderMenuItems(Map.of(menuItems.get(0), 2, menuItems.get(1), 1))
                .withPlacedDate(placedDateTime.toLocalDate().plusDays(5))
                .withPlacedTime(placedDateTime.toLocalTime())
                .withTotalPrice(OrderUtilities.calculateTotalPrice(
                        Map.of(menuItems.get(0), 2, menuItems.get(1), 1))
                )
                .build();

        MenuItemsQuantityMapConverter menuItemsQuantityMapConverter = new MenuItemsQuantityMapConverter();

//        String answer = menuItemsQuantityMapConverter.convert(order1.getOrderMenuItemsMap());
//        System.out.println("Converting MenuItem to String for DynamoDB");
//        System.out.println(answer);
//        System.out.println();
//        System.out.println("Unconverting from DynamoDB String to MenuItem");
//        System.out.println(menuItemsQuantityMapConverter.unconvert(answer));
//        System.out.println();
//        System.out.println("Saving Order to Database");
//        orderDao.saveOrder(order1);
//        System.out.println();
//        System.out.println("Retrieving Order from Database:");
//        System.out.println(orderDao.getOrder(order1.getOrderId()));
//        System.out.println();
//
        System.out.println("Getting Orders by Date");
        List<Order> orders = orderDao.getOrdersByPlacedDate("2022-03-30");
        System.out.println(orders.size());
        System.out.println(orders);
    }
}
