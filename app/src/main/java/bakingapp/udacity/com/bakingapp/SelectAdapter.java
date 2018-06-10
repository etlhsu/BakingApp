package bakingapp.udacity.com.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * A {@link BaseAdapter} that displays a recipe in a nice looking form
 */
public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.SelectViewHolder> {

    Recipe currentRecipe;
    Context context;
    LayoutInflater inflater;

    /**
     * The constructor where an instance of {@link SelectAdapter} is made
     *
     * @param c A context to access tools with
     * @param r A recipe to be displayed
     */
    public SelectAdapter(Context c, Recipe r) {
        currentRecipe = r;
        context = c;
        inflater = LayoutInflater.from(c);
    }

    @NonNull
    @Override
    public SelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View creationView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_select, parent,false);

        return new SelectViewHolder(creationView);
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
                Log.v("TEXT",currentText);
                Log.v("DISTEXT",displayText);
            }
            holder.getDescriptText().setText(displayText);
        } else {
            Step currentText = currentRecipe.getSteps().get(position - 1);
            holder.getHeadText().setText("Step " + String.valueOf(position));
            holder.getDescriptText().setText(currentText.getShortDescription());
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


    public static class SelectViewHolder extends RecyclerView.ViewHolder {
        private TextView headText;
        private TextView descriptText;

        public SelectViewHolder(View itemView) {
            super(itemView);
            headText = itemView.findViewById(R.id.tv_header);
            descriptText = itemView.findViewById(R.id.tv_description);

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
