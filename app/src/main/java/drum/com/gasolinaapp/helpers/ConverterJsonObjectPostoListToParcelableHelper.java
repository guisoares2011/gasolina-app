package drum.com.gasolinaapp.helpers;

import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import drum.com.gasolinaapp.objects.Posto;

/**
 * Created by gui-wani on 23/05/2015.
 */
public class ConverterJsonObjectPostoListToParcelableHelper {

    public static ArrayList<Posto> toParcelable(JSONObject data){
        ArrayList<Posto> list = new ArrayList<>();

        try{
            JSONArray postosListRequest = data.getJSONArray("postos");
            for(int i = 0; i < postosListRequest.length(); i++){
                list.add(new Posto((JSONObject) postosListRequest.get(i)));
            }
        } catch (JSONException e){
            Log.e("MY", "Ocurred some error while trying to parse JSON or extract data from it", e);
        }
        return list;
    }
}
