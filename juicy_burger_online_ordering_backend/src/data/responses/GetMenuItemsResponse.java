package data.responses;

import data.types.MenuItem;

import java.util.List;

public class GetMenuItemsResponse {
    private List<MenuItem> menuItems;

    public GetMenuItemsResponse() {}

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
