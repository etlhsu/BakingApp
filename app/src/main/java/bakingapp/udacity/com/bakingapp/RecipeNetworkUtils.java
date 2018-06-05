package bakingapp.udacity.com.bakingapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bakingapp.udacity.com.bakingapp.Ingredient.Unit;

/**
 * RecipeNetworkUtils provides tools for getting, converting and manipulating recipe data
 */
public class RecipeNetworkUtils {

    private static final String REQUEST_URL = "http://go.udacity.com/android-baking-app-json";

    /**
     * Gets the recipe listings from the internet
     *
     * @param context          A valid {@link android.content.Context} to make a {@link com.android.volley.RequestQueue}
     * @param responseListener A listener to be triggered when the data is available
     * @param errorListener    A listener to be triggered when an error has occurred
     * @return Void because the data is returned within the responseListener
     */
    public void getMoviesJson(Context context, Response.Listener responseListener, Response.ErrorListener errorListener) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, REQUEST_URL,
                null, responseListener, errorListener);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(jsonArrayRequest);
    }
    private static final String RESPONSE_STEP_ID = "id";
    private static final String RESPONSE_NAME = "name";
    private static final String RESPONSE_INGREDIENTS = "ingredients";
    private static final String RESPONSE_STEPS = "steps";
    private static final String RESPONSE_SERVINGS = "servings";
    private static final String RESPONSE_IMAGE = "image";


    private static final String INGREDIENT_QUANTITY = "quantity";
    private static final String INGREDIENT_MEASURE = "measure";
    private static final String INGREDIENT_INGREDIENT = "ingredient";

    private static final String STEP_SHORT_DESCRIPTION = "shortDescription";
    private static final String STEP_DESCRIPTION = "description";
    private static final String STEP_VIDEO_URL = "videoURL";
    private static final String STEP_THUMBNAIL_URL = "thumbnailURL";

    private static final String UNIT_CUP = "CUP";
    private static final String UNIT_TSP = "TSP";
    private static final String UNIT_TBLSP = "TBLSP";
    private static final String UNIT_K ="K";
    private static final String UNIT_G = "G";
    private static final String UNIT_OZ = "OZ";
    private static final String UNIT_UNIT = "UNIT";

    private static final String OVERRIDE_VIDEO = "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffda20_7-add-cream-mix-creampie/7-add-cream-mix-creampie.mp4";
    private static final String OVERRIDE_THUMBNAIL = "";

    /**
     * @param jsonArray The network response {@link org.json.JSONArray} that will be parsed
     * @return The {@link java.util.ArrayList} that was created based on the given data
     * @throws JSONException When the parser can't find specified data at a specified location
    */
    public ArrayList<Recipe> parseRecipeJson (JSONArray jsonArray) throws JSONException{

        ArrayList<Recipe> recipes = new ArrayList<>();
        for(Integer i = 0; i < jsonArray.length(); i++){
            JSONObject recipeObject = jsonArray.getJSONObject(i);
            Recipe recipe = new Recipe();
            recipe.setId(recipeObject.getInt(RESPONSE_STEP_ID));
            recipe.setName(recipeObject.getString(RESPONSE_NAME));

            ArrayList<Ingredient> ingredients = new ArrayList<>();
            JSONArray ingredientsData = recipeObject.getJSONArray(RESPONSE_INGREDIENTS);

            for(Integer j = 0; j < ingredientsData.length(); j++){
                JSONObject ingredientData = ingredientsData.getJSONObject(j);
                Ingredient ingredient = new Ingredient();
                ingredient.setQuantity(ingredientData.getInt(INGREDIENT_QUANTITY));

                switch (ingredientData.getString(INGREDIENT_MEASURE)){
                    case UNIT_CUP:
                        ingredient.setUnit(Unit.CUPS);
                        break;
                    case UNIT_TSP:
                        ingredient.setUnit(Unit.TSP);
                        break;
                    case UNIT_TBLSP:
                        ingredient.setUnit(Unit.TBLSP);
                        break;
                    case UNIT_K:
                        ingredient.setUnit(Unit.K);
                        break;
                    case UNIT_G:
                        ingredient.setUnit(Unit.G);
                        break;
                    case UNIT_OZ:
                        ingredient.setUnit(Unit.OZ);
                        break;
                    case UNIT_UNIT:
                        ingredient.setUnit(Unit.UNIT);
                        break;
                }
                ingredient.setName(ingredientData.getString(INGREDIENT_INGREDIENT));
                ingredients.add(ingredient);
            }
            recipe.setIngredients(ingredients);

            ArrayList<Step> steps = new ArrayList<>();
            JSONArray stepsData = recipeObject.getJSONArray(RESPONSE_STEPS);
            for(Integer e = 0; e < stepsData.length(); e++){
                JSONObject stepData = stepsData.getJSONObject(e);
                Step step = new Step();
                step.setShortDescription(stepData.getString(STEP_SHORT_DESCRIPTION));
                step.setDescription(stepData.getString(STEP_DESCRIPTION));
                if(i == 1 && e == 5){
                    step.setVideoURL(OVERRIDE_VIDEO);
                    step.setThumbnailURL(OVERRIDE_THUMBNAIL);
                }
                step.setVideoURL(stepData.getString(STEP_VIDEO_URL));
                step.setThumbnailURL(stepData.getString(STEP_THUMBNAIL_URL));
                steps.add(step);
            }
            recipe.setSteps(steps);
            recipe.setServings(recipeObject.getInt(RESPONSE_SERVINGS));
            recipe.setImage(recipeObject.getString(RESPONSE_IMAGE));
            recipes.add(recipe);
        }
        return recipes;

    }

}
