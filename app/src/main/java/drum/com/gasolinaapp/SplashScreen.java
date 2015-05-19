package drum.com.gasolinaapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONObject;

import drum.com.gasolinapp.api.GasolinaAppAPI;
import drum.com.gasolinapp.api.RequestCallback;


public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        GasolinaAppAPI api = new GasolinaAppAPI(this);
        api.getPostos(new RequestCallback(){
            @Override
            public void ok(JSONObject data, Context context){
                Log.i("Test", data.toString());
                Intent intent = new Intent();
                intent.setClass(SplashScreen.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
