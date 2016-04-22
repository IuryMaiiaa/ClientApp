package HuntApi.ControleGeolocalizacao.GoogleMaps;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import HuntApi.Model.CordenadaGeografica;

/**
 * Created by Iury on 4/15/2016.
 */
public class getGoogleServiceClient {

    private GoogleApiClient mGoogleApiClient;

    public GoogleApiClient getGoogleApiClient(Activity activity, GoogleApiClient.ConnectionCallbacks connectionCallBacks,
                                              GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(activity)
                    .addConnectionCallbacks(connectionCallBacks)
                    .addOnConnectionFailedListener(onConnectionFailedListener)
                    .addApi(LocationServices.API)
                    .build();
        }
        return mGoogleApiClient;
    }




}
