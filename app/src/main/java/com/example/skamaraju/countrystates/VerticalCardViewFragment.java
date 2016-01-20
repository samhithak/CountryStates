package com.example.skamaraju.countrystates;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.support.v7.widget.DefaultItemAnimator;

public class VerticalCardViewFragment extends Fragment implements OnItemClickListener{

    private RecyclerView mList;
    private CountryAdapter mAdapter;
    private OnVerticalCardViewListener mListener;

    public VerticalCardViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_recycler, container, false);

        Button addButton = (Button) rootView.findViewById(R.id.add);
        addButton.setOnClickListener(mOnClickListener);

        mList = (RecyclerView) rootView.findViewById(R.id.section_list);
        mList.setLayoutManager(mListener.getLayoutManager(0));
        mList.addItemDecoration(mListener.getItemDecoration());
        mList.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new CountryAdapter(0, getActivity());
        mAdapter.setItemCount();
        mAdapter.setOnItemClickListener(this);
        mList.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnVerticalCardViewListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    OnClickListener mOnClickListener = new OnClickListener(){
        public void onClick(View v){
            mAdapter.addItem("New Country", "Its continent", "Its area");
        }

    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Clicked: " + position + ", index " + mList.indexOfChild(view),Toast.LENGTH_SHORT).show();
    }

    public interface OnVerticalCardViewListener {

        public LayoutManager getLayoutManager(int position);

        public ItemDecoration getItemDecoration();
    }

}
