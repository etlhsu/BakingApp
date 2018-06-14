package bakingapp.udacity.com.bakingapp;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * The configuration screen for the {@link IngredientsWidget IngredientsWidget} AppWidget.
 */
public class IngredientsWidgetConfigureActivity extends Activity {

    private static final String PREFS_NAME = "bakingapp.udacity.com.bakingapp.IngredientsWidget";
    private static final String PREF_PREFIX_KEY = "appwidget_";
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    EditText mAppWidgetText;
    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Context context = IngredientsWidgetConfigureActivity.this;

            // When the button is clicked, store the string locally
            String widgetText = mAppWidgetText.getText().toString();
            saveWrapPref(context, mAppWidgetId, new RecipeWrapper(mAppWidgetId, "ingredients", "name"));

            // It is the responsibility of the configuration activity to update the app widget
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            IngredientsWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);


            // Make sure we pass back the original appWidgetId
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_OK, resultValue);
            finish();
        }
    };

    public IngredientsWidgetConfigureActivity() {
        super();
    }

    // Write the prefix to the SharedPreferences object for this widget
    static void saveWrapPref(final Context context, int appWidgetId, final RecipeWrapper wrapper) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "recipewrapper").build();
                final RecipeWrapperDao dao = db.recipeWrapperDao();
                dao.insert(wrapper);

            }
        };
        Thread newThread = new Thread(run, "WrapperThread");
        newThread.start();
    }

    // Read the prefix from the SharedPreferences object for this widget.
    // If there is no preference saved, get the default from a resource
    static RecipeWrapper loadWrapperPref(final Context context, final int appWidgetId) throws InterruptedException {
        final RecipeWrapper[] wrapper = new RecipeWrapper[1];
        Runnable run = new Runnable() {
            @Override
            public void run() {
                AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "recipewrapper").build();
                final RecipeWrapperDao dao = db.recipeWrapperDao();
                wrapper[0] = dao.query(appWidgetId);

            }
        };
        Thread newThread = new Thread(run, "WrapperThread");
        newThread.start();
        newThread.join();
        return wrapper[0];
    }

    static void deleteWrapperPref(final Context context, final int appWidgetId) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                try {
                    RecipeWrapper wrapper = loadWrapperPref(context, appWidgetId);
                    AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "recipewrapper").build();
                    final RecipeWrapperDao dao = db.recipeWrapperDao();
                    dao.delete(wrapper);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        Thread newThread = new Thread(run, "WrapperThread");
        newThread.start();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        setContentView(R.layout.ingredients_widget_configure);
        mAppWidgetText = (EditText) findViewById(R.id.appwidget_text);
        findViewById(R.id.add_button).setOnClickListener(mOnClickListener);

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        //mAppWidgetText.setText(loadWrapperPref(IngredientsWidgetConfigureActivity.this, mAppWidgetId));
    }
}

