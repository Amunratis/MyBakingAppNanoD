package com.example.sirth.mybakingappnanod.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.sirth.mybakingappnanod.App;
import com.example.sirth.mybakingappnanod.R;
import com.example.sirth.mybakingappnanod.baseClasses.BaseActivity;
import com.example.sirth.mybakingappnanod.networking.CakePOJO;
import com.example.sirth.mybakingappnanod.networking.RestApi;
import com.example.sirth.mybakingappnanod.ui.RecipeDetailActivity.RecipeDetActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends BaseActivity {

    public static final List<CakePOJO> names = new ArrayList<>();
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_list);
        ((App) getApplication()).getNetComponent().inject(this);


        final RecyclerView recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;



        try {
            Call<List<CakePOJO>> cakes = retrofit.create(RestApi.class).getCakes();

            cakes.enqueue(new Callback<List<CakePOJO>>() {
                @Override
                public void onResponse(Call<List<CakePOJO>> call, Response<List<CakePOJO>> response) {


                    List<CakePOJO> cakes = response.body();

                    String string=response.body().toString();

                    Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();





                    recyclerView.setAdapter(new MainActivityRecyclerViewAdapter(MainActivity.this, cakes));

                }

                @Override
                public void onFailure(Call<List<CakePOJO>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast
                            .LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }



    public static void setUserObject(Context c, String userObject, String key) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, userObject);
        editor.commit();
    }

    public static String getUserObject(Context ctx,String key) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        String userObject = pref.getString(key, null);
        return userObject;
    }


}