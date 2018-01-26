package zmabrook.com.zweatherapp.Network.RetroFit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zmabrook.com.zweatherapp.Entities.WeatherItem;

/**
 * Created by zMabrook on 26/01/18.
 */

public interface WeatherInterface {
    @GET("/weather")
    Call<WeatherItem> getWeatherItemById(@Query("APPID") String weatherApiKey ,@Query("unit") String unit
            ,@Query("id") String cityId );

    @GET("/weather")
    Call<WeatherItem> getWeatherItemByLatLong (@Query("APPID") String weatherApiKey
            ,@Query("unit") String unit ,@Query("lat") String lat , @Query("lon") String lng);

    @GET("/weather")
    Call<WeatherItem> getWeatherItemByName(@Query("APPID") String weatherApiKey ,@Query("unit") String unit
            ,@Query("q") String cityName );

    @GET("/group")
    Call<ArrayList<WeatherItem>> getWeatherItemsById(@Query("APPID") String weatherApiKey , @Query("unit") String unit
            , @Query("id") String citiesId );
}
