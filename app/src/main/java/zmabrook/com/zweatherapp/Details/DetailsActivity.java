package zmabrook.com.zweatherapp.Details;

import android.os.Bundle;

import zmabrook.com.zweatherapp.R;
import zmabrook.com.zweatherapp.base.BaseActivity;
/**
 * Created by zMabrook on 26/01/18.
 */
public class DetailsActivity extends BaseActivity implements DetailsContract.View{
    private DetailsContract.Actions mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mPresenter = new DetailsPresenter(this);
    }
}
