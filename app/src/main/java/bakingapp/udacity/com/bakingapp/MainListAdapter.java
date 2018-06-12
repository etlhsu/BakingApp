package bakingapp.udacity.com.bakingapp;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * An appropriate {@link BaseAdapter} that inflates recipe cards with their data
 */
public class MainListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Recipe> data;
    private LayoutInflater layoutInflater;

    /**
     * The constructor where you initalized an instance of {@link MainListAdapter}
     *
     * @param c A valid context that can access a {@link LayoutInflater}
     * @param d A set of recipes in the ArrayList form
     */
    public MainListAdapter(Context c, ArrayList<Recipe> d) {
        context = c;
        data = d;
        layoutInflater = LayoutInflater.from(context);
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
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.card_main, null);
            MainListViewHolder holder = new MainListViewHolder(convertView);
            convertView.setTag(holder);
        }
        MainListViewHolder listHolder = (MainListViewHolder) convertView.getTag();

        if (data.get(position).getImage().isEmpty()) {
            listHolder.recipeImage.setImageResource(R.drawable.ic_baseline_chrome_reader_mode_24px);
        } else {
            Picasso.get().load(data.get(position).getImage()).into(listHolder.recipeImage);
        }
        listHolder.nameText.setText(data.get(position).getName());
        listHolder.servingsText.setText(data.get(position).getServings().toString() + " Servings");

        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 365, r.getDisplayMetrics());
        int dp = (int) px;
        convertView.setLayoutParams(new ViewGroup.LayoutParams(dp, dp));
        return convertView;
    }

    public class MainListViewHolder {
        ImageView recipeImage;
        TextView nameText;
        TextView servingsText;

        public MainListViewHolder(View v) {
            recipeImage = v.findViewById(R.id.iv_recipe_image);
            nameText = v.findViewById(R.id.tv_name);
            servingsText = v.findViewById(R.id.tv_servings);
        }
    }
}
