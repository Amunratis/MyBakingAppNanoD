package com.example.sirth.mybakingappnanod.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sirth.mybakingappnanod.App;
import com.example.sirth.mybakingappnanod.BR;
import com.example.sirth.mybakingappnanod.R;
import com.example.sirth.mybakingappnanod.baseClasses.BaseActivity;
import com.example.sirth.mybakingappnanod.data.Name;
import com.example.sirth.mybakingappnanod.networking.CakePOJO;
import com.example.sirth.mybakingappnanod.networking.RestApi;
import com.github.nitrico.lastadapter.LastAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RecipeDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */

    private boolean mTwoPane;
    private List<Name> names = new ArrayList<>();

    Context context=getApplicationContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        ((App) getApplication()).getNetComponent().inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;


        try {
            Call<List<CakePOJO>> cakes= retrofit.create(RestApi.class).getCakes();

          cakes.enqueue(new Callback<List<CakePOJO>>() {
                @Override
                public void onResponse(  Call<List<CakePOJO>> call, Response<List<CakePOJO>> response) {



                    if(response.isSuccessful())
                    {
                        List<CakePOJO> cakes = response.body();
                        /*for (CakePOJO cakePOJO : cakes) {
                            names.add(cakePOJO.getName());
                        }
                        Toast.makeText(MainActivity.this, names.get(0), Toast
                                .LENGTH_LONG).show();*/
                        names.add(0,new Name("1"));
                        names.add(1,new Name("2"));

                        Toast.makeText(context, names.get(0).getName(), Toast
                                .LENGTH_LONG).show();


                    }

                }

                @Override
                public void onFailure(  Call<List<CakePOJO>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast
                            .LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

        LastAdapter.with(names, BR.item, false)
                .map(Name.class, R.layout.item_list_content)
                .into((RecyclerView) recyclerView);

    }


}


    /*private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, CakeContent.ITEMS, mTwoPane));
    }*/



