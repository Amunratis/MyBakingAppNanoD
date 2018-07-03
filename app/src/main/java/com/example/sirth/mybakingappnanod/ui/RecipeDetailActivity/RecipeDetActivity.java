package com.example.sirth.mybakingappnanod.ui.RecipeDetailActivity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sirth.mybakingappnanod.R;
import com.example.sirth.mybakingappnanod.baseClasses.BaseActivity;
import com.example.sirth.mybakingappnanod.networking.CakePOJO;
import com.example.sirth.mybakingappnanod.networking.Ingredient;
import com.example.sirth.mybakingappnanod.networking.Step;
import com.example.sirth.mybakingappnanod.ui.RecipeDetailActivity.StepsDetails.FragmentStepsDetailsTwoPane;

import java.util.ArrayList;
import java.util.List;

/*TODO 1
 * Load data from step in textviews and exoplayer in StepsDetailsActivity
 *through RecipeDetActivityAdapterIngredients
 * */
public class RecipeDetActivity extends BaseActivity implements FragmentStepsDetailsTwoPane.OnFragmentInteractionListener {


    CakePOJO cakePOJO;
    private Integer id;
    private String name;
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();
    private List<Step> steps = new ArrayList<Step>();
    private Integer servings;
    private String image;
    RecyclerView ingredientsRecyclerView;
    RecyclerView stepsRecyclerView;
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



        if (findViewById(R.id.fragment) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        //Setup Ingredients and Recipe Recyclers
        setupLists();


    }


    void setupLists() {

        cakePOJO = getIntent().getParcelableExtra("parcel");

        stepsRecyclerView = findViewById(R.id.activity_steps_detail_recycler);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        stepsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        stepsRecyclerView.setAdapter(new RecipeDetActivityAdapterSteps(RecipeDetActivity.this, cakePOJO, mTwoPane));


        ingredientsRecyclerView = findViewById(R.id.activity_ingredients_detail_recycler);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this, 1, true));
        ingredientsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ingredientsRecyclerView.setAdapter(new RecipeDetActivityAdapterIngredients(RecipeDetActivity.this,
                cakePOJO, mTwoPane));

    }


    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
