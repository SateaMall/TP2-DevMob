package com.example.listedecapteurs;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;

import static androidx.core.math.MathUtils.clamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;

public class Accelerometer extends AppCompatActivity implements SensorEventListener {
    private FloatingActionButton myButton;
    private SensorManager sensorManager;
    private Sensor sensor_Acc;

    private View view;
    private final float THRESHOLD_MEDIUM = 1.2f;
    private final float THRESHOLD_HIGH = 1.6f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor_Acc = sensorManager.getDefaultSensor(TYPE_ACCELEROMETER);

        sensorManager.registerListener(this, sensor_Acc, SensorManager.SENSOR_DELAY_UI);

        // View
        view = findViewById(R.id.backgroundView);
        //view.setBackgroundColor(Color.GREEN);

        // Back button
        myButton = findViewById(R.id.my_button);
            myButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Accelerometer.this, ChoseApplication.class);
                    startActivity(intent);
                }
            });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Check if the sensor changement is from the accelerometer
        if (event.sensor.getType()== TYPE_ACCELEROMETER){ onAccelerometerChanged(event); }
    }
    private void onAccelerometerChanged(SensorEvent event) {
        // Get accelometer values
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float magnitude = (float) Math.sqrt(x * x + y * y + z * z) / SensorManager.GRAVITY_EARTH;

        if (magnitude < THRESHOLD_MEDIUM) {
            // Low values, set background to green
            view.setBackgroundColor(Color.GREEN);
        } else if (magnitude < THRESHOLD_HIGH) {
            // Medium values, set background to black
            view.setBackgroundColor(Color.BLACK);
        } else {
            // High values, set background to red
            view.setBackgroundColor(Color.RED);
        }

    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
    @Override
    protected void onResume() {
        super.onResume();
        // Register this class as a listener for the accelerometer sensor
        sensorManager.registerListener(this, sensor_Acc, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Unregister the sensor when the activity pauses.
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}