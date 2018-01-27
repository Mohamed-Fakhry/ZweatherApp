package zmabrook.com.zweatherapp.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
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
            }

            @Override
            public void onSearchAction(String currentQuery) {
                startDetailsActivity(currentQuery);
                mSearchView.clearQuery();
            }
        });
        if (mPresenter.isFirstUseOfApp(this)) {

            if (ConnectionUtil.isConnected(this))
                mPresenter.loadFirstCity(this, locationUtil);


        } else {


            if (ConnectionUtil.isConnected(this)){
                mPresenter.loadFirstCity(this, locationUtil);

                //load Data from cache
                //show Data from cache

            }else{
                //load Data from cache
                //get Id's from retrieved data
                //show Data from cache
                //cache data again
            }

        }
    }

    @Override
    public void addWeatherItem(WeatherItem weatherItem) {

        adapter.addItem(weatherItem);
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
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(CITY_NAME,cityname);
        startActivity(intent);
    }
}
