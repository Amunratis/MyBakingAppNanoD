package com.example.sirth.mybakingappnanod.ui.recipeDetailActivity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sirth.mybakingappnanod.R;
import com.example.sirth.mybakingappnanod.baseClasses.BaseActivity;
import com.example.sirth.mybakingappnanod.data.CakePOJO;
import com.example.sirth.mybakingappnanod.data.Ingredient;
import com.example.sirth.mybakingappnanod.data.Step;
import com.example.sirth.mybakingappnanod.ui.recipeDetailActivity.stepsDetails.FragmentStepsDetailsTwoPane;
import com.example.sirth.mybakingappnanod.utilsAndConstants.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;


public class RecipeDetActivity extends BaseActivity implements FragmentStepsDetailsTwoPane.OnFragmentInteractionListener {


    public static final int GET_RECIPE_FROM_SHARED_PREFS = 1234;
    public static final String KEY_GET_RECIPE_FROM_SHARED_PREFS = "get_recipe_from_shared_prefs";
    CakePOJO cakePOJO;
    RecyclerView ingredientsRecyclerView;
    RecyclerView stepsRecyclerView;
    private Integer id;
    private String name;
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();
    private List<Step> steps = new ArrayList<Step>();
    private Integer servings;
    private String image;
    private boolean mTwoPane;
    private boolean widgetPined;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_detail);


        if (findViewById(R.id.activity_steps_details) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        //Setup Ingredients and Recipe Recyclers
        setupListsAndWidget();

    }


    void setupListsAndWidget() {

        if (getIntent().hasExtra(KEY_GET_RECIPE_FROM_SHARED_PREFS)) {
            cakePOJO = SharedPreferencesUtil.getCakePOJOFromPreferences(this);
            getSupportActionBar().setTitle(cakePOJO.getName());
        } else {
            cakePOJO = getIntent().getParcelableExtra("parcel");
            getSupportActionBar().setTitle(cakePOJO.getName());
        }


        //setup steps Recycler
        stepsRecyclerView = findViewById(R.id.activity_steps_detail_recycler);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        stepsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        stepsRecyclerView.setAdapter(new StepsDetActivityAdapter(RecipeDetActivity.this, cakePOJO, mTwoPane));


        //setup ingredients recycler
        ingredientsRecyclerView = findViewById(R.id.activity_ingredients_detail_recycler);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this, 1, true));
        ingredientsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ingredientsRecyclerView.setAdapter(new IngredientsDetActivityAdapter(RecipeDetActivity.this,
                cakePOJO, mTwoPane));

        //Widget handling part


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_pin_to_widget:
                if (!widgetPined) {
                    SharedPreferencesUtil.pinToWidget(this, getApplication(), item, cakePOJO);
                } else {
                    SharedPreferencesUtil.removeFromWidget(this, getApplication(), item);

                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        String savedName = SharedPreferencesUtil.getCakePOJONameFromPreferences(this);
        widgetPined = savedName.contentEquals(cakePOJO.getName());
        if (widgetPined) {
            MenuItem menuItem = menu.getItem(0);
            menuItem.setIcon(R.drawable.ic_action_pined_24dp);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
