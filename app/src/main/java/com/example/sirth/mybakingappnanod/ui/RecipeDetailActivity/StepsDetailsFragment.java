package com.example.sirth.mybakingappnanod.ui.RecipeDetailActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sirth.mybakingappnanod.R;
import com.example.sirth.mybakingappnanod.baseClasses.BaseFragment;
import com.example.sirth.mybakingappnanod.networking.CakePOJO;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StepsDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StepsDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StepsDetailsFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String steps = "param1";
    CakePOJO cakePOJO;

    // TODO: Rename and change types of parameters
    private TextView shortDesc;
    private TextView desc;
    private ImageView imageView;



    private OnFragmentInteractionListener mListener;

    public StepsDetailsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static StepsDetailsFragment newInstance(CakePOJO cakePOJO) {

        StepsDetailsFragment fragment = new StepsDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(steps, cakePOJO);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_steps_details, container, false);

        shortDesc=view.findViewById(R.id.short_description);
        desc=view.findViewById(R.id.description);


        Bundle bundle = this.getArguments();
        if (bundle != null) {




            cakePOJO = bundle.getParcelable("parcel");
            shortDesc.setText(cakePOJO.getSteps().get(bundle.getInt("int")).getShortDescription());
            desc.setText(cakePOJO.getSteps().get(bundle.getInt("int")).getDescription());
        }
        else { Toast.makeText(getContext(),"NULL",Toast.LENGTH_LONG).show();}



        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
