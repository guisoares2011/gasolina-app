package drum.com.gasolinaapp.helpers;

import java.util.ArrayList;

import drum.com.gasolinaapp.objects.Posto;

/**
 * Created by gui-wani on 30/05/2015.
 */
public class DataSaveHelper {

    private static ArrayList<Posto> listPosto;

    public static void setListPosto(ArrayList<Posto> p){
        listPosto = p;
    }

    public static ArrayList<Posto> getListPosto(){
        return listPosto;
    }
}
