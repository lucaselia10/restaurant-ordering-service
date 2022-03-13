package daos;

import data.types.MenuItem;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.util.List;

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
    public void getMenuItems_withAJSONFileOfMenuItems_returnsAListOfMenuItems() {
        // GIVEN
        List<MenuItem> menuItems = menuItemDao.getMenuItems();

        // WHEN - THEN
        assertNotNull(menuItems);
        assertTrue(menuItems.size() > 0);
    }

}
