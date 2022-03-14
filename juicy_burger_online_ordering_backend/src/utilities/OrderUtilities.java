package utilities;

import data.types.MenuItem;
import org.joda.time.LocalDateTime;

import java.util.Map;
import java.util.UUID;

/**
 * A utility class for the Order object. Provides
 */
public class OrderUtilities {

    private OrderUtilities() {}

    /**
     * An OrderUtilities method that generates a String UUID for the Order
     * partition key
     * @return String UUID
     */
    public static String generateOrderId() {
        return UUID.randomUUID().toString();
    }

    /**
     * An OrderUtilities method that generates a String representation of the
     * current LocalDateTime
     * @return String current local date time
     */
    public static String generateDateTimeNow() {
        return LocalDateTime.now().toString();
    }

    /**
     * An OrderUtilities method that calculates the total price of a Map of
     * MenuItems and Long quantity within an Order
     * @param orderItems Map of MenuItems and Long quantity
     * @return Long totalPrice
     */
    public static Long calculatePrice(Map<MenuItem, Long> orderItems) {
        return calculatePrice(orderItems, 0L);
    }

    /**
     * An overloaded OrderUtilities method that calculates the total price of a
     * Map of MenuItems and Long quantity within an Order including tax.
     * @param orderItems Map of MenuItems and Long quantity
     * @param taxRate Long the effective tax rate in decimal
     * @return
     */
    public static Long calculatePrice(Map<MenuItem, Long> orderItems, Long taxRate) {
        long calculatedPrice = 0L;
        for (Map.Entry<MenuItem, Long> entry : orderItems.entrySet()) {
            calculatedPrice += entry.getKey().getPrice() * entry.getValue();
        }
        return calculatedPrice * 1;
    }


}
