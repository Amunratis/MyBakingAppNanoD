package com.example.sirth.mybakingappnanod.ui.RecipeDetailActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sirth.mybakingappnanod.R;
import com.example.sirth.mybakingappnanod.networking.CakePOJO;

public class RecipeIngredientsDetActivityAdapter extends RecyclerView.Adapter<RecipeIngredientsDetActivityAdapter.ViewHolder> {


    RecipeDetActivity recipeDetActivity;
    CakePOJO cakePOJO;

    public RecipeIngredientsDetActivityAdapter(RecipeDetActivity recipeDetActivity, CakePOJO cakePOJO) {
        this.recipeDetActivity = recipeDetActivity;
        this.cakePOJO = cakePOJO;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_content,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.ingredient.setText(cakePOJO.getIngredients().
                get(position).getIngredient());
        holder.measurement.setText(cakePOJO.getIngredients().get(position).getMeasure());
        holder.quantity.setText((Double.toString(cakePOJO.getIngredients().get(position).getQuantity())));


    }

    @Override
    public int getItemCount() {
        return cakePOJO.getIngredients().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView ingredient;
        TextView measurement;
        TextView quantity;

        public ViewHolder(View itemView) {
            super(itemView);
            ingredient =itemView.findViewById(R.id.ingredients);
            measurement=itemView.findViewById(R.id.measurement);
            quantity=itemView.findViewById(R.id.quantity);
        }
    }
}
