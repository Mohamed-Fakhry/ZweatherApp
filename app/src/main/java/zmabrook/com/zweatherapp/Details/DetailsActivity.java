package zmabrook.com.zweatherapp.Details;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import zmabrook.com.zweatherapp.Configs.CommonConstants;
import zmabrook.com.zweatherapp.Details.Extras.DetailsRecyclerViewAdapter;
import zmabrook.com.zweatherapp.Entities.FiveDaysResponse;
import zmabrook.com.zweatherapp.Entities.WeatherItem;
import zmabrook.com.zweatherapp.Listeners.AddCityListener;
import zmabrook.com.zweatherapp.R;
import zmabrook.com.zweatherapp.base.BaseActivity;

import static zmabrook.com.zweatherapp.Configs.CommonConstants.ADD_CITY_LISTENER;
import static zmabrook.com.zweatherapp.Configs.CommonConstants.CITY_ID;
import static zmabrook.com.zweatherapp.Configs.CommonConstants.CITY_NAME;

/**
 * Created by zMabrook on 26/01/18.
 */
public class DetailsActivity extends BaseActivity implements DetailsContract.View{
    @BindView(R.id.tempratureTextView)
    TextView tempratureTextView;

    @BindView(R.id.cityNameTextView)
    TextView cityNameTextView;

    @BindView(R.id.descriptionTextView)
    TextView descriptionTextView;

    @BindView(R.id.nextDaysRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.addCityToHomeButton)
    Button addCityToHomeButton;

    private DetailsContract.Actions mPresenter;
    private String mCityId;
    private String mCityName;
    ProgressDialog progress;
    private DetailsRecyclerViewAdapter adapter;
    private AddCityListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        mPresenter = new DetailsPresenter(this);

        mCityId = getIntent().getStringExtra(CITY_ID);
        mCityName = getIntent().getStringExtra(CITY_NAME);
        listener = (AddCityListener) getIntent().getSerializableExtra(ADD_CITY_LISTENER);

        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Please Wait");
        progress.setCancelable(false);
        progress.show();


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DetailsRecyclerViewAdapter(new ArrayList<WeatherItem>(),getApplicationContext());
        mRecyclerView.setAdapter(adapter);


        if (mCityId !=null){
            mPresenter.getFiveDaysForecastByID(mCityId);
        }else if(mCityName !=null){
            mPresenter.getFiveDaysForecastByName(mCityName);
        }

    }


    @Override
    public void loadData(final FiveDaysResponse response) {
        if (response == null){
            showToastAndDismiss(getString(R.string.city_not_found));
            return;
        }
        cityNameTextView.setText((response.getCity().getName()!=null)?response.getCity().getName():"");
        descriptionTextView.setText((response.getList().get(0).getWeather().get(0).getDescription()!=null)?response.getList().get(0).getWeather().get(0).getDescription():"");
        tempratureTextView.setText((String.valueOf(response.getList().get(0).getMain().getTemp())!=null)?(String.valueOf(response.getList().get(0).getMain().getTemp()))+"°":"");

        adapter = new DetailsRecyclerViewAdapter(response.getList(),getApplicationContext());
        mRecyclerView.setAdapter(adapter);
        progress.dismiss();

        addCityToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherItem item = response.getList().get(0);
                item.setId(response.getCity().getId());
                item.setName(response.getCity().getName());
                CommonConstants.home.addWeatherItem(item);
                finish();
            }
        });

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToastAndDismiss(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        progress.dismiss();
        finish();
    }


}
