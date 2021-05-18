package bussinesLayer;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author Diana Borza
 * Interface of DeliveryService
  */

public interface IDeliveryServiceProcessing {
    /**
     * Method that import products from the csv file
     * @throws IOException throws an IO exception
     */
    void importProducts() throws IOException;

    /**
     * Method that returns the products which have rating between minValue and maxValue
     * @param minValue minimum value
     * @param maxValue maximum value
     * @return base products
     * @Requires ({"minValue>=0", "maxValue>=0"})
     */
    HashSet<BaseProduct> getProductsBasedOnRating(float minValue, float maxValue);

    /**
     * Method that returns the products which have calories between minValue and maxValue
     * @param minValue minimum value
     * @param maxValue maximum value
     * @return base products
     * @Requires ({"minValue>=0", "maxValue>=0"})
     */
    HashSet<BaseProduct> getProductsBasedOnCalories(int minValue, int maxValue);

    /**
     * Method that returns the products which have proteins between minValue and maxValue
     * @param minValue minimum value
     * @param maxValue maximum value
     * @return base products
     * @Requires ({"minValue>=0", "maxValue>=0"})
     */
    HashSet<BaseProduct> getProductsBasedOnProteins(int minValue, int maxValue);

    /**
     * Method that returns the products which have fats between minValue and maxValue
     * @param minValue minimum value
     * @param maxValue maximum value
     * @return base products
     * @Requires ({"minValue>=0", "maxValue>=0"})
     */
    HashSet<BaseProduct> getProductsBasedOnFats(int minValue, int maxValue);

    /**
     * Method that returns the products which have sodium between minValue and maxValue
     * @param minValue minimum value
     * @param maxValue maximum value
     * @return base products
     * @Requires ({"minValue>=0", "maxValue>=0"})
     */
    HashSet<BaseProduct> getProductsBasedOnSodium(int minValue, int maxValue);

    /**
     * Method that returns the products which have price between minValue and maxValue
     * @param minValue minimum value
     * @param maxValue maximum value
     * @return base products
     * @Requires ({"minValue>=0", "maxValue>=0"})
     */
    HashSet<BaseProduct> getProductsBasedOnPrice(int minValue, int maxValue);

    /**
     * Method that add to the menu multiple products based on their id
     * @param itemsID list of items ids
     * @Requires ("itemsID!=null")
     */
    void addMenuItems(List<Integer> itemsID);

    /**
     * Method that creates an order
     * @param menuItems list of menu items
     * @param clientID  client id
     * @return Order
     * @Requires ({"menuItems!=null", "clientID>=0"})
     * @Ensures ("Order!=null")
     */
    Order addOrder(HashSet<MenuItem> menuItems,int clientID);

    /**
     * Method that compute price for a list of products
     * @return price
     * @Ensures ("price>0")
     */
    int computePrice();

    /**
     * Method that delete a base product
     * @param id product id
     * @Requires ("id>=0")
     */
    void deleteProduct(int id);

    /**
     * Method that add a base product
     * @param baseProduct base product
     * @Requires ("baseProduct!=null")
     */
    void addProduct(BaseProduct baseProduct);

    /**
     * Method that modifies a base product
     * @param productId product id
     * @param rating rating
     * @param name product name
     * @param calories calories
     * @param sodium sodium
     * @param fat fat
     * @param proteins proteins
     * @param price product price
     * @Requires ({"productId>=0","rating>=0","name!=null","calories>=0","sodium>=0","fat>=0","proteins>=0","price>=0"})
     */
    void modifyProduct(int productId, float rating, String name, int calories, int sodium, int fat, int proteins, int price);

    /**
     * Method that generate report based on time interval
     * @param startHour start Hour
     * @param endHour end Hour
     * @throws IOException throw IO exception
     * @Requires ({"startHour>=0","endHour>=0"})
     */
    void generateReportTimeInterval(int startHour, int endHour) throws IOException;

    /**
     * Method that generate report with clients
     * @param number minimum number of times that the client ordered
     * @param value  order minimum value
     * @throws IOException throw IO exception
     * @Requires ({"number>0","value>0"})
     */
    void generateReportClients(int number, int value) throws IOException;

    /**
     * Method that generate report with products ordered more than a specified number
     * @param value number of times that a product has been ordered
     * @throws IOException throw IO exception
     * @Requires ("value>0")
     */
    void generateReportProductsOrderedMost(int value) throws IOException;

    /**
     * Method that generate report with products ordered in a specified day
     * @param date date
     * @throws IOException throw IO exception
     * @Requires ("date!=null")
     */
    void generateReportProductBasedOnDay(Date date) throws IOException;

    /**
     * Method that removes the order with the minimum id
     */
    void prepareOrder();
}
