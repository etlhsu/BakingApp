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

    Boolean tabletState;
    Recipe recipe;
    Fragment recipeFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        tabletState = getResources().getBoolean(R.bool.tabletState);

        recipe = (Recipe) getIntent().getSerializableExtra("data");

        getSupportActionBar().setTitle(recipe.getName());

        final FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment f = new SelectFragment();
        Bundle b = new Bundle();
        b.putSerializable("data", recipe);

        if (!tabletState) {


            SelectAdapter.OnItemClickListener listener = new SelectAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Integer position, Recipe currentRecipe) {
                    if (position != 0) {
                        Intent launchIntent = new Intent(RecipeActivity.this, ViewActivity.class);
                        launchIntent.putExtra("data", currentRecipe);
                        launchIntent.putExtra("start", position - 1);
                        startActivity(launchIntent);
                    }
                }
            };
            b.putSerializable("listener", listener);
            f.setArguments(b);

            fragmentManager.beginTransaction()
                    .add(R.id.container, f)
                    .commit();
        } else {
            final Boolean clicked = false;
            SelectAdapter.OnItemClickListener listener = new SelectAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Integer position, Recipe currentRecipe) {
                    if (position != 0) {
                        recipeFrag = new RecipeFragment();
                        Bundle b = new Bundle();
                        b.putSerializable("data", currentRecipe.getSteps().get(position - 1));
                        recipeFrag.setArguments(b);

                        if (clicked) {
                            fragmentManager.beginTransaction().add(R.id.container_b, recipeFrag).commit();
                        } else {
                            fragmentManager.beginTransaction().replace(R.id.container_b, recipeFrag).commit();
                        }
                    }
                }
            };
            //Listener
            b.putSerializable("listener", listener);
            f.setArguments(b);

            fragmentManager.beginTransaction()
                    .add(R.id.container_a, f)
                    .commit();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (recipeFrag != null) {
            getSupportFragmentManager().beginTransaction().remove(recipeFrag).commit();
        }
    }
}
