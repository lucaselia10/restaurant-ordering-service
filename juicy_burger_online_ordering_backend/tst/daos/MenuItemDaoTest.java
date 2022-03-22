package daos;

import data.types.MenuItem;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MenuItemDaoTest {
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
    void getListOfMenuItems_withAJSONFileOfMenuItems_returnsAListOfMenuItems() {
        // GIVEN
        List<MenuItem> menuItemList = menuItemDao.getListOfMenuItems();

        // WHEN - THEN
        assertTrue(menuItemList.size() > 0);
        assertEquals(menuItemList.get(0).getClass(), MenuItem.class);
    }

    @Test
    void getMapOfMenuItems_withAJSONFileOfMenuItems_returnsMenuItemsMappedToNameOfMenuItems() {
        // GIVEN
        Map<String, MenuItem> menuItemMap = menuItemDao.getMapOfMenuItems();

        // WHEN - THEN
        assertTrue(menuItemMap.size() > 0);
        assertEquals(menuItemMap.entrySet().iterator().next().getValue().getClass(), MenuItem.class);
        assertEquals(menuItemMap.entrySet().iterator().next().getKey().getClass(), String.class);
    }
}
