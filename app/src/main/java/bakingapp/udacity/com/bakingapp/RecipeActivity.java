package bakingapp.udacity.com.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.exoplayer2.ui.PlayerView;

/**
 * Class that defines the RecipeActivity {@link android.support.v7.app.AppCompatActivity}.
 */
public class RecipeActivity extends AppCompatActivity {

    Boolean tabletState;
    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        tabletState = getResources().getBoolean(R.bool.tabletState);

        recipe = (Recipe) getIntent().getSerializableExtra("data");

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment f = new SelectFragment();
        Bundle b = new Bundle();
        b.putSerializable("data", recipe);

        if(!tabletState) {


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
        }
        else{

            //Listener
            f.setArguments(b);

            fragmentManager.beginTransaction()
                    .add(R.id.container_a, f)
                    .commit();

        }
    }
}
