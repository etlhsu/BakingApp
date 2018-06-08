package bakingapp.udacity.com.bakingapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * A {@link BaseAdapter} that displays a recipe in a nice looking form
*/
public class SelectAdapter extends BaseAdapter {
    /**The constructor where an instance of {@link SelectAdapter} is made
     * @param c A context to access tools with
     * @param r A recipe to be displayed
    */
    public SelectAdapter(Context c, Recipe r,) {
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
