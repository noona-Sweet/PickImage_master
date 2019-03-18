package com.vansuita.pickimage.sample.act;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vansuita.pickimage.sample.R;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

public class Location extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final int MY_PERMISSION_FOR_ACCESS_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_location);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getPermissionToAccessLocation();
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        Intent intent = getIntent();
        String locationName = intent.getStringExtra("name");
        String[] locationArray = intent.getStringExtra("location").split("/");

        final Double latitude = Double.valueOf(locationArray[0]);
        final Double longitude = Double.valueOf(locationArray[1]);


        final LatLng PL = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(PL).title(locationName).snippet(locationName)).showInfoWindow();
        CameraPosition cameraPosition = new CameraPosition.Builder().target(PL).zoom(12).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSION_FOR_ACCESS_LOCATION) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Read LOCATION permission denied", Toast.LENGTH_SHORT).show();
            } else {

                boolean showRationale = shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION);


                if (showRationale) {
                    // do something here to handle degraded mode
                } else {
                    Toast.makeText(this, "Read LOCATION permission granted", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }


    @TargetApi(23)
    public void getPermissionToAccessLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            shouldShowRequestPermissionRationale(
                    Manifest.permission.ACCESS_COARSE_LOCATION);
            shouldShowRequestPermissionRationale(
                    Manifest.permission.ACCESS_FINE_LOCATION);


            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    }, MY_PERMISSION_FOR_ACCESS_LOCATION
            );

        }

    }
}
