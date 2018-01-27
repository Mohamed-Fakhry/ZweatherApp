package zmabrook.com.zweatherapp.Home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import zmabrook.com.zweatherapp.Configs.CommonConstants;
import zmabrook.com.zweatherapp.Details.DetailsActivity;
import zmabrook.com.zweatherapp.Entities.StructuredFormatting;
import zmabrook.com.zweatherapp.Entities.WeatherItem;
import zmabrook.com.zweatherapp.Home.Extras.HomeRecyclerViewAdapter;
import zmabrook.com.zweatherapp.R;
import zmabrook.com.zweatherapp.Utils.ConnectionUtil;
import zmabrook.com.zweatherapp.Utils.LocationUtil;
import zmabrook.com.zweatherapp.base.BaseActivity;

import static zmabrook.com.zweatherapp.Configs.CommonConstants.CITY_NAME;

/**
 * Created by zmabrook on 26/01/18.
 */
public class MainActivity extends BaseActivity implements HomeContract.View {
    @BindView(R.id.floating_search_view)
    FloatingSearchView mSearchView;

    @BindView(R.id.home_recyclerView)
    RecyclerView mRecyclerView;


    private HomeContract.Actions mPresenter;
    private LocationUtil locationUtil;
    private HomeRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        CommonConstants.home = this;
        mPresenter = new HomePresenter(this);
        locationUtil = new LocationUtil(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HomeRecyclerViewAdapter(new ArrayList<WeatherItem>(),getApplicationContext());
        mRecyclerView.setAdapter(adapter);


        mSearchView.setCloseSearchOnKeyboardDismiss(true);
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {


                if (newQuery.length()>3){
                    mPresenter.getCitySuggestions(newQuery);
                }

            }
        });

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

                startDetailsActivity(searchSuggestion.getBody());
                mSearchView.clearQuery();
                mSearchView.clearSearchFocus();
                View view = MainActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }

            @Override
            public void onSearchAction(String currentQuery) {
                startDetailsActivity(currentQuery);
                mSearchView.clearQuery();
                mSearchView.clearSearchFocus();
                View view = MainActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
        if (mPresenter.isFirstUseOfApp(this)) {

            if (ConnectionUtil.isConnected(this))
                mPresenter.loadFirstCity(this, locationUtil);


        } else {
            SharedPreferences pref = getApplicationContext().getSharedPreferences(CommonConstants.SHARED_PREF_NAME, 0);

            String ids = pref.getString(CommonConstants.ID_LIST, "");
            if (ids.equals("")) {
                mPresenter.loadFirstCity(this, locationUtil);
            }else {
                mPresenter.loadCityList(ids);
            }


        }
    }

    @Override
    public void addWeatherItem(WeatherItem weatherItem) {

        adapter.addItemWithToast(weatherItem);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void addWeatherItems(ArrayList<WeatherItem> weatherItems) {
        for (int i =0 ; i < weatherItems.size() ; i++){
            adapter.addItemWithoutToast(weatherItems.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSearchSuggestions(ArrayList<StructuredFormatting> suggestions) {
        mSearchView.swapSuggestions(suggestions);

    }

    private void startDetailsActivity(String cityname){
        if (ConnectionUtil.isConnected(this)) {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(CITY_NAME, cityname);
            startActivity(intent);
        }
    }

}
