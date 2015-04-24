package drum.com.gasolinaapp;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;

/**
 * Created by gui-wani on 20/04/2015.
 */
public interface DrawerHelperInterface {
    public DrawerLayout drawerLayout = null;
    public ActionBarDrawerToggle drawerToggle = null;
    public void setDrawerMenu();
}
