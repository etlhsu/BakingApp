package bakingapp.udacity.com.bakingapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

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

}
