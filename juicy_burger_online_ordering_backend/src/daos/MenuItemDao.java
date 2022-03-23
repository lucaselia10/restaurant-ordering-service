package daos;

import data.types.MenuItem;
<<<<<<< HEAD
=======
import exceptions.OrderException;
>>>>>>> origin/PlaceOrderActivityFeature
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.inject.Inject;
import java.io.FileReader;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.List;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> origin/PlaceOrderActivityFeature

/**
 * MenuItemDao defines the characteristics and behavior of a readonly
 * data access object for MenuItem objects.
 * @author willi
 */
public class MenuItemDao {
    private JSONParser jsonParser;
    private FileReader fileReader;

    @Inject
    public MenuItemDao(JSONParser jsonParser, FileReader fileReader) {
        this.jsonParser = jsonParser;
        this.fileReader = fileReader;
    }

    // TODO: Need to determine the behavior when MenuItems cannot to retrieved
    /**
     * Generates and returns a List of MenuItems.
     * @return List of MenuItems
     */
<<<<<<< HEAD
    public List<MenuItem> getMenuItems() {
=======
    public List<MenuItem> getListOfMenuItems() {
>>>>>>> origin/PlaceOrderActivityFeature
        List<MenuItem> menuItems = new ArrayList<>();
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
<<<<<<< HEAD
                menuItems.add(
                        MenuItem.builder()
                                .withName((String) jsonObject.get("name"))
                                .withPrice((Long) jsonObject.get("price"))
=======
                Long price = (Long) jsonObject.get("price");
                menuItems.add(
                        MenuItem.builder()
                                .withName((String) jsonObject.get("name"))
                                .withPrice(price.intValue())
>>>>>>> origin/PlaceOrderActivityFeature
                                .withDescription((String) jsonObject.get("description"))
                                .build()
                );
            }
        } catch (Exception e) {
<<<<<<< HEAD
            // TODO: Initiate the Error Code 500 from here?
            System.out.println("Unable to parse file: " + e.getMessage());
=======
            // TODO: Initiate the Error Code 500 from here for the API Gateway?
            throw new OrderException("Unable to parse file", e);
        }
        return menuItems;
    }

    /**
     * Generates and returns a MenuItems mapped to the name of the MenuItem
     * @return Map of String to MenuItems
     */
    public Map<String, MenuItem> getMapOfMenuItems() {
        Map<String, MenuItem> menuItems = new HashMap<>();
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                Long price = (Long) jsonObject.get("price");
                menuItems.put(
                        (String) jsonObject.get("name"),
                        MenuItem.builder()
                                .withName((String) jsonObject.get("name"))
                                .withPrice(Math.toIntExact(price))
                                .withDescription((String) jsonObject.get("description"))
                                .build()
                );
            }
        } catch (Exception e) {
            // TODO: Initiate the Error Code 500 from here for the API Gateway?
            throw new OrderException("Unable to parse file", e);
>>>>>>> origin/PlaceOrderActivityFeature
        }
        return menuItems;
    }
}
