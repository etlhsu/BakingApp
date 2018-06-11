package bakingapp.udacity.com.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {

    Step currentStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        currentStep = (Step) getIntent().getSerializableExtra("data");

        RecipeFragment f = new RecipeFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.view_container, f)
                .commit();


    }
}
