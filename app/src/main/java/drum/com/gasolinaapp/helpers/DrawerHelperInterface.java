package drum.com.gasolinaapp.helpers;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;

/**
 * Created by gui-wani on 20/04/2015.
 */
public interface DrawerHelperInterface {
    public DrawerLayout drawerLayout = null;
    public ActionBarDrawerToggle drawerToggle = null;
    public void setDrawerMenu();

    public void changeContentByFragment(int id);

    public int POSTO_INTENT = 1;
//    public int POSTO_INTENT = 1;
//    public int POSTO_INTENT = 1;
//    public int POSTO_INTENT = 1;
    public int ABOUT_US_INTENT = 4;
}
