package HuntApi.ControleGeolocalizacao.GoogleMaps;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Iury on 4/15/2016.
 */
public class getGoogleServiceClient {

    private GoogleApiClient mGoogleApiClient;

    public GoogleApiClient getmGoogleApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder()
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            return mGoogleApiClient;
        } else {
            return mGoogleApiClient;
        }
    }
}
