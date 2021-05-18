package bussinesLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    private int itemID;
    private int price;
    private String itemName;

    public MenuItem(int itemId, int price, String itemName) {
        this.itemID = itemId;
        this.price = price;
        this.itemName = itemName;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    abstract int computePrice();

   @Override
    public String toString() {
        return "{" +
                "itemID=" + itemID +
                ", price=" + price +
                ", itemName='" + itemName + '\'' +
                '}'+
                '\n';
    }
}
