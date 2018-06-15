package bakingapp.udacity.com.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    MainListAdapter listAdapter;
    Context context;
    RecyclerView mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        setContentView(R.layout.activity_main);

        mainList = findViewById(R.id.rv_recipes);
        RecipeNetworkUtils.getRecipesJson(this, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Recipe> recipes = null;
                try {
                    recipes = RecipeNetworkUtils.parseRecipeJson(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listAdapter = new MainListAdapter(context, recipes, new MainListAdapter.MainListClick() {
                    @Override
                    public void onClick(int position) {
                        Recipe currentRecipe = listAdapter.getItem(position);
                        Intent launchIntent = new Intent(MainActivity.this,RecipeActivity.class);
                        launchIntent.putExtra("data",currentRecipe);
                        startActivity(launchIntent);
                    }
                });


                if(getResources().getBoolean(R.bool.tabletState)){
                    GridLayoutManager manager = new GridLayoutManager(context,3);

                    mainList.setLayoutManager(manager);
                }
                else {
                    GridLayoutManager manager = new GridLayoutManager(context, 1);
                    mainList.setLayoutManager(manager);
                }
                mainList.setAdapter(listAdapter);



            }
        }, null);


    }
}
