package com.example.sirth.mybakingappnanod.ui.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateService extends IntentService {

    //TODO simplify this string usage, investigate if it can be done the other way
    public static String FROM_ACTIVITY_INGREDIENTS_LIST="FROM_ACTIVITY_INGREDIENTS_LIST";

    public UpdateService(){super("UpdateService");}

    public static void startBakingService(Context context, ArrayList<String> fromActivityIngredientsList){
        Intent intent=new Intent(context,UpdateService.class);
        intent.putExtra(FROM_ACTIVITY_INGREDIENTS_LIST,fromActivityIngredientsList);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if(intent!=null){
            ArrayList<String> fromActivityIngredientsList=intent.getExtras().getStringArrayList(FROM_ACTIVITY_INGREDIENTS_LIST);
            handleActionUpdateBakingWidgets(fromActivityIngredientsList );
        }
    }

    private void handleActionUpdateBakingWidgets(ArrayList<String> fromActivityIgredientsList){
        Intent intent=new Intent("android.appwidget.action.APPWIDGET_UPDATE2");

        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.putExtra(FROM_ACTIVITY_INGREDIENTS_LIST,fromActivityIgredientsList);
        sendBroadcast(intent);
    }
}
