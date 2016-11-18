package com.example.iury.clientapp;

import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import HuntApi.ControleGeolocalizacao.GoogleMaps.HuntApiGoogleServiceGerente;
import HuntApi.ControleInteracaoJogo.UsuarioControlers.UsuarioControlerInteracao;
import HuntApi.Model.CoordenadaGeografica;

public class MapsActivity extends FragmentActivity  implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, mapsFragment.OnFragmentInteractionListener {

    private GoogleMap mMap;
    private CoordenadaGeografica cordenadaAtual;
    private GoogleApiClient mGoogleApiClient;
    private HuntApiGoogleServiceGerente huntApiGoogleServiceGerente;
    private Location mLocationRequest;

    public MapsActivity() {
        super();
        huntApiGoogleServiceGerente = new HuntApiGoogleServiceGerente();
        cordenadaAtual = new CoordenadaGeografica();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //setContentView(R.layout.fragment_maps);


        huntApiGoogleServiceGerente.getGoogleApiClient(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    protected  void onStart() {
        huntApiGoogleServiceGerente.GoogleApiConnect();
        super.onStart();
    }

    protected void onStop() {
        huntApiGoogleServiceGerente.GoogleApiDisconnet();
        super.onStop();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        //getgoogleServiceGerente.atualizarMapa(mMap);
        huntApiGoogleServiceGerente.addGoogleMaps(mMap);
        UsuarioControlerInteracao.atulizarUsuarioSessao();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    /*
    @Override
    public void onConnected(Bundle bundle) {
        //Log.d("posicao","chego aqui");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        if (mLastLocation != null) {
            cordenadaAtual.setLat(mLastLocation.getLatitude());
            cordenadaAtual.setLon(mLastLocation.getLongitude());
        }
        //Log.d("posicao", cordenadaAtual.getLat() + " " + cordenadaAtual.getLon() + " cordenada");

        LatLng Quixada = new LatLng(cordenadaAtual.getLat(), cordenadaAtual.getLon());
        mMap.addMarker(new MarkerOptions().position(Quixada).title("Marcar Quixada"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Quixada));

        getgoogleServiceGerente.onConnected(bundle);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
*/

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
