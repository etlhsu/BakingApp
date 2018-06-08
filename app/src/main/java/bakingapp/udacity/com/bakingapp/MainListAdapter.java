package bakingapp.udacity.com.bakingapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * An appropriate {@link BaseAdapter} that inflates recipe cards with their data
 */
public class MainListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Recipe> data;

    public MainListAdapter(Context c, ArrayList<Recipe> d) {
        context = c;
        data = d;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Recipe getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
