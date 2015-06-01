package drum.com.gasolinaapp.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;

import drum.com.gasolinaapp.R;
import drum.com.gasolinaapp.api.GasolinaAppAPI;
import drum.com.gasolinaapp.api.RequestCallback;
import drum.com.gasolinaapp.fragments.DialogError;
import drum.com.gasolinaapp.helpers.ConverterJsonObjectPostoListToParcelableHelper;
import drum.com.gasolinaapp.helpers.DataSaveHelper;
import drum.com.gasolinaapp.objects.Posto;


public class SplashScreen extends FragmentActivity {

    private GasolinaAppAPI api;
    public boolean closeApp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        api = new GasolinaAppAPI(this);
        getInfoPostos();
    }

    protected void getInfoPostos(){

        api.getPostos(new RequestCallback(){
            @Override
            public void ok(JSONObject data, Context context){
                Intent intent = new Intent();
                intent.setClass(SplashScreen.this, MainActivity.class);
                DataSaveHelper.setListPosto(ConverterJsonObjectPostoListToParcelableHelper.toParcelable(data));
                startActivity(intent);
                finish();
            }

            public void error(VolleyError error, final Context context){

                String title = getResources().getString(R.string.error_connection_title);
                String message = getResources().getString(R.string.error_connection_message);
                String button_splash_screen_text = getResources().getString(R.string.error_connection_button_texto);
                final SplashScreen context1 = (SplashScreen) context;

                DialogError dialog = new DialogError(SplashScreen.this, title, message, button_splash_screen_text){{
                    setOnDismissListener(new OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {

                            if(context1.closeApp){
                                finish();
                            } else {
                                getInfoPostos();
                            }
                        }
                    });

                    setOnCancelListener(new OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            context1.closeApp = true;
                        }
                    });
                    setCanceledOnTouchOutside(false);
                }};

                dialog.show();
            }
        });
    }
}
