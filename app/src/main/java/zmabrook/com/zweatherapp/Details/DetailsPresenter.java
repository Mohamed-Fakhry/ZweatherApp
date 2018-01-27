package zmabrook.com.zweatherapp.Details;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zmabrook.com.zweatherapp.Entities.FiveDaysResponse;
import zmabrook.com.zweatherapp.Network.RetroFit.RetrofitWeatherClient;
import zmabrook.com.zweatherapp.Network.RetroFit.WeatherEndpoints;
import zmabrook.com.zweatherapp.base.BasePresenter;

import static zmabrook.com.zweatherapp.Configs.CommonConstants.WEATHER_API_KEY;
import static zmabrook.com.zweatherapp.Configs.CommonConstants.WEATHER_UNIT_CELICUS;

/**
 * Created by zMabrook on 26/01/18.
 */

public class DetailsPresenter extends BasePresenter implements DetailsContract.Actions {
    private DetailsContract.View mView;
    private WeatherEndpoints weatherEndpoints;

    public DetailsPresenter(DetailsContract.View mView) {

        this.mView = mView;
        weatherEndpoints = RetrofitWeatherClient.getClient().create(WeatherEndpoints.class);

    }

    @Override
    public void getFiveDaysForecastByID(String cityId) {
        Call<FiveDaysResponse> call = weatherEndpoints.get5DaysWeatherById(WEATHER_API_KEY,WEATHER_UNIT_CELICUS,cityId);
        call.enqueue(new Callback<FiveDaysResponse>() {
            @Override
            public void onResponse(Call<FiveDaysResponse> call, Response<FiveDaysResponse> response) {
                mView.loadData(response.body());
            }

            @Override
            public void onFailure(Call<FiveDaysResponse> call, Throwable t) {
                mView.showToastAndDismiss(t.getMessage());

            }
        });

    }

    @Override
    public void getFiveDaysForecastByName(String cityName) {
        Call<FiveDaysResponse> call = weatherEndpoints.get5DaysWeatherByName(WEATHER_API_KEY,WEATHER_UNIT_CELICUS,cityName);
        call.enqueue(new Callback<FiveDaysResponse>() {
            @Override
            public void onResponse(Call<FiveDaysResponse> call, Response<FiveDaysResponse> response) {
                mView.loadData(response.body());
            }

            @Override
            public void onFailure(Call<FiveDaysResponse> call, Throwable t) {
                mView.showToastAndDismiss(t.getMessage());

            }
        });
    }
}
