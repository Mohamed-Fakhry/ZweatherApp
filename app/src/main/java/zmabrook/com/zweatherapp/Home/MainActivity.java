package zmabrook.com.zweatherapp.Home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zmabrook.com.zweatherapp.R;
import zmabrook.com.zweatherapp.base.BaseActivity;
/**
 * Created by zmabrook on 26/01/18.
 */
public class MainActivity extends BaseActivity implements  HomeContract.View {
    private HomeContract.Actions mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new HomePresenter(this);
    }
}
