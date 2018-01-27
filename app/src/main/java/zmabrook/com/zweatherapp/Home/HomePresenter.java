package zmabrook.com.zweatherapp.Home;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zmabrook.com.zweatherapp.Configs.CommonConstants;
import zmabrook.com.zweatherapp.Entities.AutoComplete;
import zmabrook.com.zweatherapp.Entities.StructuredFormatting;
import zmabrook.com.zweatherapp.Entities.WeatherItem;
import zmabrook.com.zweatherapp.Listeners.GenericListener;
import zmabrook.com.zweatherapp.Network.RetroFit.PlacesEndpoints;
import zmabrook.com.zweatherapp.Network.RetroFit.RetrofitPlacesClient;
import zmabrook.com.zweatherapp.Network.RetroFit.RetrofitWeatherClient;
import zmabrook.com.zweatherapp.Network.RetroFit.WeatherEndpoints;
import zmabrook.com.zweatherapp.Utils.LocationUtil;
import zmabrook.com.zweatherapp.Utils.PermissionUtil;
import zmabrook.com.zweatherapp.base.BasePresenter;

import static zmabrook.com.zweatherapp.Configs.CommonConstants.DEFAULT_CITY;
import static zmabrook.com.zweatherapp.Configs.CommonConstants.GPLACES_API_KEY;
import static zmabrook.com.zweatherapp.Configs.CommonConstants.WEATHER_API_KEY;
import static zmabrook.com.zweatherapp.Configs.CommonConstants.WEATHER_UNIT_CELICUS;


/**
 * Created by zmabrook on 26/01/18.
 */

public class HomePresenter extends BasePresenter implements HomeContract.Actions {
    private HomeContract.View mView;
    private WeatherEndpoints weatherEndpoints;
    private PlacesEndpoints placesEndpoints;

    public HomePresenter(HomeContract.View mView) {
        this.mView = mView;
        weatherEndpoints = RetrofitWeatherClient.getClient().create(WeatherEndpoints.class);
    }

    @Override
    public boolean isFirstUseOfApp(Context context) {
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences(CommonConstants.SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();

        boolean result = pref.getBoolean(CommonConstants.IS_FIRST_USE, true);
        editor.putBoolean(CommonConstants.IS_FIRST_USE,false);
        editor.commit();

        return result;
    }



    @Override
    public void loadFirstCity(final Activity activity , final LocationUtil locationUtil) {
        PermissionUtil.askForLocationPermission(activity);

        if (!locationUtil.canGetLocation()) {
            locationUtil.showSettingsAlert(new GenericListener() {
                @Override
                public void onSuccess() {
                    if (PermissionUtil.isLocationPermissionGranted(activity) && locationUtil.canGetLocation()&&
                            locationUtil.getLatitude()!=null && locationUtil.getLongitude()!=null ) {
                        getWeatherItemByLatLong(locationUtil.getLatitude(),locationUtil.getLongitude());
                    }
                }

                @Override
                public void onFailure() {
                    getWeatherItemByName(DEFAULT_CITY);
                }
            });


        }else{
            if (locationUtil.canGetLocation()&&
                    locationUtil.getLatitude()!=null && locationUtil.getLongitude()!=null)
                getWeatherItemByLatLong(locationUtil.getLatitude(),locationUtil.getLongitude());
            else
            getWeatherItemByName(DEFAULT_CITY);
        }

    }

    private void getWeatherItemByName(String cityName){
        Call<WeatherItem> call = weatherEndpoints.getWeatherItemByName(WEATHER_API_KEY,WEATHER_UNIT_CELICUS,cityName);
        call.enqueue(new Callback<WeatherItem>() {
            @Override
            public void onResponse(Call<WeatherItem> call, Response<WeatherItem> response) {
                mView.addWeatherItem(response.body());
            }

            @Override
            public void onFailure(Call<WeatherItem> call, Throwable t) {
                mView.showToast(t.getMessage());
            }
        });
    }

    private void getWeatherItemByLatLong(String lat,String lng){
        Call<WeatherItem> call = weatherEndpoints.getWeatherItemByLatLong(WEATHER_API_KEY,WEATHER_UNIT_CELICUS,
                lat,lng);
        call.enqueue(new Callback<WeatherItem>() {
            @Override
            public void onResponse(Call<WeatherItem> call, Response<WeatherItem> response) {
                mView.addWeatherItem(response.body());
            }

            @Override
            public void onFailure(Call<WeatherItem> call, Throwable t) {
                mView.showToast(t.getMessage());
            }
        });
    }

    private ArrayList<StructuredFormatting> generateSuggestionsFromApiResult(AutoComplete.AutoCompleteResult result){
        ArrayList<AutoComplete.Prediction> predictions = result.getPredictions();
        ArrayList<StructuredFormatting> suggestions = new ArrayList<>();
        for (int i=0 ; i < predictions.size()-1 ; i++){
            suggestions.add(predictions.get(i).getStructuredFormatting());
        }

        return suggestions;
    }

    @Override
    public void getCitySuggestions(String query) {
        placesEndpoints = RetrofitPlacesClient.getClient().create(PlacesEndpoints.class);
        Call<AutoComplete.AutoCompleteResult> autoCompleteResultCall = placesEndpoints.getCitySuggestions(query,"(cities)",GPLACES_API_KEY);
        autoCompleteResultCall.enqueue(new Callback<AutoComplete.AutoCompleteResult>() {
            @Override
            public void onResponse(Call<AutoComplete.AutoCompleteResult> call, Response<AutoComplete.AutoCompleteResult> response) {
                mView.showSearchSuggestions(generateSuggestionsFromApiResult(response.body()));
            }

            @Override
            public void onFailure(Call<AutoComplete.AutoCompleteResult> call, Throwable t) {
                mView.showToast(t.getMessage());
            }
        });
    }
}
