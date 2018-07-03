package com.example.sirth.mybakingappnanod.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sirth.mybakingappnanod.R;
import com.example.sirth.mybakingappnanod.networking.CakePOJO;
import com.example.sirth.mybakingappnanod.ui.RecipeDetailActivity.RecipeDetActivity;

import java.util.List;

public class MainActivityRecyclerViewAdapter
        extends RecyclerView.Adapter<MainActivityRecyclerViewAdapter.ViewHolder> {

    //RecyclerAdapter constructor variables
    private final MainActivity mParentActivity;
    private final List<CakePOJO> cakePOJOS;


    /*we override onclick method in onclicklistener*/
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CakePOJO cakePojo = (CakePOJO) view.getTag();

                Intent intent = new Intent(mParentActivity, RecipeDetActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("parcel", cakePojo);
                mParentActivity.startActivity(intent);
            }

    };

    MainActivityRecyclerViewAdapter(MainActivity parent,
                                    List<CakePOJO> items                                  ) {
        cakePOJOS = items;
        mParentActivity = parent;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                //TODO Which layout file should I use here ?
                .inflate(R.layout.item_list_content, parent, false);

              return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        holder.itemView.setTag(cakePOJOS.get(position));

        // Get name from cakePOJOS
        holder.name.setText(cakePOJOS.get(position).getName());
        // setTag assigns a number to the view, the view can bee recognized
        // through this number. In
        // this case the number assigned is position of object within collection "CakePOJOS"
        // In this onBindViewHolder method the tag is assigned to the ViewHolder, later

        // on it is read within onClick method of onClickListener attached to the viewHolder object
        holder.itemView.setOnClickListener(mOnClickListener);



    }

    @Override
    public int getItemCount() {
        return cakePOJOS.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name;

        ViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.nameText);
        }
    }
}
