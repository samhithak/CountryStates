
package com.example.skamaraju.countrystates;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author skamaraju
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryListHolder> {

    private List<Country> mCountries;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public int mViewType;

    public View mRoot;
    private int mInitPosition = -1;

    public CountryAdapter(int viewType, Context context) {

        mCountries = new ArrayList<Country>();
        mViewType = viewType;
        mContext = context;
    }


    public void setItemCount() {
        mCountries.clear();
        mCountries.addAll(generateDummyData());

        notifyDataSetChanged();
    }

    public static List<Country> generateDummyData() {
        ArrayList<Country> items = new ArrayList<Country>();

        items.add(new Country("USA","North America","9,826,675"));
        items.add(new Country("Mexico","North America","1,964,375"));
        items.add(new Country("Canada","North America","9,984,670"));
        items.add(new Country("Costa Rica","North America","51,100"));
        items.add(new Country("Argentina","South America","2,780,400"));
        items.add(new Country("Brazil","South America","8,514,877"));
        items.add(new Country("Chile","South America","756,102"));
        items.add(new Country("Columbia","South America","1,138,910"));
        items.add(new Country("Peru","South America","1,285,216"));
        items.add(new Country("Uruguay","South America","176,215"));
        items.add(new Country("China","Asia","9,596,960"));
        items.add(new Country("India","Asia","3,287,263"));
        items.add(new Country("Japan","Asia","377,915"));
        items.add(new Country("Israel","Asia","20,770"));
        items.add(new Country("Indonesia","Asia","1,904,569"));
        items.add(new Country("Iran","Asia","1,648,195"));
        items.add(new Country("Iraq","Asia","438,317"));
        items.add(new Country("Bangladesh","Asia","143,998"));
        items.add(new Country("Malaysia","Asia","329,847"));
        items.add(new Country("Turkey","Asia","783,562"));
        items.add(new Country("Thailand","Asia","513,120"));
        items.add(new Country("Egypt","Africa","1,001,450"));
        items.add(new Country("Brazil","Africa","8,514,877"));
        items.add(new Country("Somalia","Africa","637,657"));
        items.add(new Country("Zimbabwe","Africa","390,757"));
        items.add(new Country("Germany","Europe","357,022"));
        items.add(new Country("Portugal","Europe","92,090"));
        items.add(new Country("Belgium","Europe","30,528"));
        items.add(new Country("Norway","Europe","323,802"));
        items.add(new Country("Netherlands","Europe","41,543"));
        items.add(new Country("Sweden","Europe","450,295"));
        items.add(new Country("Switzerland","Europe","41,277"));
        items.add(new Country("United Kingdom","Europe","243,610"));
        items.add(new Country("Poland","Europe","312,685"));
        items.add(new Country("Ukraine","Europe","603,550"));
        items.add(new Country("France","Europe","643,801"));
        items.add(new Country("Italy","Europe","301,340"));
        items.add(new Country("Ireland","Europe","70,273"));
        items.add(new Country("Denmark","Europe","43,094"));
        items.add(new Country("Greece","Europe","131,957"));

        return items;
    }

    public void addItem(String country, String continent, String area){
        Country newCountry = new Country(country, continent, area);
        mCountries.add(newCountry);
        notifyItemInserted(mCountries.size());
    }


    @Override
    public CountryListHolder onCreateViewHolder(ViewGroup container, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());

        if(mViewType == 2){
             mRoot = inflater.inflate(R.layout.list_view, container, false);
        }else {
             mRoot = inflater.inflate(R.layout.card_view, container, false);
        }

        return new CountryListHolder(mRoot, this);
    }

    @Override
    public void onBindViewHolder(CountryListHolder countryListHolder, int position) {
        Country item = mCountries.get(position);

        countryListHolder.setCountryName(String.valueOf(item.countryName));
        countryListHolder.setContinentName(String.valueOf(item.continentName));
        countryListHolder.setArea(String.valueOf(item.area));
        setAnimation(countryListHolder.itemView, position);
    }

    public void setAnimation(View viewToAnimate, int position)
    {
        if (position > mInitPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            animation.setDuration(1000);
            viewToAnimate.startAnimation(animation);
            mInitPosition = position;
        }
    }
    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private void onItemHolderClick(CountryListHolder countryListHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, countryListHolder.itemView, countryListHolder.getPosition(), countryListHolder.getItemId());
        }
    }

    public static class CountryListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mCountryName;
        private TextView mContinentName;
        private TextView mArea;

        private CountryAdapter mAdapter;

        public CountryListHolder(android.view.View itemView, CountryAdapter adapter) {
            super(itemView);

            itemView.setOnClickListener(this);

            mAdapter = adapter;

            mCountryName = (TextView) itemView.findViewById(R.id.text_country);
            mContinentName = (TextView) itemView.findViewById(R.id.text_continent);
            mArea = (TextView) itemView.findViewById(R.id.text_area);
        }

        @Override
        public void onClick(android.view.View v) {
            mAdapter.onItemHolderClick(this);
        }

        public void setCountryName(CharSequence countryName) {
            mCountryName.setText("Country: "+countryName);
        }

        public void setContinentName(CharSequence continentName) {
            mContinentName.setText("Continent: " + continentName);
        }
        public void setArea(CharSequence area) {
            mArea.setText("Area(in Sq.Km.): "+area);
        }

    }

}

