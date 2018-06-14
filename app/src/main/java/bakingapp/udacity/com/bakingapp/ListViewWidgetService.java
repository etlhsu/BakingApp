package bakingapp.udacity.com.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

public class ListViewWidgetService extends RemoteViewsService {


    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {

        ListViewRemoteViewsFactory factory = new ListViewRemoteViewsFactory(this.getApplicationContext());
        factory.setData(intent.getStringArrayListExtra("data"));
        return factory;

    }

}

class ListViewRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;

    private ArrayList<String> data;


    public ListViewRemoteViewsFactory(Context context) {

        mContext = context;

    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public void onCreate() {

        if (data == null) {
            data = new ArrayList<String>();
        }
    }

    // Given the position (index) of a WidgetItem in the array, use the item's text value in

    // combination with the app widget item XML file to construct a RemoteViews object.

    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_ingredient_card);


        views.setTextViewText(R.id.widget_ingredients, data.get(position));

        Bundle extras = new Bundle();
        extras.putInt(IngredientsWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();

        fillInIntent.putExtra("data", data);

        fillInIntent.putExtras(extras);


        views.setOnClickFillInIntent(R.id.widget_card_layout, fillInIntent);

        return views;

    }

    public int getCount() {

        return data.size();

    }

    public void onDataSetChanged() {


    }

    public int getViewTypeCount() {

        return 1;

    }

    public long getItemId(int position) {

        return 0;

    }

    public void onDestroy() {

        data.clear();

    }

    public boolean hasStableIds() {

        return true;

    }

    public RemoteViews getLoadingView() {

        return null;

    }

}
