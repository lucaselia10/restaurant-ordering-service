package converters;

import comprators.MenuItemModelNameComparator;
import data.types.MenuItem;
import data.types.Order;
import data.types.models.MenuItemModel;
import data.types.models.OrderModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A converter class that provides helper methods for converting MenuItems or Order types
 * to its Model equivalent. Do not instantiate. Do not instantiate.
 */
public class ModelConverter {

    private ModelConverter() {}

    /**
     * Converts an Order to a OrderModel
     * @param order Order to convert
     * @return OrderModel
     */
    public static OrderModel orderModelConverter(Order order) {
        return OrderModel.builder()
                .withOrderId(order.getOrderId())
                .withPlacedDateTime(order.getPlacedDateTime().toString())
                .withProcessDateTime(
                        order.getProcessDateTime() == null ? "" : order.getProcessDateTime().toString()
                )
                .withCompletedDateTime(
                        order.getCompletedDateTime() == null ? "" : order.getCompletedDateTime().toString()
                )
                .withOrderMenuItemsList(orderMenuItemMapConverter(order.getOrderMenuItemsMap()))
                .withTotalPrice(order.getTotalPrice())
                .build();
    }

    /**
     * Converts a MenuItem Integer Map to a MenuItemModel list sorted
     * lexicographically by name
     * @param orderMenuItemsMap
     * @return
     */
    public static List<MenuItemModel> orderMenuItemMapConverter(Map<MenuItem, Integer> orderMenuItemsMap) {
        List<MenuItemModel> menuItemModelList = new ArrayList<>();
        for (Map.Entry<MenuItem, Integer> entry : orderMenuItemsMap.entrySet()) {
            menuItemModelList.add(
                    MenuItemModel.builder()
                            .withName(entry.getKey().getName())
                            .withDescription(entry.getKey().getDescription())
                            .withPrice(entry.getKey().getPrice())
                            .withQuantity(entry.getValue())
                            .build()
            );
        }
        menuItemModelList.sort(new MenuItemModelNameComparator());
        return menuItemModelList;
    }
}