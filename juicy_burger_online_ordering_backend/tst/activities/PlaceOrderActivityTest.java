package activities;

import activites.PlaceOrderActivity;

import daos.MenuItemDao;
import daos.OrderDao;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

public class PlaceOrderActivityTest {
    private PlaceOrderActivity placeOrderActivity;

    @Mock
    private OrderDao orderDao;

    private MenuItemDao menuItemDao;

    @BeforeEach
    void setup() {
        initMocks(this);
        placeOrderActivity = new PlaceOrderActivity(orderDao, menuItemDao);
    }
}
