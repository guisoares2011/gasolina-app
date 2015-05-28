package drum.com.gasolinaapp.objects;

public class Fuel implements FuelType {

    public int id = 0;
    public double sellingPrice = 0, purchasePrice = 0;

    public Fuel(int id, double sellingPrice, double purchasePrice){
        this.id = id;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
    }

    public String getDescription(){
        return Fuel.FUEL_NAME_LIST[id];
    }

    public int getId(){
        return id;
    }

    public double getSellingPrice(){
        return sellingPrice;
    }

    public double getPurchasePrice(){
        return purchasePrice;
    }
}
