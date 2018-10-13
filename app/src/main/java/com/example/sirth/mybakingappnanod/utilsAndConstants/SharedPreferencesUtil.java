package com.example.sirth.mybakingappnanod.utilsAndConstants;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sirth.mybakingappnanod.R;
import com.example.sirth.mybakingappnanod.data.CakePOJO;
import com.example.sirth.mybakingappnanod.data.Ingredient;
import com.example.sirth.mybakingappnanod.ui.widget.MyAppWidgetProvider;
import com.google.gson.Gson;

import java.util.List;

public class SharedPreferencesUtil {

    public static String  BAKING_SHARED_PREFERENCES = "bakingSharedPrefs";

    public static void saveCakePOJOInPreferences(Context context, CakePOJO recipe) {
        Gson gson = new Gson();
        String json = gson.toJson(recipe);
        SharedPreferences preferences = context.getSharedPreferences(
                BAKING_SHARED_PREFERENCES, 0);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.KEY_RECIPE, json);
        editor.apply();
    }

    public static CakePOJO getCakePOJOFromPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(
                BAKING_SHARED_PREFERENCES, 0);
        String json = preferences.getString(Constants.KEY_RECIPE, "");
        return new Gson().fromJson(json, CakePOJO.class);
    }

    public static List<Ingredient> getIngredientListFromPreferences(Context context) {
        CakePOJO recipe = getCakePOJOFromPreferences(context);
        if (recipe == null) {
            return null;
        }

        return getCakePOJOFromPreferences(context).getIngredients();
    }

    public static String getCakePOJONameFromPreferences(Context context) {
        CakePOJO recipe = getCakePOJOFromPreferences(context);
        if (recipe == null) {
            return "";
        }

        return recipe.getName();
    }

    public static void removeFromPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(
                BAKING_SHARED_PREFERENCES, 0);

        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }


    /**
     * Remove recipe from SharedPreferences, call getAWM_InstanceAndUpdateWidget(), and notify user of action
     * @param item menu item that was clicked
     */
    public static void removeFromWidget(Context context, Application application, MenuItem item) {
        removeFromPreferences(context);
        MyAppWidgetProvider.getAWM_InstanceAndUpdateWidget(application);
        item.setIcon(R.drawable.ic_action_disabled_24dp);

        Toast.makeText(context, "Recipe removed from the widget", Toast.LENGTH_SHORT)
                .show();
    }

    /**
     * Save recipe in SharedPreferences, call getAWM_InstanceAndUpdateWidget() and notify user of action
     * @param item menu item that was clicked
     */
    public static void pinToWidget(Context context, Application application, MenuItem item,
                                   CakePOJO recipe) {
        saveCakePOJOInPreferences(context, recipe);
        MyAppWidgetProvider.getAWM_InstanceAndUpdateWidget(application);
        item.setIcon(R.drawable.ic_action_pined_24dp);

        Toast.makeText(context, "Pined to widget", Toast.LENGTH_SHORT)
                .show();
    }

}
