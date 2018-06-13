package bakingapp.udacity.com.bakingapp;

import android.content.Context;

import com.android.volley.Response;

import org.json.JSONArray;

public class TestRecipeSet {
    public static void returnTestIngredients(Context c, Response.Listener<JSONArray> l) {
        RecipeNetworkUtils.getRecipesJson(c, l, null);
    }
}
