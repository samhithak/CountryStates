package com.example.skamaraju.countrystates;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.DefaultItemAnimator;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class HorizontalCardViewFragment extends Fragment implements OnItemClickListener {

    private RecyclerView mList;
    private CountryAdapter mAdapter;

    private OnHorizontalCardViewListener mListener;

    public HorizontalCardViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.view_recycler, container, false);

        mList = (RecyclerView) rootView.findViewById(R.id.section_list);
        Button addButton = (Button) rootView.findViewById(R.id.add);
        addButton.setOnClickListener(mOnClickListener);

        mList.setLayoutManager(mListener.getLayoutManager(1));
        mList.addItemDecoration(mListener.getItemDecoration());
        mList.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new CountryAdapter(1, getActivity());
        mAdapter.setItemCount();
        mAdapter.setOnItemClickListener(this);
        mList.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onItemClick(android.widget.AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getActivity(), "Clicked: " + position + ", index " + mList.indexOfChild(view), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnHorizontalCardViewListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    OnClickListener mOnClickListener = new OnClickListener(){
        public void onClick(View v){
            mAdapter.addItem("New Country", "Its Continent", "Its area");
        }

    };

    public interface OnHorizontalCardViewListener{

        public RecyclerView.LayoutManager getLayoutManager(int position);

        public ItemDecoration getItemDecoration();

    }

}
