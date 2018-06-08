package bakingapp.udacity.com.bakingapp;

import android.content.ContentResolver;
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
