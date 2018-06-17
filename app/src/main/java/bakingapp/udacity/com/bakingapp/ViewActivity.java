package bakingapp.udacity.com.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


public class ViewActivity extends AppCompatActivity {
    Recipe currentRecipe;
    RecipeFragment f;
    Integer position;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("added",true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        if(savedInstanceState == null){
            currentRecipe = (Recipe) getIntent().getSerializableExtra("data");
            position = getIntent().getIntExtra("start",0);

            f = new RecipeFragment();
            Bundle b = new Bundle();
            b.putInt("start",position);
            b.putSerializable("data",currentRecipe);
            f.setArguments(b);
            getSupportFragmentManager().beginTransaction().add(R.id.view_container,f).commit();

        }

    }

}
