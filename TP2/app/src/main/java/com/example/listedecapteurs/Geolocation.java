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

    private TextView locationTextView;
    private LocationManager locationManager;

    //    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocation);

        locationTextView = findViewById(R.id.locationTextView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        Log.v("customGPS", "avant");
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        /*if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            Log.v("customGPS","dedant");
            //requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION});
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},0);
            } else {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},0);
            }
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }*/
        Log.v("customGPS", "après");


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

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 0) {
            Log.v("customGPS", "onRequestPermissionsResult");
            //Toast.makeText(this,"Coucou", Toast.LENGTH_SHORT).show();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            } else {
                Log.v("customGPS", "permissions for location granted");
            }
            Location location = locationManager
                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            String lat = null;
            String longi = null;
            //Log.v("customGPS",location.toString());

            Log.v("customGPS", "get all providers");
            for (String provider : locationManager.getAllProviders()) {
                Log.v("customGPS", provider);
            }

            for (String provider : locationManager.getProviders(true)) {
                Location l = locationManager.getLastKnownLocation(provider);
                Log.v("customGPS", provider);
                if (l == null) {
                    continue;
                } else {
                    Log.v("customGPS", location.toString());
                }
            }
            if (location != null) {
                lat = String.valueOf(location.getLatitude());
                longi = String.valueOf(location.getLongitude());
            }

            Log.v("customGPS","test"+lat);
            Log.v("customGPS","test"+longi);


            Log.v("customGPS","après les strings");
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}