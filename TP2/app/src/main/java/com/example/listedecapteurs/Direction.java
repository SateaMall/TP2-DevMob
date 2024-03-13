package com.example.listedecapteurs;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Direction extends AppCompatActivity implements SensorEventListener {
    private FloatingActionButton myButton;
    private SensorManager sensorManager;
    private Sensor sensor_Acc;

    private TextView directionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor_Acc = sensorManager.getDefaultSensor(TYPE_ACCELEROMETER);
        directionTextView = findViewById(R.id.directionTextView);
        sensorManager.registerListener(this, sensor_Acc, SensorManager.SENSOR_DELAY_UI);


        myButton = findViewById(R.id.my_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Direction.this, ChoseApplication.class);
                startActivity(intent);
            }
        });
    }

    public void onSensorChanged(SensorEvent event) {
        // Check if the sensor changement is from the accelerometer
        if (event.sensor.getType()== TYPE_ACCELEROMETER){ onAccelerometerChanged(event); }
    }
    private void onAccelerometerChanged(SensorEvent event) {
        // Get accelometer values
        float[] values = event.values;
        float x = values[0];
        float z = values[2];

        if (Math.abs(x) > Math.abs(z)) {
            if (x > 0) {
                directionTextView.setText("Right");
            } else {
                directionTextView.setText("Left");
            }
        } else {
            if (z > 0) {
                directionTextView.setText("Up");
            } else {
                directionTextView.setText("Down");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
}