package converters.dynamodbtypeconverters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import data.types.MenuItem;

import java.util.HashMap;
import java.util.Map;

/**
 * A converter class for the arbitrary data type Map of MenuItems and
 * Long quantity for DynamoDB parsing
 * @author willi
 */
public class MenuItemsMapConverter implements DynamoDBTypeConverter<String, Map<MenuItem, Long>> {

    /**
     * Converts the Map of MenuItems and Long quantity Java datatype
     * into a String for DynamoDB
     * @param object Map of MenuItems mapped to a Long quantity
     * @return String representation of the map
     */
    @Override
    public String convert(Map<MenuItem, Long> object) {
        StringBuilder orderMenuItemsString = new StringBuilder();
        try {
            int index = 0;
            for (Map.Entry<MenuItem, Long> entry : object.entrySet()) {
                orderMenuItemsString.append(String.format(
                        "%s : %s : %s : %s",
                        entry.getKey().getName(),
                        entry.getKey().getPrice(),
                        entry.getKey().getDescription(),
                        entry.getValue())
                );
                orderMenuItemsString.append(index++ < object.size() - 1 ? " & " : "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderMenuItemsString.toString();
    }

    /**
     * Converts the DynamoDB String representation of Map of MenuItems
     * and Long quantity into a Java Map data type
     * @param object representation of the map
     * @return Map of MenuItems mapped to a Long quantity
     */
    @Override
    public Map<MenuItem, Long> unconvert(String object) {
        Map<MenuItem, Long> menuItemsQuantityMap = new HashMap<>();
        try {
            for (String menuItemAttributes : object.split("&")) {
                menuItemsQuantityMap.put(
                        MenuItem.builder()
                                .withName(menuItemAttributes.split(":")[0].trim())
                                .withPrice(Long.valueOf(menuItemAttributes.split(":")[1].trim()))
                                .withDescription(menuItemAttributes.split(":")[2].trim())
                                .build(),
                        Long.valueOf(menuItemAttributes.split(":")[3].trim()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuItemsQuantityMap;
    }
}

