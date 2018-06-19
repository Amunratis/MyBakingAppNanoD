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
    private final boolean mTwoPane;


    /*we override onclick method in onclicklistener*/
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // The object casted isn't a View object. Actually it's an object of type
            // DummyContent.DummyItem
            // set as a tag on the view
            // You can also set arbitrary objects as tags besides usual Int's
            CakePOJO cakePojo = (CakePOJO) view.getTag();
            /*Now, what's happening here? We're checking whether mTwoPane boolean value is true
             * If it is then that means we're holding a tablet and when it's false it means we're  using a  phone*/

            /*Tablet*/
            if (mTwoPane) {
                /*Tablet, show detail and list activity side by side. Create a bundle to store
                 * arguments that we will later on handle over to our detailFragment */
                Bundle arguments = new Bundle();
                /*If CakeDetailFragment.ARG_ITEM_ID is static final I wonder why not to use
                 * string literal instead? */
                arguments.putParcelable(RecipeDetailFragment.ARG_ITEM_ID, cakePojo);

                RecipeDetailFragment fragment = new RecipeDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        //TODO Which layout file should I use here ?
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            } else {/*Phone*/
                Intent intent = new Intent(mParentActivity, RecipeDetActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("parcel", cakePojo);
                mParentActivity.startActivity(intent);
            }
        }
    };

    MainActivityRecyclerViewAdapter(MainActivity parent,
                                    List<CakePOJO> items,
                                    boolean twoPane) {
        cakePOJOS = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
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
