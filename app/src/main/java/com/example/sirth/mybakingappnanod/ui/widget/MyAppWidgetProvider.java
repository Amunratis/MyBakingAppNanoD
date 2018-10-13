package com.example.sirth.mybakingappnanod.ui.widget;

import android.app.Application;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RemoteViews;

import com.example.sirth.mybakingappnanod.R;
import com.example.sirth.mybakingappnanod.ui.MainActivity;
import com.example.sirth.mybakingappnanod.ui.recipeDetailActivity.RecipeDetActivity;
import com.example.sirth.mybakingappnanod.utilsAndConstants.SharedPreferencesUtil;


public class MyAppWidgetProvider extends AppWidgetProvider {

    public static final String EXTRA_LABEL = "TASK_TEXT";


    public static void updateAppWidgets(Context context, AppWidgetManager appWidgetManager,
                                        int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId) {
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_list_view);

        String name = SharedPreferencesUtil.getCakePOJONameFromPreferences(context);
        /*If there is object saved in prefs remove the blank View, set recipe's name, got to
         *
         * */
        if (name != null && !name.isEmpty()) {

            views.setViewVisibility(R.id.blank_state, View.GONE);
            views.setViewVisibility(R.id.widget_list_view, View.VISIBLE);
            views.setTextViewText(R.id.recipe_name, name);

            // Provide navigation
            PendingIntent backStackPendingIntent =
                    getPendingIntentWithBackStack(
                            context,
                            getIntentForSelectedRecipe(context));

            // set click pending intent for recipe name
            views.setOnClickPendingIntent(R.id.recipe_name, backStackPendingIntent);
            // Set list item click to launch recipe
            views.setPendingIntentTemplate(R.id.widget_list_view, backStackPendingIntent);

            // Set the ListWidgetService to act as the adapter for the ListView
            Intent widgetServiceIntent = new Intent(context, MyWidgetRemoteViewsService.class);
            views.setRemoteAdapter(R.id.widget_list_view, widgetServiceIntent);
        }/*If there are no recipes saved then set the visibility of of the blank View to VISIBLE
        and the visibility of the list to GONE, set the name of the app on widget, on click launch
        list of all the recipes*/ else {
            views.setViewVisibility(R.id.widget_list_view, View.GONE);
            views.setViewVisibility(R.id.blank_state, View.VISIBLE);
            views.setTextViewText(R.id.recipe_name, context.getString(R.string.app_name));

            Intent recipeListActivityIntent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    recipeListActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            views.setOnClickPendingIntent(R.id.blank_state, pendingIntent);
            views.setOnClickPendingIntent(R.id.recipe_name, pendingIntent);
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @NonNull
    private static Intent getIntentForSelectedRecipe(Context context) {
        Intent launchActivityIntent = new Intent(context, RecipeDetActivity.class);
        launchActivityIntent.putExtra(RecipeDetActivity.KEY_GET_RECIPE_FROM_SHARED_PREFS,
                RecipeDetActivity.GET_RECIPE_FROM_SHARED_PREFS);
        return launchActivityIntent;
    }

    /*Pending intent for widget OnClick navigation in case there are no saved
    Recipes
     */


    private static PendingIntent getPendingIntentWithBackStack(Context context,
                                                               Intent launchActivityIntent) {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntentWithParentStack(launchActivityIntent);
        return stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    /**
     * Get instance of AppWidgetManager and call WidgetProviders updateAppWidgets()
     * Notify remote adapter to update widget data
     */
    public static void getAWM_InstanceAndUpdateWidget(Application application) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(application);

        int[] ids = appWidgetManager
                .getAppWidgetIds(new ComponentName(application, MyAppWidgetProvider.class));

        updateAppWidgets(application, appWidgetManager, ids);
        appWidgetManager.notifyAppWidgetViewDataChanged(ids, R.id.widget_list_view);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            // refresh all your widgets
            AppWidgetManager mgr = AppWidgetManager.getInstance(context);
            ComponentName cn = new ComponentName(context, MyAppWidgetProvider.class);
            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.widget_list_view);
        }
        super.onReceive(context, intent);
    }
}

