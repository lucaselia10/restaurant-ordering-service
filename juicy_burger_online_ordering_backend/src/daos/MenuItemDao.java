package daos;

import data.types.MenuItem;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.inject.Inject;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
    public List<MenuItem> getMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                menuItems.add(
                        MenuItem.builder()
                                .withName((String) jsonObject.get("name"))
                                .withPrice((Long) jsonObject.get("price"))
                                .withDescription((String) jsonObject.get("description"))
                                .build()
                );
            }
        } catch (Exception e) {
            // TODO: Initiate the Error Code 500 from here?
            System.out.println("Unable to parse file: " + e.getMessage());
        }
        return menuItems;
    }
}
