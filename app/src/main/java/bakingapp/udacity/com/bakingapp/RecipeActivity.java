package bakingapp.udacity.com.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Class that defines the RecipeActivity {@link android.support.v7.app.AppCompatActivity}.
 */
public class RecipeActivity extends AppCompatActivity {

    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        recipe = (Recipe) getIntent().getSerializableExtra("data");

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment f = new SelectFragment();
        Bundle b = new Bundle();
        b.putSerializable("data", recipe);

        SelectAdapter.OnItemClickListener listener = new SelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Integer position, Recipe currentRecipe) {
                if (position != 0) {
                    Intent launchIntent = new Intent(RecipeActivity.this, ViewActivity.class);
                    launchIntent.putExtra("data", currentRecipe.getSteps().get(position - 1));
                    startActivity(launchIntent);
                }
            }
        };
        b.putSerializable("listener", listener);
        f.setArguments(b);

        fragmentManager.beginTransaction()
                .add(R.id.container, f)
                .commit();
    }
}
