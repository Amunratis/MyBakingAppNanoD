package com.example.sirth.mybakingappnanod.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import com.example.sirth.mybakingappnanod.R;
import com.example.sirth.mybakingappnanod.data.Ingredient;
import com.example.sirth.mybakingappnanod.utilsAndConstants.SharedPreferencesUtil;

import java.util.List;


public class MyWidgetRemoteViewsService extends RemoteViewsService {

    private static final String TAG = "WidgetService";

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.d(TAG, "onGetViewFactory: " + "Service called");
        return new MyWidgetRemoteViewsFactory(this.getApplicationContext());
    }
                                     /*This is just like RecyclerView.Adapter*/
    public static class MyWidgetRemoteViewsFactory implements RemoteViewsFactory {

        private List<Ingredient> ingredients;
        private Context context;


        public MyWidgetRemoteViewsFactory(Context applicationContext) {
            this.context = applicationContext;


        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            ingredients = SharedPreferencesUtil.getIngredientListFromPreferences(context);


        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return ingredients != null ? ingredients.size() : 0;

        }

        @Override
        public RemoteViews getViewAt(int position) {


            Ingredient ingredient = ingredients.get(position);

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_list_item_ingredient);

            remoteViews.setTextViewText(R.id.ingredient_name, ingredient.getIngredient());
            remoteViews.setTextViewText(R.id.measure_and_quantity,
                    ingredient.getQuantity() + " " + ingredient.getMeasure()+" ");


            Intent fillInIntent = new Intent();

            remoteViews.setOnClickFillInIntent(R.id.widget_list_item_ingredient_containter, fillInIntent);

            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;

        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

    }
}
