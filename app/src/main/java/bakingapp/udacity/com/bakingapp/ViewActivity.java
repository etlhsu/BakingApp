package bakingapp.udacity.com.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.Serializable;

public class ViewActivity extends AppCompatActivity {

    Step currentStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Toast.makeText(this,currentStep.getShortDescription(),Toast.LENGTH_SHORT).show();

        RecipeFragment f = new RecipeFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.view_container,f)
                .commit();



    }
}
