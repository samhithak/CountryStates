package com.example.skamaraju.countrystates;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;


public class CountryDataActivity extends ActionBarActivity implements VerticalCardViewFragment.OnVerticalCardViewListener, HorizontalCardViewFragment.OnHorizontalCardViewListener, ListViewFragment.OnListViewListener, NavigationDrawerFragment.NavigationDrawerCallbacks{


    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_data);

        NavigationDrawerFragment navigationDrawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        navigationDrawerFragment.setUp(R.id.navigation_drawer,(android.support.v4.widget.DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        android.util.Log.d("onNavDrawerItemSelected", String.valueOf(position));
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
            case 0:
                fragmentManager.beginTransaction().replace(R.id.container,(new VerticalCardViewFragment())).commit();
                break;
            case 1:
                fragmentManager.beginTransaction().replace(R.id.container, (new HorizontalCardViewFragment())).commit();
                break;
            case 2:
                fragmentManager.beginTransaction().replace(R.id.container, (new ListViewFragment())).commit();
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_vertical_countries);
                break;
            case 2:
                mTitle = getString(R.string.title_horizontal_countries);
                break;
            case 3:
                mTitle = getString(R.string.title_list_view_countries);
                break;
        }
    }

    public void restoreActionBar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_country_states, menu);
        return true;
    }

    @Override
    public LayoutManager getLayoutManager(int position) {

        LayoutManager linearLayoutManager = null;
        switch (position) {
            case 0:
                linearLayoutManager =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            break;

            case 1:
                linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            break;

        }
        return linearLayoutManager;
    }

    @Override
    public ItemDecoration getItemDecoration() {
        //We must draw dividers ourselves if we want them in a list
        return new DividerDecoration(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.placeholder, container, false);
        }

        @Override
        public void onAttach(android.app.Activity activity) {
            super.onAttach(activity);
            ((CountryDataActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
        }

    }
}
