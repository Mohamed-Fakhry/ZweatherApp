package zmabrook.com.zweatherapp.Home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.arlib.floatingsearchview.FloatingSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zmabrook.com.zweatherapp.R;
import zmabrook.com.zweatherapp.Utils.LocationUtil;
import zmabrook.com.zweatherapp.Utils.PermissionUtil;
import zmabrook.com.zweatherapp.base.BaseActivity;
/**
 * Created by zmabrook on 26/01/18.
 */
public class MainActivity extends BaseActivity implements  HomeContract.View {
    @BindView(R.id.floating_search_view)
    FloatingSearchView floatingSearchView;

    @BindView(R.id.home_recyclerView)
    RecyclerView recyclerView;


    private HomeContract.Actions mPresenter;
    private LocationUtil locationUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new HomePresenter(this);



        if (mPresenter.isFirstUseOfApp(this)){
            PermissionUtil.askForLocationPermission(this);
            //get User Location
            locationUtil = new LocationUtil(this);
            if (!locationUtil.canGetLocation()) {
                locationUtil.showSettingsAlert();

                //mPresenter.loadFirstCity();

            }else{

            }

            //load 1st city by location on London if no location available
        }else {

            //ge
        }
    }
}
