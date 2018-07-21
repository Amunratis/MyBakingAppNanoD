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

        if(getUserObject(getBaseContext(),"response").equals(""))
        {
            Toast.makeText(this, "no prefs saved", Toast.LENGTH_SHORT).show();
        }else
        {
            Log.d(TAG, getUserObject(getBaseContext(),"response"));
        }






        try {
            Call<List<CakePOJO>> cakes = retrofit.create(RestApi.class).getCakes();

            cakes.enqueue(new Callback<List<CakePOJO>>() {
                @Override
                public void onResponse(Call<List<CakePOJO>> call, Response<List<CakePOJO>> response) {


                    List<CakePOJO> cakes = response.body();



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



   /*     888888888888888888888888888888888888888888888888888888888888888888888888*/
        Call<String> stringCall = retrofit.create(RestApi.class).getStringResponse("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String responseString = response.body();
                    // todo: do something with the response string
                    setUserObject(getBaseContext(),responseString,"response");
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, t.toString());

            }
        });




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