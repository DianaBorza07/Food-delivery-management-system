package presentationLayer;

import bussinesLayer.MenuItem;
import bussinesLayer.Order;

import java.util.HashSet;

public interface Observer {
    void update(Order order, HashSet<MenuItem> menuItems);
}
