package converters;

import comprators.MenuItemModelNameComparator;
import daos.MenuItemDao;
import data.types.MenuItem;
import data.types.Order;

import data.types.models.MenuItemModel;
import data.types.models.OrderModel;
import dependencies.FileReaderModule;
import dependencies.JSONParserModule;

import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.OrderUtilities;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ModelConverterTest {
    private MenuItemDao menuItemDao;

    @BeforeEach
    void setup() {
        try {
            menuItemDao = new MenuItemDao(
                    new JSONParser(),
                    new FileReader("./src/resources/menuItems.json")
            );
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void orderModelConverter_withOrder_returnsOrderModel() {
        // GIVEN
        List<MenuItem> menuItemList = menuItemDao.getListOfMenuItems();

        LocalDateTime now = LocalDateTime.now();

        Order actualOrder = Order.builder()
                .withOrderId("OrderTest1")
                .withPlacedDateTime(now)
                .withProcessDateTime(null)
                .withCompletedDateTime(null)
                .withOrderMenuItems(Map.of(menuItemList.get(0), 1, menuItemList.get(1), 2))
                .withTotalPrice(OrderUtilities.calculateTotalPrice(
                        Map.of(menuItemList.get(0), 1, menuItemList.get(1), 2))
                )
                .build();

        OrderModel expected = OrderModel.builder()
                .withOrderId(actualOrder.getOrderId())
                .withPlacedDateTime(actualOrder.getPlacedDateTime().toString())
                .withProcessDateTime("")
                .withCompletedDateTime("")
                .withOrderMenuItemsList(List.of(
                        MenuItemModel.builder()
                                .withName(menuItemList.get(1).getName())
                                .withPrice(menuItemList.get(1).getPrice())
                                .withDescription(menuItemList.get(1).getDescription())
                                .withQuantity(2)
                                .build(),
                        MenuItemModel.builder()
                                .withName(menuItemList.get(0).getName())
                                .withPrice(menuItemList.get(0).getPrice())
                                .withDescription(menuItemList.get(0).getDescription())
                                .withQuantity(1)
                                .build()
                        )
                )
                .withTotalPrice(actualOrder.getTotalPrice())
                .build();

        // WHEN
        OrderModel actual = ModelConverter.orderModelConverter(actualOrder);

        // THEN
        assertEquals(expected, actual);
    }

    @Test
    void orderMenuItemMapConverter_withOrderMenuItemsMap_returnsListOfMenuItemModels() {
        // GIVEN
        List<MenuItem> menuItemList = menuItemDao.getListOfMenuItems();
        List<MenuItemModel> expected = Arrays.asList(
                MenuItemModel.builder()
                        .withName(menuItemList.get(0).getName())
                        .withPrice(menuItemList.get(0).getPrice())
                        .withDescription(menuItemList.get(0).getDescription())
                        .withQuantity(1)
                        .build(),
                MenuItemModel.builder()
                        .withName(menuItemList.get(1).getName())
                        .withPrice(menuItemList.get(1).getPrice())
                        .withDescription(menuItemList.get(1).getDescription())
                        .withQuantity(2)
                        .build()
        );
        Map<MenuItem, Integer> menuItemIntegerMap = Map.of(menuItemList.get(1), 2, menuItemList.get(0), 1);

        // WHEN
        List<MenuItemModel> actual = ModelConverter.orderMenuItemMapConverter(menuItemIntegerMap);
        expected.sort(new MenuItemModelNameComparator());
        actual.sort(new MenuItemModelNameComparator());

        // THEN
        assertEquals(expected, actual);
    }
}
