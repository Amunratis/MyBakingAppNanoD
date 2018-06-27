package com.example.sirth.mybakingappnanod.ui.RecipeDetailActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sirth.mybakingappnanod.R;
import com.example.sirth.mybakingappnanod.networking.CakePOJO;

public class StepsShDescAdapter extends RecyclerView.Adapter<StepsShDescAdapter.ViewHolder> {

    Context context;
    CakePOJO cakePOJO;

    public StepsShDescAdapter(Context context, CakePOJO cakePOJO) {
        this.context = context;
        this.cakePOJO = cakePOJO;
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
        holder.shortDescription.setText(cakePOJO.getSteps().get(position).getShortDescription());



    }

    @Override
    public int getItemCount() {

        return cakePOJO.getSteps().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        TextView shortDescription;


        public ViewHolder(View itemView)  {
            super(itemView);
            shortDescription = itemView.findViewById(R.id.short_description);

        }
    }
}
