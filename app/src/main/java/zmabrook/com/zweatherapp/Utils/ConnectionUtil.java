package zmabrook.com.zweatherapp.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import zmabrook.com.zweatherapp.R;

/**
 * Created by zMabrook on 26/01/18.
 */

public class ConnectionUtil {

    public static boolean isConnected(Context context) {

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (!isConnected)
            Toast.makeText(context, R.string.no_internet_message,Toast.LENGTH_LONG).show();
        return isConnected;
    }
}
