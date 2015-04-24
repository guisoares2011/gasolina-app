package drum.com.gasolinaapp;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
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

import drum.com.gasolinapp.handlers.MapHandler;
import drum.com.gasolinapp.helpers.DrawerInitializeHelper;
import drum.com.gasolinapp.objects.GasStation;

public class MainActivity extends Activity implements DrawerHelperInterface{

    private GoogleMap map; // Might be null if Google Play services APK is not available.

    private MapHandler mapHandler;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpMapIfNeeded();
        setDrawerMenu();
        /*
        * listaAlunos.setOnItemClickListener(new OnItemClickListener() {
        	@Override
	        public void onItemClick(AdapterView<?> adapter, View view, int posicao,
	        		long id) {
        		Intent edicao = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
        		edicao.putExtra(Extras.ALUNO_SELECIONADO, (Aluno) listaAlunos.getItemAtPosition(posicao));
        		startActivity(edicao);
	        }
        });
        * */
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
                mapHandler.initialize(new ArrayList<GasStation>(){{
                    add(new GasStation(1, "Posto do Ipiranga"){{

                    }});
                }});
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        getMenuInflater().inflate(R.menu.main_menu, menu);
    }
}
