package com.app.code;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
public class GetLocation extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener {
    public static final int RequestPermissionCode = 1;
    protected GoogleApiClient googleApiClient;
    protected TextView longitudeText;
    protected TextView latitudeText;
    protected Location lastLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    CardView nearbysites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
        nearbysites = (CardView)findViewById(R.id.nearbysites);
        longitudeText = (TextView) findViewById(R.id.longitude_text);
        latitudeText = (TextView) findViewById(R.id.latitude_text);
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);


        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        } else {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(final Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
//                                latitudeText.setText(String.valueOf(location.getLatitude()));
//                                longitudeText.setText(String.valueOf(location.getLongitude()));
//
//
//                                nearbyhospital.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        String x = String.valueOf(location.getLatitude());
//                                        String y = String.valueOf(location.getLongitude());
//
//                                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                                                Uri.parse("http://maps.google.com/maps?&saddr="
//                                                        + x
//                                                        + ","
//                                                        + y
//                                                        + "&daddr=nearby hospitals"
//
//                                                ));
//                                        startActivity(intent);
//
//                                    }
//                                });
//
//                                nearbymedicalshop.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        String x = String.valueOf(location.getLatitude());
//                                        String y = String.valueOf(location.getLongitude());
//
//                                        Intent intent1 = new Intent(android.content.Intent.ACTION_VIEW,
//                                                Uri.parse("http://maps.google.com/maps?&saddr="
//                                                        + x
//                                                        + ","
//                                                        + y
//                                                        + "&daddr=nearby medical shops"
//
//                                                ));
//                                        startActivity(intent1);
//                                    }
//                                });


                            }
                        }
                    });
        }

    }



    private void requestPermission() {
        ActivityCompat.requestPermissions(GetLocation.this, new
                String[]{ACCESS_FINE_LOCATION}, RequestPermissionCode);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("MainActivity", "Connection failed: " + connectionResult.getErrorCode());
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e("MainActivity", "Connection suspendedd");
    }

}