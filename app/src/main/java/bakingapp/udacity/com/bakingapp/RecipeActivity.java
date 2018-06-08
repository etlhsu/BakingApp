package bakingapp.udacity.com.bakingapp;

import android.os.Bundle;
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

    }
}
