package daos;

import converters.dynamodbtypeconverters.MenuItemsMapConverter;
import data.types.MenuItem;
import data.types.Order;
import dependencies.DynamoDBModule;
import dependencies.FileReaderModule;
import dependencies.JSONParserModule;
import utilities.OrderUtilities;

import java.util.List;
import java.util.Map;

// TODO: Not an actual Unit Test. Needs to be reimplemented.
public class OrderDaoTest {


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

        List<MenuItem> menuItems = menuItemDao.getMenuItems();


        Order order1 = Order.builder()
                .withOrderId(OrderUtilities.generateOrderId())
                .withOrderMenuItems(Map.of(menuItems.get(0), 2L, menuItems.get(1), 1L))
                .withPlacedDateTime(OrderUtilities.generateDateTimeNow())
                .build();


        MenuItemsMapConverter menuItemsMapConverter = new MenuItemsMapConverter();

        String answer = menuItemsMapConverter.convert(order1.getOrderMenuItems());
        System.out.println("Converting MenuItem to String for DynamoDB");
        System.out.println(answer);
        System.out.println();
        System.out.println("Unconverting from DynamoDB String to MenuItem");
        System.out.println(menuItemsMapConverter.unconvert(answer));
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
