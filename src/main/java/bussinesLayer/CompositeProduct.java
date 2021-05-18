package bussinesLayer;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem{
    private List<MenuItem> menuItemList;
    public CompositeProduct(int itemId, int price, String itemName) {
        super(itemId, price, itemName);
        this.menuItemList = new ArrayList<MenuItem>();
    }

    public int computePrice() {
        int total =0;
        for (MenuItem m : menuItemList
             ) {
            total+=m.getPrice();
        }
        this.setPrice(total);
        return total;
    }

    public void addItem(MenuItem menuItem){
        this.menuItemList.add(menuItem);
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

}
