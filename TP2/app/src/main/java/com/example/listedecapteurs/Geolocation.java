package com.example.listedecapteurs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.health.connect.datatypes.ExerciseRoute;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Geolocation extends AppCompatActivity {
    private FloatingActionButton myButton;
    private SensorManager sensorManager;

    //private TextView locationTextView;
    private LocationManager locationManager;

    //    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocation);

        //locationTextView = findViewById(R.id.locationTextView);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION},
                0);


        /******************************* Button *********************/
        myButton = findViewById(R.id.my_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Geolocation.this, ChoseApplication.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = locationManager
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
            String lat = null;
            String longi = null;
            if (location != null) {
                lat = String.valueOf(location.getLatitude());
                longi = String.valueOf(location.getLongitude());
            }

            String s = "Lattitude="+lat+" Longitude="+longi;
            Toast.makeText(this,s, Toast.LENGTH_SHORT).show();

            //locationTextView.setText(s);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}