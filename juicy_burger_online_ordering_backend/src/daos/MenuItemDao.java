package daos;

import data.types.MenuItem;

import exceptions.OrderException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.inject.Inject;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MenuItemDao defines the characteristics and behavior of a readonly
 * data access object for MenuItem objects. MenuItem attributes must
 * NOT contain the characters ':' nor '&'.
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

    /**
     * Generates and returns a List of MenuItems.
     * @return List of MenuItems
     */
    public List<MenuItem> getListOfMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                Long price = (Long) jsonObject.get("price");
                menuItems.add(
                        MenuItem.builder()
                                .withName((String) jsonObject.get("name"))
                                .withPrice(price.intValue())
                                .withDescription((String) jsonObject.get("description"))
                                .build()
                );
            }
        } catch (Exception e) {
            // TODO: Initiate the Error Code 500 from here for the API Gateway?
            throw new OrderException("Unable to parse internal file", e);
        }
        return menuItems;
    }

    /**
     * Generates and returns a MenuItems mapped to the String name of the MenuItem
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
            throw new OrderException("Unable to parse internal file", e);
        }
        return menuItems;
    }
}
