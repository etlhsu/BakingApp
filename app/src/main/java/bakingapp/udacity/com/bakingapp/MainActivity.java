package bakingapp.udacity.com.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Class that defines the MainActivity {@link android.support.v7.app.AppCompatActivity}.
 * <h3>MainActivity</h3>
 * MainActivity displays a list of recipes that can be clicked on to view details
 */
public class MainActivity extends AppCompatActivity {

    Context context;
    GridView mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        setContentView(R.layout.activity_main);

        mainList = findViewById(R.id.gv_recipes);
        RecipeNetworkUtils.getMoviesJson(this, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Recipe> recipes = null;
                try {
                    recipes = RecipeNetworkUtils.parseRecipeJson(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mainList.setAdapter(new MainListAdapter(context,recipes));
            }
        }, null);


    }
}
