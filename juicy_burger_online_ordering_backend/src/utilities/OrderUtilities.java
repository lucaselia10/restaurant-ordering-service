package utilities;

import java.util.UUID;

/**
 * Do not instantiate
 */
public class OrderUtilities {

    public static String generateOrderId() {
        return UUID.randomUUID().toString();
    }
}
