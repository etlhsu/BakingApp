package bakingapp.udacity.com.bakingapp;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainListViewHolder> {
    private Context context;
    private ArrayList<Recipe> data;
    private LayoutInflater layoutInflater;
    private MainListClick click;

    public MainListAdapter(Context c, ArrayList<Recipe> d, MainListClick click) {
        context = c;
        data = d;
        layoutInflater = LayoutInflater.from(context);
        this.click = click;
    }

    @NonNull
    @Override
    public MainListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = layoutInflater.inflate(R.layout.card_main, null);
        final MainListViewHolder mainListViewHolder = new MainListViewHolder(convertView);
        mainListViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClick(mainListViewHolder.getLayoutPosition());
            }
        });
        return mainListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainListViewHolder holder, int position) {
        if (data.get(position).getImage().isEmpty()) {
            holder.recipeImage.setImageResource(R.drawable.ic_baseline_chrome_reader_mode_24px);
        } else {
            Picasso.get().load(data.get(position).getImage()).into(holder.recipeImage);
        }
        holder.nameText.setText(data.get(position).getName());
        holder.servingsText.setText(data.get(position).getServings().toString() + " Servings");

        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 365, r.getDisplayMetrics());
        int dp = (int) px;

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                dp,dp);
        params.gravity = Gravity.CENTER;
        holder.view.setLayoutParams(params);

    }


    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MainListViewHolder extends RecyclerView.ViewHolder {
        ImageView recipeImage;
        TextView nameText;
        TextView servingsText;
        View view;

        public MainListViewHolder(View v) {
            super(v);
            recipeImage = v.findViewById(R.id.iv_recipe_image);
            nameText = v.findViewById(R.id.tv_name);
            servingsText = v.findViewById(R.id.tv_servings);
            view = v;
        }
    }
    public interface MainListClick {
        void onClick(int position);
    }
    public Recipe getItem(int position){
        return data.get(position);
    }
}
