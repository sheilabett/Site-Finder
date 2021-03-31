package com.app.code;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.app.code.GetLocation.RequestPermissionCode;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Get_Direct extends FragmentActivity implements OnMapReadyCallback, LocationListener,
        GoogleMap.OnMarkerClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public double currentLatitude;
    public static final int ROUND = 10;
    public double currentLongitude;
    Button hospital;
    private GoogleMap mMap;
    public GoogleApiClient googleApiClient;
    private ChildEventListener mChildEventListener;
    private DatabaseReference mUsers;
    Marker marker;
    LocationRequest mLocationRequest;
    SupportMapFragment supportMapFragment;
    public FusedLocationProviderClient fusedLocationProviderClient;
    FusedLocationProviderClient mFusedLocationClient;
    double currentlatitude = 0, currentlongitude = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get__direct);
        buildGoogleApiClient();
        googleApiClient.connect();

        //defining the buttons
        //hospital = findViewById(R.id.hospital);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //   setUpMap();

        if (ActivityCompat.checkSelfPermission(Get_Direct.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(Get_Direct.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 45);

        }


        //calling google api's client to establish location connections
       /* googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        //fusedLocionProviderClient provides the current location coordinates
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        NotificationChannel channel = new NotificationChannel
                ("MyNotification","MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);
        }



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/
        ChildEventListener mChildEventListener;
        mUsers = FirebaseDatabase.getInstance().getReference("coordinates");
        mUsers.push().setValue(marker);
        onMapReady(mMap);
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.
                checkSelfPermission(this, Manifest.permission
                        .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        Task<Location> task = mFusedLocationClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentlatitude = location.getLatitude();
                    currentlongitude = location.getLongitude();
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {

                            //Add a marker in location and move the camera
                            mMap = googleMap;
                            LatLng location = new LatLng(currentlatitude, currentlongitude);
                            CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(10).build();
                            googleMap.addMarker(new MarkerOptions().position(location).title(" Your Current location"))
                                    .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 10));

                        }
                    });
                }

            }


        });


        // return;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    Coordinates points = s.getValue(Coordinates.class);
                    LatLng location = new LatLng(points.latitude, points.longitude);
                    mMap.addMarker(new MarkerOptions().position(location).title(points.name))
                            .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 10));

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
//       googleApiClient.connect();              //establishment of google api client connection
    }

    @Override
    protected void onResume() {
        super.onResume();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);

    }

    @Override
    protected void onStop() {
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();       //discarding the google api client service if the activity is in onStop condition
        }
        super.onStop();
    }

    protected synchronized void buildGoogleApiClient() {
        //Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
        googleApiClient = new GoogleApiClient.Builder(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        } else {
            fusedLocationProviderClient.getLastLocation()
                    //Through this we are fetching the last location of user recorded
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(final Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                //making button clickable and to open the maps with nearby bus and metro stations from your current location

                                /* hospital.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String x = String.valueOf(location.getLatitude());
                                                String y = String.valueOf(location.getLongitude());

                                                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                                        Uri.parse("http://maps.google.com/maps?&saddr="
                                                        + x
                                                        + ","
                                                        + y
                                                        + "&daddr=nearby hospitals"
                                                        ));
                                        startActivity(intent);

                                            }
                                        });*/

                            }

                        }
                    });
        }

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(Get_Direct.this, new
                String[]{ACCESS_FINE_LOCATION}, RequestPermissionCode);
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onLocationChanged(Location location) {
        currentLatitude = location.getLatitude();               //getting current latitude
        currentLongitude = location.getLongitude();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

}

