package drum.com.gasolinaapp.helpers;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.identity.intents.AddressConstants;

import java.util.ArrayList;

import drum.com.gasolinaapp.R;
import drum.com.gasolinaapp.activities.MainActivity;
import drum.com.gasolinaapp.adapters.MenuAdapter;
import drum.com.gasolinaapp.adapters.MenuDrawerItem;
import drum.com.gasolinaapp.fragments.PostoMapFragment;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by gui-wani on 20/04/2015.
 */
public class DrawerInitializeHelper {

    final protected Activity context;
    private final ArrayList<MenuDrawerItem> menu;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    final protected CharSequence  titleOnDrawerOpened, titleOnDrawerClosed;

    public DrawerInitializeHelper(Activity context, CharSequence titleOnDrawerOpened, CharSequence titleOnDrawerClosed) {
        this.context = context;
        this.titleOnDrawerOpened = titleOnDrawerOpened;
        this.titleOnDrawerClosed = titleOnDrawerClosed;
        this.menu = this.getMenu();
    }

    public ArrayList<MenuDrawerItem> getMenu(){
        ArrayList<MenuDrawerItem> menu = new ArrayList<MenuDrawerItem>();
        menu.add(new MenuDrawerItem(1, R.string.search_posto, R.drawable.star_menu_icon));
        menu.add(new MenuDrawerItem(2, R.string.favorito_text_button, R.drawable.star_menu_icon));
        menu.add(new MenuDrawerItem(3, R.string.recentes_string_button, R.drawable.recentes_icon));
        menu.add(new MenuDrawerItem(true));
        menu.add(new MenuDrawerItem(4, R.string.sobre_text_button, R.drawable.info_drawer_icon));
        menu.add(new MenuDrawerItem(5, R.string.ajuda_text_button, R.drawable.help_drawer_icon));
        return menu;
    }

    public void build() {
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        drawerToggle = new ActionBarDrawerToggle(context, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                context.getActionBar().setTitle(titleOnDrawerClosed);
                context.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                context.getActionBar().setTitle(titleOnDrawerOpened);
                context.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        context.getActionBar().setHomeButtonEnabled(true);
        context.getActionBar().setDisplayUseLogoEnabled(true);

        MenuAdapter adapter = new MenuAdapter(this.context, this.menu);
        final ListView menu = (ListView) this.context.findViewById(R.id.menu_list_button);
        menu.setAdapter(adapter);
        menu.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int posicao,
                                    long id) {
                MenuDrawerItem item = (MenuDrawerItem) menu.getItemAtPosition(posicao);
                Log.i("getItemAtPosition", String.valueOf(posicao));
                if(!item.isUseSepatator()){
                   Log.i("getId", String.valueOf(item.getId()));
                  ((MainActivity) context).changeContentByFragment(item.getId());
                }
            }
        });
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public ActionBarDrawerToggle getDrawerToggle() {
        return drawerToggle;
    }

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }

    public void setDrawerToggle(ActionBarDrawerToggle drawerToggle) {
        this.drawerToggle = drawerToggle;
    }
}
