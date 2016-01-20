package com.example.skamaraju.countrystates;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.support.v7.widget.RecyclerView;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.AdapterView;
import android.widget.Toast;


public class ListViewFragment extends Fragment implements OnItemClickListener{

    private RecyclerView mList;
    private CountryAdapter mAdapter;

    private OnListViewListener mListener;

    public ListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_recycler, container, false);

        mList = (RecyclerView) rootView.findViewById(R.id.section_list);

        Button addButton = (Button) rootView.findViewById(R.id.add);
        addButton.setOnClickListener(mOnClickListener);

        mList.setLayoutManager(new LinearLayoutManager(getActivity()));

        mList.addItemDecoration(mListener.getItemDecoration());

        mAdapter = new CountryAdapter(2, getActivity());
        mAdapter.setItemCount();
        mAdapter.setOnItemClickListener(this);
        mList.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnListViewListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    android.view.View.OnClickListener mOnClickListener = new android.view.View.OnClickListener(){
        public void onClick(View v){
            mAdapter.addItem("New Country", "Its continent", "Its area");
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getActivity(),"Clicked: " + position + ", index " + mList.indexOfChild(view), Toast.LENGTH_SHORT).show();
    }

    public interface OnListViewListener {

        public RecyclerView.ItemDecoration getItemDecoration();
    }
}
