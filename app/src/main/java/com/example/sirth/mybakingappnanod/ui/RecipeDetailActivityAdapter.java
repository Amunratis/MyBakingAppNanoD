package com.example.sirth.mybakingappnanod.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sirth.mybakingappnanod.R;
import com.example.sirth.mybakingappnanod.networking.CakePOJO;

public class RecipeDetailActivityAdapter extends RecyclerView.Adapter<RecipeDetailActivityAdapter.ViewHolder> {


    RecipeDetailActivity recipeDetailActivity;
    CakePOJO cakePOJO;

    public RecipeDetailActivityAdapter(RecipeDetailActivity recipeDetailActivity, CakePOJO cakePOJO) {
        this.recipeDetailActivity = recipeDetailActivity;
        this.cakePOJO = cakePOJO;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_content,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textView.setText(cakePOJO.getIngredients().get(position).getIngredient());

    }

    @Override
    public int getItemCount() {
        return cakePOJO.getIngredients().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.ingredients_content);
        }
    }
}
