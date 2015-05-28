package drum.com.gasolinaapp.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by gui-wani on 18/05/2015.
 */
public class GasolinaAppAPI implements Request.Method {

    static final String API_URL = "http://private-c6896-gasolinaapp.apiary-mock.com/";

    private final Context context;

    public GasolinaAppAPI(Context context){
        this.context = context;
    }

    public void request(String url, int type, Map<String, Object> data, final RequestCallback callback){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.context);
        queue.start();
        // Request a string response from the provided URL.

        JSONObject parameters;
        if(data != null){
            parameters = new JSONObject(data);
        } else {
            parameters = new JSONObject();
        }
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(type, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("OI", "A");
                callback.ok(response, context);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
                callback.error(error, context);
            }
        });
        // Add the request to the RequestQueue.
        queue.add(jsObjRequest);

    }

    public void getPostos(RequestCallback callback){
        this.getPostos(null, callback);
    }

    public void getPostos(Map<String, Object> data, RequestCallback callback){
        this.request(GasolinaAppAPI.API_URL + "getPostos", GasolinaAppAPI.POST, data, callback);
    }
}
