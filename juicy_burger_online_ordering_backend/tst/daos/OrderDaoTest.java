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

import static org.mockito.MockitoAnnotations.initMocks;

// TODO: Not an actual Unit Test. Needs to be reimplemented.
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


        Order order1 = Order.builder()
                .withOrderId(OrderUtilities.generateOrderId())
                .withOrderMenuItems(Map.of(menuItems.get(0), 2, menuItems.get(1), 1))
                .withPlacedDateTime(LocalDateTime.now())
                .withProcessDateTime(LocalDateTime.now().plusMinutes(10))
                .withCompletedDateTime(LocalDateTime.now().plusMinutes(20))
                .withTotalPrice(OrderUtilities.calculateTotalPrice(
                        Map.of(menuItems.get(0), 2, menuItems.get(1), 1))
                )
                .build();


        MenuItemsQuantityMapConverter menuItemsQuantityMapConverter = new MenuItemsQuantityMapConverter();

        String answer = menuItemsQuantityMapConverter.convert(order1.getOrderMenuItems());
        System.out.println("Converting MenuItem to String for DynamoDB");
        System.out.println(answer);
        System.out.println();
        System.out.println("Unconverting from DynamoDB String to MenuItem");
        System.out.println(menuItemsQuantityMapConverter.unconvert(answer));
        System.out.println();
        System.out.println("Saving Order to Database");
        orderDao.saveOrder(order1);
        System.out.println();
        System.out.println("Retrieving Order from Database:");
        System.out.println(orderDao.getOrder(order1.getOrderId()));
//        System.out.println("Deleting Order from Database:");
//        orderDao.deleteOrder(order1.getOrderId());
    }
}
