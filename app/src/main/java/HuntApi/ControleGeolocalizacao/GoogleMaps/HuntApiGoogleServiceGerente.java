package HuntApi.ControleGeolocalizacao.GoogleMaps;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.iury.clientapp.MapsActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import HuntApi.ControleComunicacaoServidor.ControleQuest.QuestHttpController;
import HuntApi.Model.CordenadaGeografica;
import HuntApi.Model.QuestGeolocalizada;

/**
 * Created by Iury on 4/15/2016.
 */
public class HuntApiGoogleServiceGerente implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static GoogleApiClient mGoogleApiClient;
    private static MapsActivity mapsActivity;
    private static CordenadaGeografica posicaoAtual;
    private static GoogleMap maps;

    private LocationRequest mRequestLocation;

    private String mLastUpdateTime;
    private Location mCurrentLocation;

    public GoogleApiClient getGoogleApiClient(MapsActivity activity) {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(activity)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            this.mapsActivity = activity;
        }
        return mGoogleApiClient;
    }

    public void addGoogleMaps(GoogleMap maps) {
        this.maps = maps;
    }

    public static GoogleMap getGoogleMaps() {
        return maps;
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

        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        //LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            posicaoAtual.setLat(mLastLocation.getLatitude());
            posicaoAtual.setLon(mLastLocation.getLongitude());
            Log.d("cordenada", posicaoAtual.getLat() + " " + posicaoAtual.getLon());
        }

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        iniciaAtualizacaoPosicoes();
        MoverCameraMapa(maps);
        Log.d("aqui","onconeccted");
    }

    private void iniciaAtualizacaoPosicoes() {
        mRequestLocation = new LocationRequest();

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
        Log.d("posicao","chego aqui location request");
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mRequestLocation, this);
        getPermissoesPosicao();
        atualizarMarcadores();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    public GoogleMap MoverCameraMapa(GoogleMap map) {
        LatLng posicao = new LatLng(posicaoAtual.getLat(), posicaoAtual.getLon());
        map.moveCamera(CameraUpdateFactory.newLatLng(posicao));
        return map;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("cordenada", location.getLatitude() + " " + location.getLongitude());
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        atualizaPosicao();
        MoverCameraMapa(maps);
        atualizarMarcadores();
    }

    public void atualizarMarcadores() {
        QuestHttpController questHttpController = new QuestHttpController();
        GerenciadorDeMarcadores gerenciadorDeMarcadores = new GerenciadorDeMarcadores();
        CordenadaGeografica cordena = HuntApiGoogleServiceGerente.getPosicaoAtual();
        Log.d("cordenada","" + cordena.getLat() + " " + cordena.getLon());
        List<QuestGeolocalizada> quests = questHttpController.listarProximas(cordena,10000);
        gerenciadorDeMarcadores.addQuestMapa( maps,quests);
    }

    public void atualizaPosicao() {
        getPermissoesPosicao();
        posicaoAtual = new CordenadaGeografica();
        posicaoAtual.setLat(mCurrentLocation.getLatitude());
        posicaoAtual.setLon(mCurrentLocation.getLongitude());
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}
