package com.example.sirth.mybakingappnanod.ui.recipeDetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sirth.mybakingappnanod.R;
import com.example.sirth.mybakingappnanod.networking.CakePOJO;
import com.example.sirth.mybakingappnanod.networking.Step;
import com.example.sirth.mybakingappnanod.ui.recipeDetailActivity.stepsDetails.FragmentStepsDetailsTwoPane;
import com.example.sirth.mybakingappnanod.ui.recipeDetailActivity.stepsDetails.StepsDetailsActivity;

public class RecipeDetActivityAdapterSteps extends RecyclerView.Adapter<RecipeDetActivityAdapterSteps.ViewHolder> {

    RecipeDetActivity recipeDetActivity;
    CakePOJO cakePOJO;
    Boolean mTwoPane;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Step step = (Step) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putParcelable("argumentsRecipe", step);
                FragmentStepsDetailsTwoPane fragment = new FragmentStepsDetailsTwoPane();
                fragment.setArguments(arguments);
                recipeDetActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment, fragment)
                        .commit();
            } else {
                Intent intent = new Intent(recipeDetActivity, StepsDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("parcel", step);
                recipeDetActivity.startActivity(intent);
            }
        }

    };


    public RecipeDetActivityAdapterSteps(RecipeDetActivity recipeDetActivity, CakePOJO cakePOJO, Boolean mTwoPane) {
        this.recipeDetActivity = recipeDetActivity;
        this.cakePOJO = cakePOJO;
        this.mTwoPane = mTwoPane;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.shortDescription.setText(cakePOJO.getSteps().get(position).getShortDescription());
        holder.itemView.setTag(cakePOJO.getSteps().get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {

        return cakePOJO.getSteps().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        TextView shortDescription;


        public ViewHolder(View itemView) {
            super(itemView);
            shortDescription = itemView.findViewById(R.id.short_description);

        }
    }
}
