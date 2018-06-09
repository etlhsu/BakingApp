package bakingapp.udacity.com.bakingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * A {@link BaseAdapter} that displays a recipe in a nice looking form
*/
public class SelectAdapter extends BaseAdapter {

    Recipe currentRecipe;
    Context context;
    LayoutInflater inflater;

    /**The constructor where an instance of {@link SelectAdapter} is made
     * @param c A context to access tools with
     * @param r A recipe to be displayed
    */
    public SelectAdapter(Context c, Recipe r) {
        currentRecipe = r;
        context = c;
        inflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return currentRecipe.getSteps().size() + 1;
    }

    @Override
    public Object getItem(int position) {
        if(position == 0){
            return currentRecipe.getIngredients();
        }
        else{
            return currentRecipe.getSteps().get(position - 1);
        }
    }

    @Override
    public long getItemId(int position) {
        if(position == 0){
            return 0;
        }
        else {
            return currentRecipe.getSteps().get(position - 1).getId();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.card_select,null);
            convertView.setTag(new SelectViewHolder(convertView));
        }
        SelectViewHolder holder = (SelectViewHolder) convertView.getTag();
        if(position == 0){
            holder.headText.setText("Ingredients");
            String displayText = "";
            for(int i = 0; i < currentRecipe.getIngredients().size(); i++){
                Ingredient currentIngredient = currentRecipe.getIngredients().get(i);
                String unitText;
                switch (currentIngredient.getUnit()){
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
                        currentIngredient.getUnit() + currentIngredient.getName() + "\n";
                displayText.concat(currentText);
            }
            holder.descriptText.setText(displayText);
        }
        else{
            Step currentText = currentRecipe.getSteps().get(position - 1);
            holder.headText.setText("Step " + String.valueOf(position - 1));
            holder.descriptText.setText(currentText.getShortDescription());
        }
        return convertView;

    }
    public class SelectViewHolder {
        private TextView headText;
        private TextView descriptText;

        public SelectViewHolder(View v) {
            headText = v.findViewById(R.id.tv_header);
            descriptText = v.findViewById(R.id.tv_description);
        }
    }
}
