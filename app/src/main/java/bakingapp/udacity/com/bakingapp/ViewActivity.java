package bakingapp.udacity.com.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class ViewActivity extends AppCompatActivity {

    Recipe currentRecipe = null;
    Integer position = null;
    RecipeFragment f;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("recipe", currentRecipe);
        outState.putInt("position", position);
        outState.putBoolean("added",true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        getSupportActionBar().setTitle("Steps");

        if (savedInstanceState == null) {

            currentRecipe = (Recipe) getIntent().getSerializableExtra("data");
            position = getIntent().getIntExtra("start", 0);

        } else {
            currentRecipe = (Recipe) savedInstanceState.getSerializable("recipe");
            position = savedInstanceState.getInt("position");

        }
        if (savedInstanceState == null) {

                RecipeFragment.onNavClicked listener = new RecipeFragment.onNavClicked() {
                    @Override
                    public void onLeftClicked() {
                        if (position != 0) {
                            position -= 1;
                            f.setData(currentRecipe.getSteps().get(position));
                        }
                    }

                    @Override
                    public void onRightClicked() {
                        if (position != currentRecipe.getSteps().size() - 1) {
                            position += 1;
                            f.setData(currentRecipe.getSteps().get(position));
                        }
                    }
                };

                f = new RecipeFragment();
                Bundle b = new Bundle();
                b.putSerializable("data", currentRecipe.getSteps().get(position));
                b.putSerializable("listener", listener);
                f.setArguments(b);

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.view_container, f)
                        .commit();
            }

    }

}
