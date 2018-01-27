package zmabrook.com.zweatherapp.Home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import zmabrook.com.zweatherapp.Entities.StructuredFormatting;
import zmabrook.com.zweatherapp.Entities.WeatherItem;
import zmabrook.com.zweatherapp.Home.Extras.HomeRecyclerViewAdapter;
import zmabrook.com.zweatherapp.R;
import zmabrook.com.zweatherapp.Utils.ConnectionUtil;
import zmabrook.com.zweatherapp.Utils.LocationUtil;
import zmabrook.com.zweatherapp.base.BaseActivity;

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
    private ArrayList<WeatherItem> itemArrayList;

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


        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {


                if (newQuery.length()>3){
                    mPresenter.getCitySuggestions(newQuery);
                }

            }
        });
        if (mPresenter.isFirstUseOfApp(this)) {

            if (ConnectionUtil.isConnected(this))
                mPresenter.loadFirstCity(this, locationUtil);


        } else {
            mPresenter.loadFirstCity(this, locationUtil);

            if (ConnectionUtil.isConnected(this)){
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
}
