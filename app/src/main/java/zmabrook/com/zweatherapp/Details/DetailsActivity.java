package zmabrook.com.zweatherapp.Details;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import zmabrook.com.zweatherapp.Entities.FiveDaysResponse;
import zmabrook.com.zweatherapp.R;
import zmabrook.com.zweatherapp.base.BaseActivity;

import static zmabrook.com.zweatherapp.Configs.CommonConstants.CITY_ID;
import static zmabrook.com.zweatherapp.Configs.CommonConstants.CITY_NAME;

/**
 * Created by zMabrook on 26/01/18.
 */
public class DetailsActivity extends BaseActivity implements DetailsContract.View{
    private DetailsContract.Actions mPresenter;
    private String cityId ;
    private String cityName;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mPresenter = new DetailsPresenter(this);

        cityId = getIntent().getStringExtra(CITY_ID);
        cityName = getIntent().getStringExtra(CITY_NAME);

        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setCancelable(false);
        progress.show();


        progress.dismiss();


        if (cityId!=null){
            mPresenter.getFiveDaysForecastByID(cityId);
        }else if(cityName !=null){
            mPresenter.getFiveDaysForecastByName(cityName);
        }

    }


    @Override
    public void loadData(FiveDaysResponse response) {


        progress.dismiss();

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
