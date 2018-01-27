package zmabrook.com.zweatherapp.Home;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import zmabrook.com.zweatherapp.Entities.StructuredFormatting;
import zmabrook.com.zweatherapp.Entities.WeatherItem;
import zmabrook.com.zweatherapp.Utils.LocationUtil;

/**
 * Created by zmabrook on 26/01/18.
 */

public interface HomeContract {
    public interface View{
        public void addWeatherItem(WeatherItem weatherItem);
        public void showToast(String message);
        public void showSearchSuggestions(ArrayList<StructuredFormatting> suggestions);
    }

    public interface Actions{
        public boolean isFirstUseOfApp(Context context);
        public void loadFirstCity(Activity activity , LocationUtil locationUtil);
        public void getCitySuggestions(String query);
    }


}
