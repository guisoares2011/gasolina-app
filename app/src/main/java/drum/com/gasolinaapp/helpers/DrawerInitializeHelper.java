package drum.com.gasolinaapp.helpers;

import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import drum.com.gasolinaapp.R;

/**
 * Created by gui-wani on 20/04/2015.
 */
public class DrawerInitializeHelper {

    final protected Activity context;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    final protected CharSequence  titleOnDrawerOpened, titleOnDrawerClosed;

    public DrawerInitializeHelper(Activity context, CharSequence titleOnDrawerOpened, CharSequence titleOnDrawerClosed) {
        this.context = context;
        this.titleOnDrawerOpened = titleOnDrawerOpened;
        this.titleOnDrawerClosed = titleOnDrawerClosed;
//        this.build();
    }

    public void build() {
//        drawerLayout = (DrawerLayout) context.findViewById(R.id.drawer_layout);
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
