package com.app.code;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.code.sql.PlaceDataSource;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;




public class placess extends AppCompatActivity implements OnMapReadyCallback {

    Button btnSave;
    EditText txtName, txtType, txtImages, txtDescription;
    PlaceDataSource placeDataSource;
    GoogleMap map;
    MarkerOptions markerOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        // Init datasource
        placeDataSource = new PlaceDataSource(this);
        placeDataSource.open();

       // Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );

        btnSave = (Button) findViewById(R.id.btnSave);
       // btnSave.setTypeface(font);
        txtName = (EditText) findViewById(R.id.txtName);
        txtImages = (EditText) findViewById(R.id.txtImages);
        txtType = (EditText) findViewById(R.id.txtType);
        txtDescription = (EditText) findViewById(R.id.txtDescription);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                String type = txtType.getText().toString();
                String description = txtDescription.getText().toString();
                String images = txtImages.getText().toString();
                double lat = 0;
                double lng = 0;
                if(markerOptions != null) {
                    lat = markerOptions.getPosition().latitude;
                    lng = markerOptions.getPosition().longitude;
                }
              //  placeDataSource.savePlace(name, type, description, (float)lng, (float)lng, images);
                Toast.makeText(placess.this, "Saved " + name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        placeDataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        placeDataSource.open();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                map.clear();
                markerOptions = new MarkerOptions();
                markerOptions.position(latLng).title("Place");
                map.addMarker(markerOptions);
            }
        });

    }
}
