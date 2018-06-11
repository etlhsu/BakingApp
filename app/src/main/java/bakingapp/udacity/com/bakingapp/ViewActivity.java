package bakingapp.udacity.com.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {

    Step currentStep = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        currentStep = (Step) getIntent().getSerializableExtra("data");

        RecipeFragment f = new RecipeFragment();
        Bundle b = new Bundle();
        b.putSerializable("data",currentStep);
        f.setArguments(b);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.view_container, f)
                .commit();


    }
}
