package bakingapp.udacity.com.bakingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Class that defines the RecipeActivity {@link android.support.v7.app.AppCompatActivity}.
*/
public class RecipeActivity extends AppCompatActivity {

    Recipe currentRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        currentRecipe = (Recipe) getIntent().getSerializableExtra("data");

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment f = new SelectFragment();
        Bundle b = new Bundle();
        b.putSerializable("data",currentRecipe);
        f.setArguments(b);

        fragmentManager.beginTransaction()
                .add(R.id.container,f)
                .commit();
    }
}
