package com.rpifinal.hitema.controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rpifinal.hitema.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    // =============================================================================================
    ////////////////////////////////
    // MEMBERS VARIABLE
    ///////////////////////////////

    private static final int REQUEST_ACCESS_FINE_LOCATION = 628;

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    // =============================================================================================
    ////////////////////////////////
    // ACTIVITY LIFE CYCLE
    ///////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        checkPermissions();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //maps.putExtra("latitute", 48.825913);
        //maps.putExtra("longitude", 2.267375);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(48.825913, 2.267375);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Hitema"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    // =============================================================================================
    ////////////////////////////////
    // LOCATION
    ///////////////////////////////

    @SuppressLint("MissingPermission")
    void getLocation() {

        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {

            if (location != null) {

            }
        });
    }
    // =============================================================================================
    ////////////////////////////////
    // PERMISSIONS
    ///////////////////////////////

    @RequiresApi(api = Build.VERSION_CODES.M)
    void checkPermissions() {

        // Permission is not yet granted
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.


            // Should we show an explanation ?
            if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION))
            {
                // Show an explanation & try to request the permission again
            }
            else // Ask for permission
            {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ACCESS_FINE_LOCATION);
            }
        }
        else // Permission is already granted
        {
            getLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    getLocation();
                }
                else
                {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
            }
            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}