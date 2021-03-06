package drum.com.gasolinaapp.handlers;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import drum.com.gasolinaapp.fragments.PostoMapFragment;
import drum.com.gasolinaapp.objects.GasStation;
import drum.com.gasolinaapp.objects.Posto;

/**
 * Created by gui-wani on 06/04/2015.
 */
public class MapHandler {
    private GoogleMap map;

    private PostoMapFragment activity;

    public MapHandler(GoogleMap map, PostoMapFragment activity){
        this.map = map;
        this.activity = activity;
    }

    public void initialize(List<Posto> listGastation){
        createMarkers(listGastation);
//        setCameraToCurrentLocation();
    }

    public void createMarkers(List<Posto> listGastation){

    }

//    public void setCameraToCurrentLocation(){
//        map.setMyLocationEnabled(true);
//        LocationManager locationManager = (LocationManager) this.activity.getSystemService(Context.LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//
//        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
//        if (location != null)
//        {
//            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
//                    new LatLng(location.getLatitude(), location.getLongitude()), 13));
//
//            CameraPosition cameraPosition = new CameraPosition.Builder()
//                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
//                    .zoom(16)                   // Sets the zoom
//                    .bearing(90)                // Sets the orientation of the camera to east
//                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
//                    .build();                   // Creates a CameraPosition from the builder
//            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//        }
//    }
}
