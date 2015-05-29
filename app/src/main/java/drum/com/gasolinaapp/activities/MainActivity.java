package drum.com.gasolinaapp.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import drum.com.gasolinaapp.fragments.PostoMapFragment;
import drum.com.gasolinaapp.helpers.DrawerHelperInterface;
import drum.com.gasolinaapp.R;
import drum.com.gasolinaapp.handlers.MapHandler;
import drum.com.gasolinaapp.helpers.DrawerInitializeHelper;
import drum.com.gasolinaapp.objects.GasStation;
import drum.com.gasolinaapp.objects.Posto;

public class MainActivity extends Activity implements DrawerHelperInterface {

    private GoogleMap map; // Might be null if Google Play services APK is not available.
    private boolean firstIntentAfterSplashScreen = true;
    private MapHandler mapHandler;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<Posto> postoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(firstIntentAfterSplashScreen){
            firstIntentAfterSplashScreen = false;
            Bundle b = getIntent().getExtras();
            ArrayList<Posto> listPosto = b.getParcelableArrayList("postoList");
            if(listPosto != null){
                postoList = listPosto;
            }
            changeContentToFragment(PostoMapFragment.newInstance("a", "da"));

//            setUpMapIfNeeded();
//            setDrawerMenu();
        }
    }

    private void changeContentToFragment(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_content, fragment); // newInstance() is a static factory method.
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
//            doMySearch(query);
            Toast.makeText(this, query, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if(mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setDrawerMenu(){

        CharSequence title = getTitle();
        DrawerInitializeHelper helperDrawer = new DrawerInitializeHelper(this, title, title);
        helperDrawer.setDrawerLayout((DrawerLayout) findViewById(R.id.drawer_layout));
        helperDrawer.build();
        mDrawerToggle = helperDrawer.getDrawerToggle();
        mDrawerLayout = helperDrawer.getDrawerLayout();
    }

    private void setUpMapIfNeeded() {

        // Do a null check to confirm that we have not already instantiated the map.
        if (map == null) {
            // Try to obtain the map from the SupportMapFragment.
            android.app.FragmentManager fragmentManager = getFragmentManager();
            map = ((MapFragment)fragmentManager.findFragmentById(R.id.map)).getMap();

            mapHandler = new MapHandler(map, this);

            // Check if we were successful in obtaining the map.
            if (map != null) {
                mapHandler.initialize(postoList);
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        getMenuInflater().inflate(R.menu.main_menu, menu);
    }
}
