package com.example.listedecapteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoseApplication extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_application);
    // Link the buttons from XML to Java
    Button buttonListSensors = findViewById(R.id.buttonListSensors);
    Button buttonSensorAvailability = findViewById(R.id.buttonSensorAvailability);
    Button buttonAccelerometer = findViewById(R.id.buttonAccelerometer);
    Button buttonDirection = findViewById(R.id.buttonDirection);
    Button buttonShakeDevice = findViewById(R.id.buttonShakeDevice);
    Button buttonProximity = findViewById(R.id.buttonProximity);
    Button buttonGeolocation = findViewById(R.id.buttonGeolocation);

    // Set click listeners for each button
        buttonListSensors.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
            Intent intent = new Intent(ChoseApplication.this, Index.class);
            startActivity(intent);
    }
    });

        buttonSensorAvailability.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
            Intent intent = new Intent(ChoseApplication.this, SensorAvailability.class);
            startActivity(intent);
    }
    });

        buttonAccelerometer.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
            Intent intent = new Intent(ChoseApplication.this, Accelerometer.class);
            startActivity(intent);
    }
    });

        buttonDirection.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
            Intent intent = new Intent(ChoseApplication.this, Direction.class);
            startActivity(intent);
    }
    });

        buttonShakeDevice.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
            Intent intent = new Intent(ChoseApplication.this, ShakeDevice.class);
            startActivity(intent);
    }
    });

        buttonProximity.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
            Intent intent = new Intent(ChoseApplication.this, Proximity.class);
            startActivity(intent);
    }
    });

        buttonGeolocation.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
            Intent intent = new Intent(ChoseApplication.this, Geolocation.class);
            startActivity(intent);
    }
    });
}
}