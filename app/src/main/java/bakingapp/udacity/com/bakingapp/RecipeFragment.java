package bakingapp.udacity.com.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A {@link Fragment} that turns recipe data into the main view
 */
public class RecipeFragment extends Fragment {

    Step currentStep;

    @Override
    public void setArguments(@Nullable Bundle args) {
        currentStep = (Step) args.getSerializable("data");
    }

    public RecipeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }
}
