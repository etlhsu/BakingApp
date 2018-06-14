package bakingapp.udacity.com.bakingapp;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link IngredientsWidgetConfigureActivity IngredientsWidgetConfigureActivity}
 */
public class IngredientsWidget extends AppWidgetProvider {

    public static final String EXTRA_ITEM = "bakingapp.udacity.com.bakingapp.EXTRA_ITEM";


    static void updateAppWidget(final Context context, final AppWidgetManager appWidgetManager,
                                final int appWidgetId) {

        RecipeWrapper wrapper = null;
        try {
            wrapper = IngredientsWidgetConfigureActivity.loadWrapperPref(context, appWidgetId);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateAppInfo(wrapper, context, appWidgetManager, appWidgetId);


    }

    static void updateAppInfo(RecipeWrapper r, Context c, AppWidgetManager a, int id) {
        RemoteViews views = new RemoteViews(c.getPackageName(), R.layout.ingredients_widget);
        views.setTextViewText(R.id.widget_title, r.getRecipeName());

        Intent intent = new Intent(c, ListViewWidgetService.class);

        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        intent.putExtra("data", r.getIngredientData());

        views.setRemoteAdapter(id, R.id.widget_list, intent);


        a.updateAppWidget(id, views);
    }

    static ArrayList<String> parseIngredientData(ArrayList<Ingredient> ingredients) {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient currentIngredient = ingredients.get(i);
            String unitText = null;
            switch (currentIngredient.getUnit()) {
                case CUPS:
                    unitText = "cup(s) ";
                    break;
                case TSP:
                    unitText = "tablespoon(s) ";
                    break;
                case TBLSP:
                    unitText = "teaspoon(s) ";
                    break;
                case K:
                    unitText = "kilogram(s) ";
                    break;
                case G:
                    unitText = "gram(s) ";
                    break;
                case OZ:
                    unitText = "ounce(s) ";
                case UNIT:
                    unitText = "";
            }
            String currentText = currentIngredient.getQuantity().toString() + " " +
                    unitText + currentIngredient.getName();
            data.add(currentText);

        }
        return data;
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            try {
                IngredientsWidgetConfigureActivity.deleteWrapperPref(context, appWidgetId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

