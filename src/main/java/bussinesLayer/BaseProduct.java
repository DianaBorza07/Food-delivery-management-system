package bussinesLayer;

public class BaseProduct extends MenuItem implements Comparable<BaseProduct>{

    private float rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    public BaseProduct(int itemId, int price, String itemName, float rating, int calories, int protein,int fat, int sodium) {
        super(itemId, price, itemName);
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
    }

    int computePrice() {
    return this.getPrice();
    }

    @Override
    public String toString() {
        return getItemName() +
                "{rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", price =" + getPrice() +
                '}'+
                '\n';
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }


    @Override
    public int compareTo(BaseProduct o) {
        if(this.getItemID() > o.getItemID()) return 1;
        else if(this.getItemID() < o.getItemID()) return -1;
        else return 0;
    }
}
