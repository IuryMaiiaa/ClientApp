package HuntApi.ControleGeolocalizacao.GoogleMaps;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.iury.clientapp.MapsActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;

import HuntApi.Model.CordenadaGeografica;

/**
 * Created by Iury on 4/15/2016.
 */
public class getGoogleServiceClient {

    private static GoogleApiClient mGoogleApiClient;
    private static MapsActivity mapsActivity;
    private static CordenadaGeografica posicaoAtual;
    private static GoogleMap maps;

    public GoogleApiClient getGoogleApiClient(MapsActivity activity, GoogleApiClient.ConnectionCallbacks connectionCallBacks,
                                              GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(activity)
                    .addConnectionCallbacks(connectionCallBacks)
                    .addOnConnectionFailedListener(onConnectionFailedListener)
                    .addApi(LocationServices.API)
                    .build();
            this.mapsActivity = activity;
        }
        return mGoogleApiClient;
    }

    public void addGoogleMaps(GoogleMap maps) {
        this.maps = maps;
    }

    public void GoogleApiConnect() {

        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }

    }

    public void GoogleApiDisconnet() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
    }

    public static CordenadaGeografica getPosicaoAtual() {
        CordenadaGeografica posicaoAtual = new CordenadaGeografica();


        Location mLastLocation = mapsActivity.getMyLocation();
        if (mLastLocation != null) {
            posicaoAtual.setLat(mLastLocation.getLatitude());
            posicaoAtual.setLon(mLastLocation.getLongitude());
            Log.d("cordenada", posicaoAtual.getLat() + " " + posicaoAtual.getLon());
        }

        return posicaoAtual;
    }

    private static void getPermissoesPosicao() {
        posicaoAtual = new CordenadaGeografica();

        if (ActivityCompat.checkSelfPermission(mapsActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mapsActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }
        Log.d("cordenada", posicaoAtual.getLat() + " " + posicaoAtual.getLon());
        Location mLastLocation = maps.getMyLocation();
        //LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            posicaoAtual.setLat(mLastLocation.getLatitude());
            posicaoAtual.setLon(mLastLocation.getLongitude());
            Log.d("cordenada", posicaoAtual.getLat() + " " + posicaoAtual.getLon());
        }

    }



}
