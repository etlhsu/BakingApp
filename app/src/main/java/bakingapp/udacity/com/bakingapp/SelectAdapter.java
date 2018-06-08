package bakingapp.udacity.com.bakingapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * A {@link BaseAdapter} that displays a recipe in a nice looking form
*/
public class SelectAdapter extends BaseAdapter {

    Recipe currentRecipe;
    Context context;

    /**The constructor where an instance of {@link SelectAdapter} is made
     * @param c A context to access tools with
     * @param r A recipe to be displayed
    */
    public SelectAdapter(Context c, Recipe r) {
        currentRecipe = r;
        context = c;
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
        return null;
    }
}
