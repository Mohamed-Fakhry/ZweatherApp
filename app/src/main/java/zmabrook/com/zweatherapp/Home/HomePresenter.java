package zmabrook.com.zweatherapp.Home;

import android.content.Context;
import android.content.SharedPreferences;

import zmabrook.com.zweatherapp.Configs.CommonConstants;
import zmabrook.com.zweatherapp.base.BasePresenter;




/**
 * Created by zmabrook on 26/01/18.
 */

public class HomePresenter extends BasePresenter implements HomeContract.Actions {
    private HomeContract.View mView;
    private HomeContract.model mModel;

    public HomePresenter(HomeContract.View mView) {
        this.mView = mView;
    }

    @Override
    public boolean isFirstUseOfApp(Context context) {
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences(CommonConstants.sharedPrefName, 0);
        SharedPreferences.Editor editor = pref.edit();

        boolean result = pref.getBoolean(CommonConstants.isFirstUse, true);

        editor.putBoolean(CommonConstants.isFirstUse,true);
        editor.commit();

        return result;
    }
}
