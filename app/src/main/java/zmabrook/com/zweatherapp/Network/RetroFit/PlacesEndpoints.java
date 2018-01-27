package zmabrook.com.zweatherapp.Network.RetroFit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zmabrook.com.zweatherapp.Entities.AutoComplete;
import zmabrook.com.zweatherapp.Entities.WeatherItem;

/**
 * Created by zMabrook on 27/01/18.
 */

public interface PlacesEndpoints {

    @GET("json")
    Call<AutoComplete.AutoCompleteResult> getCitySuggestions(@Query("input") String query , @Query("types") String types
            , @Query("key") String apikey );
}
