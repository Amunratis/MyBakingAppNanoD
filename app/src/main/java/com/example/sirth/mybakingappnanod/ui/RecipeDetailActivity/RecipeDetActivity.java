package com.example.sirth.mybakingappnanod.ui.RecipeDetailActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.sirth.mybakingappnanod.R;
import com.example.sirth.mybakingappnanod.baseClasses.BaseActivity;
import com.example.sirth.mybakingappnanod.networking.CakePOJO;
import com.example.sirth.mybakingappnanod.networking.Ingredient;
import com.example.sirth.mybakingappnanod.networking.Step;
import com.example.sirth.mybakingappnanod.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;



/*TODO I will use this  activity for the view of video*/
public class RecipeDetActivity extends BaseActivity implements StepsDetailsFragment.OnFragmentInteractionListener {


    CakePOJO cakePOJO;
    private Integer id;
    private String name;
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();
    private List<Step> steps = new ArrayList<Step>();
    private Integer servings;
    private String image;
    RecyclerView ingredientsRecyclerView;
    RecyclerView stepsRecyclerView;
    int stepClicked=1;

    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        cakePOJO = getIntent().getParcelableExtra("parcel");

        if (findViewById(R.id.fragment) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        if(mTwoPane){
            /*TODO load details into stepsDetailFragment here*/

            if(savedInstanceState!=null) {

                StepsDetailsFragment stepsDetailsFragment = new StepsDetailsFragment();

                Bundle args = new Bundle();
                args.putParcelable("steps", cakePOJO);
                args.putInt("int", stepClicked);

                stepsDetailsFragment.setArguments(args);

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.fragment, stepsDetailsFragment)
                        .commit();
            }
        }

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        stepsRecyclerView=findViewById(R.id.activity_steps_detail_recycler);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(this,1,false));
        stepsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        stepsRecyclerView.setAdapter(new StepsShDescAdapter(RecipeDetActivity.this,cakePOJO));


        ingredientsRecyclerView = findViewById(R.id.activity_ingredients_detail_recycler);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this,1,true));
        ingredientsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ingredientsRecyclerView.setAdapter(new RecipeIngredientsDetActivityAdapter(RecipeDetActivity.this,
                cakePOJO));

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri){
    }
}
