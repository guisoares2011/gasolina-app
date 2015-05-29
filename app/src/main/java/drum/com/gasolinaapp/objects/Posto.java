package drum.com.gasolinaapp.objects;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by gui-wani on 23/05/2015.
 */
public class Posto implements  Parcelable {

    private int id;
    private String name;
    private String bandeira;
    private Double lat;
    private Double lng;
    private Double rate;
    private Double count_rate;
    private ArrayList<Fuel> fuelList;


    public String getName(){return name;}

    //Constructor
    public Posto(JSONObject data) throws JSONException {
        this.id = data.getInt("id");
        this.name = data.getString("name");
        this.fuelList = new ArrayList<Fuel>();
        JSONArray fuels = data.getJSONArray("fuels");
        for(int i = 0; i < fuels.length(); i++){
            JSONObject f = (JSONObject) fuels.get(i);
            Log.i("MY", f.toString(2));
            this.fuelList.add(new Fuel(
                    f.getInt("type"),
                    f.getDouble("selling_price"),
                    f.getDouble("purchase_price")
            ));
        }
        this.bandeira = data.getString("bandeira");
    }



    public String toString(){
        String result = this.name;
        for(Fuel f : fuelList){
            result += f.getDescription() + " : " + f.getSellingPrice() + "\n";
        }
        return  result;
    }

    protected Posto(Parcel in) {
        id = in.readInt();
        name = in.readString();
        bandeira = in.readString();
        lat = in.readByte() == 0x00 ? null : in.readDouble();
        lng = in.readByte() == 0x00 ? null : in.readDouble();
        rate = in.readByte() == 0x00 ? null : in.readDouble();
        count_rate = in.readByte() == 0x00 ? null : in.readDouble();
        if (in.readByte() == 0x01) {
            fuelList = new ArrayList<Fuel>();
            in.readList(fuelList, Fuel.class.getClassLoader());
        } else {
            fuelList = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(bandeira);
        if (lat == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(lat);
        }
        if (lng == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(lng);
        }
        if (rate == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(rate);
        }
        if (count_rate == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(count_rate);
        }
        if (fuelList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(fuelList);
        }
    }

    public static final Parcelable.Creator<Posto> CREATOR = new Parcelable.Creator<Posto>() {
        @Override
        public Posto createFromParcel(Parcel in) {
            return new Posto(in);
        }

        @Override
        public Posto[] newArray(int size) {
            return new Posto[size];
        }
    };
}
