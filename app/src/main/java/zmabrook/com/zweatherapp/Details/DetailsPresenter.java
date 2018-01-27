package zmabrook.com.zweatherapp.Details;

import zmabrook.com.zweatherapp.base.BasePresenter;

/**
 * Created by zMabrook on 26/01/18.
 */

public class DetailsPresenter extends BasePresenter implements DetailsContract.Actions {
    private DetailsContract.View mView;

    public DetailsPresenter(DetailsContract.View mView) {
        this.mView = mView;
    }
}
