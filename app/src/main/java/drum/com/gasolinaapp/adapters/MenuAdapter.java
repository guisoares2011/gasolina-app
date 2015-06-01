package drum.com.gasolinaapp.adapters;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import drum.com.gasolinaapp.R;

/**
 * Created by gui-wani on 31/05/2015.
 */
public class MenuAdapter extends BaseAdapter {

    private final List<MenuDrawerItem> menuItems;
    private Activity activity;

    public MenuAdapter(Activity activity, List<MenuDrawerItem> menuItem){
        this.menuItems = menuItem;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int posicao) {
        return menuItems.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return menuItems.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {

        MenuDrawerItem item = menuItems.get(posicao);
        if(item.isUseSepatator()){
            return activity.getLayoutInflater().inflate(R.layout.item_sepator, null);
        } else {
            View view = activity.getLayoutInflater().inflate(R.layout.item_menu, null);
            TextView text =(TextView) view.findViewById(R.id.text_item);
            text.setText(activity.getResources().getString(item.getIdStringMenu()));

            ImageView imageIcon = (ImageView) view.findViewById(R.id.icon);
            imageIcon.setImageResource(item.getIdDrawableIcon());
            return view;
        }
    }

}