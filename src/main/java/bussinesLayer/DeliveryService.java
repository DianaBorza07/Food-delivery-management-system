package bussinesLayer;

import dataLayer.Serializator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Diana Borza
 * Class that contains the main operations performed by the administrator, employee and client
 * @Invariant ("baseProducts != null")
 */
public class DeliveryService implements IDeliveryServiceProcessing, Serializable {
    private  HashSet<BaseProduct> baseProducts = new HashSet<>();
    private  HashSet<MenuItem> menuItems = new HashSet<>();
    private Map<Order,HashSet<MenuItem>> orderListMap = new HashMap<>();
    private List<Order> orders = new ArrayList<>();
    private int baseProductID;
    private int orderID;
    public DeliveryService() {
        baseProductID = 1;
        orderID = 1;
    }

    public void importProducts() throws IOException {
        List<String> linesFromFile = Files.lines(Paths.get("products.csv")).skip(1).collect(Collectors.toList());
        HashSet<BaseProduct> baseProductsAuxiliary = new HashSet<>();
        for (String s:linesFromFile
             ) {String[] words = s.split(",");
               float rating = Float.parseFloat(words[1]);
               int calories = Integer.parseInt(words[2]);
               int protein = Integer.parseInt(words[3]);
               int fat = Integer.parseInt(words[4]);
               int sodium = Integer.parseInt(words[5]);
               int price = Integer.parseInt(words[6]);
               BaseProduct baseProduct = new BaseProduct(0,price,words[0],rating,calories,protein,fat,sodium);
               baseProductsAuxiliary.add(baseProduct);
        }
        baseProducts = (HashSet<BaseProduct>) baseProductsAuxiliary.stream().filter(
                distinctByName(b -> b.getItemName())
        ).collect(Collectors.toSet());
        for (BaseProduct b: baseProducts
             ) {
              b.setItemID(baseProductID);
              baseProductID++;
        }
    }

    public HashSet<BaseProduct> getProductsBasedOnRating(float minValue, float maxValue) {
        assert minValue<0 || maxValue<0: "Incorrect values";
        HashSet<BaseProduct> filteredProducts;
        filteredProducts = (HashSet<BaseProduct>) baseProducts.stream().filter(
                f-> f.getRating()>=minValue && f.getRating()<=maxValue).collect(Collectors.toSet());
        return filteredProducts;
    }

    public HashSet<BaseProduct> getProductsBasedOnCalories(int minValue, int maxValue) {
        assert minValue<0 || maxValue<0: "Incorrect values";
        HashSet<BaseProduct> filteredProducts = new HashSet<>();
        filteredProducts = (HashSet<BaseProduct>) baseProducts.stream().filter(
                f-> f.getCalories()>=minValue && f.getCalories()<=maxValue).collect(Collectors.toSet());
        return filteredProducts;
    }

    public HashSet<BaseProduct> getProductsBasedOnProteins(int minValue, int maxValue) {
        assert minValue<0 || maxValue<0: "Incorrect values";
        HashSet<BaseProduct> filteredProducts = new HashSet<>();
        filteredProducts = (HashSet<BaseProduct>) baseProducts.stream().filter(
                f-> f.getProtein()>=minValue && f.getProtein()<=maxValue).collect(Collectors.toSet());
        return filteredProducts;
    }

    public HashSet<BaseProduct> getProductsBasedOnFats(int minValue, int maxValue) {
        assert minValue<0 || maxValue<0: "Incorrect values";
        HashSet<BaseProduct> filteredProducts = new HashSet<>();
        filteredProducts = (HashSet<BaseProduct>) baseProducts.stream().filter(
                f-> f.getFat()>=minValue && f.getFat()<=maxValue).collect(Collectors.toSet());
        return filteredProducts;
    }

    public HashSet<BaseProduct> getProductsBasedOnSodium(int minValue, int maxValue) {
        assert minValue<0 || maxValue<0: "Incorrect values";
        HashSet<BaseProduct> filteredProducts = new HashSet<>();
        filteredProducts = (HashSet<BaseProduct>) baseProducts.stream().filter(
                f-> f.getSodium()>=minValue && f.getSodium()<=maxValue).collect(Collectors.toSet());
        return filteredProducts;
    }

    public HashSet<BaseProduct> getProductsBasedOnPrice(int minValue, int maxValue) {
        assert minValue<0 || maxValue<0: "Incorrect values";
        HashSet<BaseProduct> filteredProducts = new HashSet<>();
        filteredProducts = (HashSet<BaseProduct>) baseProducts.stream().filter(
                f-> f.getPrice()>=minValue && f.getPrice()<=maxValue).collect(Collectors.toSet());
        return filteredProducts;
    }


    public static <T> Predicate<T> distinctByName(Function<? super T, ?> keyExtractor) {
        Set<Object> obj = ConcurrentHashMap.newKeySet();
        return t -> obj.add(keyExtractor.apply(t));
    }

    public HashSet<BaseProduct> getBaseProducts() {
        return baseProducts;
    }

    public HashSet<MenuItem> getMenuItems() {
        return menuItems;
    }

    public Map<Order, HashSet<MenuItem>> getOrderListMap() {
        return orderListMap;
    }

    public void addMenuItems(List<Integer> itemsID){
        assert itemsID==null : "Null values";
        int i =0;
        Collections.sort(itemsID);
        List<BaseProduct> baseProductsSorted = new ArrayList<>(baseProducts);
        Collections.sort(baseProductsSorted);
        for (BaseProduct base: baseProductsSorted
             ) {
            if(base.getItemID()==itemsID.get(i))
            {
                menuItems.add(base);
                i++;
                if(i>= itemsID.size())
                    break;
            }
        }
    }
    public Order addOrder(HashSet<MenuItem> menuItems,int clientID){
        assert menuItems==null || clientID<0 : "Bad inputs for order";
        Order order = new Order(orderID, clientID);
        orders.add(order);
        orderID++;
        HashSet<MenuItem> menuItems1 = new HashSet<>(menuItems);
        orderListMap.put(order,menuItems1);
        try {
            generateBill(order,menuItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert order==null : "Order is null";
        return order;
    }

    private void generateBill(Order order, HashSet<MenuItem> menuItemsList) throws IOException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = format.format( order.getOrderDate());
        String message = "Order number "+ order.getOrderID() + "\n" + "Order date: " + dateString +"\n";
        for (MenuItem m : menuItemsList
             ) {
            message += "Product name: "+ m.getItemName() +" Price:" + m.getPrice() + "\n";
        }
        message += "Total price: " + computePrice();
        FileWriter fileWriter = new FileWriter("bill.txt");
        fileWriter.write(message);
        fileWriter.close();
    }
    public int computePrice(){
        int sum = 0;
        for (MenuItem m:menuItems
             ) {
            sum += m.computePrice();
        }
        assert sum<0 : "Negative sum";
        return sum;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void saveInformation(){
        Serializator serializator = new Serializator();
        try {
            serializator.serializable("deliveryServiceInfo.txt",this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBaseProductID(int baseProductID) {
        this.baseProductID = baseProductID;
    }

    public void deleteProduct(int id){
        assert id<0 : "Bad id";
        for (BaseProduct b: baseProducts
             ) {
            if(b.getItemID() == id) {
                baseProducts.remove(b);
                break;
            } }
    }

    public void addProduct(BaseProduct baseProduct){
        assert baseProduct == null : "Null input ";
        baseProduct.setItemID(baseProductID);
        baseProductID++;
        baseProducts.add(baseProduct);
    }

    public void modifyProduct(int productId,float rating, String name, int calories, int sodium, int fat,int proteins ,int price){
        assert productId<0 || rating<0 || name==null || calories<0 ||sodium<0 || fat<0 || proteins<0 || price<0 : "Bad inputs";
        for (BaseProduct b : baseProducts
             ) {
            if(b.getItemID()==productId){
                b.setItemName(name);
                b.setCalories(calories);
                b.setFat(fat);
                b.setProtein(proteins);
                b.setSodium(sodium);
                b.setRating(rating);
                b.setPrice(price); } }
    }

    public void generateReportTimeInterval(int startHour, int endHour) throws IOException {
        assert startHour<0 || endHour<0 : "Bad hour input";
        List<Order> fileredOrders = orders.stream().filter(
                order->order.getOrderDate().getHours()>=startHour && order.getOrderDate().getHours()<=endHour).collect(Collectors.toList());
        FileWriter writer = new FileWriter("reportBasedOnTimeInterval.txt");
        String message = "Orders performed between "+ startHour+" and "+ endHour+" regardless the date :\n";
        for (Order o: fileredOrders
             ) { message+= o.toString()+"\n"; }
        writer.write(message);
        writer.close();
    }

    public void generateReportClients(int number, int value) throws IOException { // lists the clients id
        assert number<=0 || value<=0 : "Negative numbers";
        AtomicInteger[] nrOfOrdersPerClient = new AtomicInteger[200];
        for (int i=0;i<200;i++) { nrOfOrdersPerClient[i] = new AtomicInteger(0); }
        String message = "The clients that have ordered more than " +number+ " times and the value of the order was higher than "+value+":\n";
        orderListMap.forEach((k,v)-> {
                if(v.stream().mapToInt(MenuItem::getPrice).sum()>= value) {
                    nrOfOrdersPerClient[k.getClientID()].getAndIncrement(); } });
        for (int  i=0;i<200;i++){
            if(nrOfOrdersPerClient[i].get() != 0 && nrOfOrdersPerClient[i].get()>number)
                message += "Client ID : " + i+ "\n"; }
        FileWriter writer = new FileWriter("clientsReport.txt");
        writer.write(message);
        writer.close();
    }

    public void generateReportProductsOrderedMost(int value) throws IOException {
        assert value<=0 : "Negative value";
        int[] nrOfOrdersPerProduct = new int[20000];
        orderListMap.forEach((k,v)->{
            v.forEach(menuItem -> {nrOfOrdersPerProduct[menuItem.getItemID()]++;}); });
        String message = "The products ordered more than " +value+"times so far:\n";
        for (int i: nrOfOrdersPerProduct
             ) { if(nrOfOrdersPerProduct[i]>=value)
                 message += "Product ID: "+i+"\n"; }
        FileWriter writer = new FileWriter("reportProductsOrderedMoreThanX.txt");
        writer.write(message);
        writer.close();
    }

    public void generateReportProductBasedOnDay(Date date) throws IOException {
        assert date==null:"Date is null";
        int[] nrOfOrdersPerProduct = new int[20000];
        orderListMap.forEach((k,v)->{
            if(k.getOrderDate().getDay() == date.getDay() && k.getOrderDate().getMonth() == date.getMonth())
                 v.forEach(menuItem -> { nrOfOrdersPerProduct[menuItem.getItemID()]++;}); });
        String message = "The products ordered within "+ date +" with the number of times they have been ordered:\n";
        for (int i : nrOfOrdersPerProduct
             ) { if(nrOfOrdersPerProduct[i]!=0) message+= "Product ID: "+i+" Nr of times: "+nrOfOrdersPerProduct[i]+"\n"; }
        FileWriter writer = new FileWriter("reportProductsOrderedOnDayX.txt");
        writer.write(message);
        writer.close();
    }

    public void prepareOrder(){
        int min=Integer.MAX_VALUE;
        Order o = null;
        for(Map.Entry<Order,HashSet<MenuItem>> map:orderListMap.entrySet()){
            int key = map.getKey().getOrderID();
            if(key<min) { min = key; o = map.getKey(); } }
        orderListMap.remove(o);
    }
}
