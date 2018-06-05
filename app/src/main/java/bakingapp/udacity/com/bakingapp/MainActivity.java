package bakingapp.udacity.com.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Class that defines the MainActivity {@link android.support.v7.app.AppCompatActivity}.
 *
 * <h3>MainActivity</h3>
 * MainActivity displays a list of recipes that can be clicked on to view details
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }
}
