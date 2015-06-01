package drum.com.gasolinaapp.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import drum.com.gasolinaapp.AboutUsFragment;
import drum.com.gasolinaapp.fragments.PostoMapFragment;
import drum.com.gasolinaapp.helpers.DrawerHelperInterface;
import drum.com.gasolinaapp.R;
import drum.com.gasolinaapp.helpers.DrawerInitializeHelper;
import drum.com.gasolinaapp.objects.Posto;

public class MainActivity extends Activity implements DrawerHelperInterface{

    private boolean firstIntentAfterSplashScreen = true;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<Posto> postoList;
    private int currentIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(firstIntentAfterSplashScreen){
            firstIntentAfterSplashScreen = false;
            changeContentByFragment(POSTO_INTENT);
//            changeContentToFragment(new PostoMapFragment());
            setDrawerMenu();
        }
    }

    private void changeContentToFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        setUpMapIfNeeded();
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if(intent.getAction() != null){

            Log.i("DEBUG INTENT", intent.getAction());
        }
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

    @Override
    public void changeContentByFragment(int id) {

        Log.i("changeContentByFragment", String.valueOf(id));
        if(currentIntent != id) {

            switch (id) {
                case ABOUT_US_INTENT:
                    currentIntent = id;
                    changeContentToFragment(new AboutUsFragment());
                break;

                case POSTO_INTENT:
                    currentIntent = id;
                    changeContentToFragment(new PostoMapFragment());
                break;
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        getMenuInflater().inflate(R.menu.main_menu, menu);
    }

}
