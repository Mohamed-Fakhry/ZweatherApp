package zmabrook.com.zweatherapp.Home;

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
}
