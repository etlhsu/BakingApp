package bakingapp.udacity.com.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;


/**
 * A {@link Fragment} that displays information about a given recipe
 */
public class SelectFragment extends Fragment {

    Recipe currentRecipe;

    public SelectFragment() {
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        currentRecipe = (Recipe) args.getSerializable("data");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View returnView = inflater.inflate(R.layout.fragment_select,container);

        RecyclerView list = returnView.findViewById(R.id.rv_info);

        if(currentRecipe != null){
            RecyclerView.Adapter adapter = new SelectAdapter(getContext(),currentRecipe);
            list.setAdapter(adapter);
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
