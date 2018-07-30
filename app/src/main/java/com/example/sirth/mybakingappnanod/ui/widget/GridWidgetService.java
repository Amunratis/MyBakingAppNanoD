package com.example.sirth.mybakingappnanod.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.sirth.mybakingappnanod.R;

import static com.example.sirth.mybakingappnanod.ui.widget.BakingWidgetProvider.ingredientsList;

import java.util.List;

import static com.example.sirth.mybakingappnanod.ui.widget.BakingWidgetProvider.REMOTE_INGREDIENT_LIST;
import static com.example.sirth.mybakingappnanod.ui.widget.BakingWidgetProvider.REMOTE_VIEW_BUNDLE;

public class GridWidgetService extends RemoteViewsService {

    List<String> remoteIngredients;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        return new RemoteViewsFactory(this.getApplicationContext(),intent);
    }

    class RemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

        Context mContext=null;

        public RemoteViewsFactory(Context context, Intent intent)
        {
            mContext=context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            remoteIngredients=ingredientsList;

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return remoteIngredients.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views=new RemoteViews(mContext.getPackageName(), R.id.widget_grid_view_item);

            views.setTextViewText(R.id.widget_grid_view_item,remoteIngredients.get(position));

            Intent fillInIntent=new Intent();

            //TODO inv
            views.setOnClickFillInIntent(R.id.widget_grid_view_item,fillInIntent);

            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override//TODO
        public int getViewTypeCount() {
            return 1;
        }

        @Override//TODO
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
