package zmabrook.com.zweatherapp.Network.RetroFit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zMabrook on 27/01/18.
 */

public class RetrofitPlacesClient {

    public static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
