package drum.com.gasolinaapp.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Fuel implements FuelType, Parcelable {

    public int id = 0;
    public double sellingPrice = 0;
    public double purchasePrice = 0;

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

    protected Fuel(Parcel in) {
        id = in.readInt();
        sellingPrice = in.readDouble();
        purchasePrice = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeDouble(sellingPrice);
        dest.writeDouble(purchasePrice);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Fuel> CREATOR = new Parcelable.Creator<Fuel>() {
        @Override
        public Fuel createFromParcel(Parcel in) {
            return new Fuel(in);
        }

        @Override
        public Fuel[] newArray(int size) {
            return new Fuel[size];
        }
    };
}
