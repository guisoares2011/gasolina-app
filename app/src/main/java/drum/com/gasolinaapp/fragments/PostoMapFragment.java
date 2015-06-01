package drum.com.gasolinaapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import java.util.ArrayList;
import java.util.List;

import drum.com.gasolinaapp.R;
import drum.com.gasolinaapp.handlers.MapHandler;
import drum.com.gasolinaapp.helpers.DataSaveHelper;
import drum.com.gasolinaapp.objects.Posto;


public class PostoMapFragment extends Fragment {

    private GoogleMap map; // Might be null if Google Play services APK is not available.
    private MapHandler mapHandler;
    private boolean initialized = false;
    private ArrayList<Posto> listPosto;


    public PostoMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!initialized) {
            listPosto = DataSaveHelper.getListPosto();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posto_map, container, false);
        setUpMapIfNeeded();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void setUpMapIfNeeded() {

        // Do a null check to confirm that we have not already instantiated the map.
        if (map == null) {
            // Try to obtain the map from the SupportMapFragment.
            android.app.FragmentManager fragmentManager = getFragmentManager();


            map = ((MapFragment)fragmentManager.findFragmentById(R.id.map_google)).getMap();

            mapHandler = new MapHandler(map, this);

            // Check if we were successful in obtaining the map.
            if (map != null) {
                mapHandler.initialize(listPosto);
            }
        }
    }
}
