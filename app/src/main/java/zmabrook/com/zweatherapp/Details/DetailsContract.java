package zmabrook.com.zweatherapp.Details;

import zmabrook.com.zweatherapp.Entities.FiveDaysResponse;

/**
 * Created by zMabrook on 26/01/18.
 */

public interface DetailsContract {
    public interface View{
        public void loadData(FiveDaysResponse response);
        public void showToast(String message);
        public void showToastAndDismiss(String message);


    }

    public interface Actions{
        public void getFiveDaysForecastByID(String cityId);
        public void getFiveDaysForecastByName(String cityName);

    }

}
