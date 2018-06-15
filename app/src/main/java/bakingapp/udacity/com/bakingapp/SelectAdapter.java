package bakingapp.udacity.com.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

/**
 * A {@link BaseAdapter} that displays a recipe in a nice looking form
 */
public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.SelectViewHolder> {

    Recipe currentRecipe;
    Context context;
    LayoutInflater inflater;
    OnItemClickListener onItemClickListener;


    /**
     * The constructor where an instance of {@link SelectAdapter} is made
     *
     * @param c A context to access tools with
     * @param r A recipe to be displayed
     * @param o A listener that is triggered whenever an item is clicked
     */
    public SelectAdapter(Context c, Recipe r, OnItemClickListener o) {
        currentRecipe = r;
        context = c;
        inflater = LayoutInflater.from(c);
        onItemClickListener = o;
    }

    @NonNull
    @Override
    public SelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View creationView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_select, parent, false);

        return new SelectViewHolder(creationView, onItemClickListener);
    }


    @Override
    public void onBindViewHolder(@NonNull SelectViewHolder holder, int position) {

        if (position == 0) {
            holder.getHeadText().setText("Ingredients");
            String displayText = "";
            for (int i = 0; i < currentRecipe.getIngredients().size(); i++) {
                Ingredient currentIngredient = currentRecipe.getIngredients().get(i);
                String unitText = null;
                switch (currentIngredient.getUnit()) {
                    case CUPS:
                        unitText = "cup(s) ";
                        break;
                    case TSP:
                        unitText = "tablespoon(s) ";
                        break;
                    case TBLSP:
                        unitText = "teaspoon(s) ";
                        break;
                    case K:
                        unitText = "kilogram(s) ";
                        break;
                    case G:
                        unitText = "gram(s) ";
                        break;
                    case OZ:
                        unitText = "ounce(s) ";
                    case UNIT:
                        unitText = "";
                }
                String currentText = currentIngredient.getQuantity().toString() + " " +
                        unitText + currentIngredient.getName() + "\n";
                displayText = displayText.concat(currentText);

            }
            holder.getDescriptText().setText(displayText);
        } else {
            Step currentStep = currentRecipe.getSteps().get(position - 1);
            holder.getHeadText().setText("Step " + String.valueOf(position));
            holder.getDescriptText().setText(currentStep.getShortDescription());
            if(!currentStep.getThumbnailURL().isEmpty()){
                Picasso.get().load(currentStep.getThumbnailURL()).into(holder.thumb);
                holder.thumb.setVisibility(View.VISIBLE);
            }
            else{
                holder.thumb.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public long getItemId(int position) {
        if (position == 0) {
            return 0;
        } else {
            return currentRecipe.getSteps().get(position - 1).getId();
        }
    }

    @Override
    public int getItemCount() {
        return currentRecipe.getSteps().size() + 1;
    }


    public interface OnItemClickListener extends Serializable {
        void onItemClick(Integer position, Recipe currentRecipe);
    }

    public class SelectViewHolder extends RecyclerView.ViewHolder {
        private TextView headText;
        private TextView descriptText;
        private ImageView thumb;

        public SelectViewHolder(View itemView, final OnItemClickListener clickListener) {
            super(itemView);
            headText = itemView.findViewById(R.id.tv_header);
            descriptText = itemView.findViewById(R.id.tv_description);
            thumb = itemView.findViewById(R.id.iv_select_thumb);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (clickListener != null) {
                        clickListener.onItemClick(getLayoutPosition(), currentRecipe);
                    }
                }
            });
        }

        public TextView getHeadText() {
            return headText;
        }

        public void setHeadText(TextView headText) {
            this.headText = headText;
        }

        public TextView getDescriptText() {
            return descriptText;
        }

        public void setDescriptText(TextView descriptText) {
            this.descriptText = descriptText;
        }
    }
}
