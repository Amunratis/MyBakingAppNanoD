package com.example.sirth.mybakingappnanod.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.sirth.mybakingappnanod.R;
import com.example.sirth.mybakingappnanod.ui.recipeDetailActivity.RecipeDetActivity;

import static com.example.sirth.mybakingappnanod.ui.widget.UpdateService.FROM_ACTIVITY_INGREDIENTS_LIST;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetProvider extends AppWidgetProvider {


    //TODO
    public static String REMOTE_INGREDIENT_LIST="REMOTE_INGRIDIENT_LIST";
    public static String REMOTE_VIEW_BUNDLE="REMOTEVIEW_BUNDLE";

    static ArrayList<String> ingredientsList=new ArrayList<>();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_grid_view);

        Intent applicationIntent=new Intent(context, RecipeDetActivity.class);
        applicationIntent.addCategory(Intent.ACTION_MAIN);
        applicationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        applicationIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,applicationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.layout.widget_grid_view,pendingIntent);
        // Set the GridWidgetService intent to act as the adapter for the GridView
        Intent intent=new Intent(context,GridWidgetService.class);
        views.setRemoteAdapter(R.layout.widget_grid_view,intent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
       /* for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }*/
    }
    //override the generic onUpdate method here
    public static void updateBakingAppWidgets(Context context, AppWidgetManager appWidgetManager, int[] appwidgetIds){
        for(int appwidgID:appwidgetIds){
            updateAppWidget(context,appWidgetManager,appwidgID);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(context);
        int[] appwidgetIds=appWidgetManager.getAppWidgetIds(new ComponentName(context,BakingWidgetProvider.class));

        final String action=intent.getAction();

        if(action.equals("android.appwidget.action.APPWIDGET_UPDATE2"))
        {
            ingredientsList=intent.getExtras().getStringArrayList(FROM_ACTIVITY_INGREDIENTS_LIST);

            Toast.makeText(context, ingredientsList.get(0), Toast.LENGTH_LONG).show();

            appWidgetManager.notifyAppWidgetViewDataChanged(appwidgetIds,R.layout.widget_grid_view);
            BakingWidgetProvider.updateBakingAppWidgets(context,appWidgetManager,appwidgetIds   );
            super.onReceive(context, intent);
        }
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

