package drum.com.gasolinaapp.adapters;


import android.app.Fragment;


/**
 * Created by gui-wani on 31/05/2015.
 */
public class MenuDrawerItem {
    private int id;
    private int idStringMenu;
    private int idDrawableIcon;
    private boolean useSepatator = false;

    public MenuDrawerItem(int id, int idStringMenu, int idDrawableIcon) {
        this.id = id;
        this.idStringMenu = idStringMenu;
        this.idDrawableIcon = idDrawableIcon;

    }

    public MenuDrawerItem(boolean b) {
        this.useSepatator = b;
    }

    public int getId() {
        return id;
    }

    public int getIdStringMenu() {
        return idStringMenu;
    }

    public int getIdDrawableIcon() {
        return idDrawableIcon;
    }

    public boolean isUseSepatator() {
        return useSepatator;
    }
}
