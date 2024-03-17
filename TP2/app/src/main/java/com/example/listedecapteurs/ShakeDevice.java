package com.example.listedecapteurs;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShakeDevice extends AppCompatActivity implements SensorEventListener {
    private FloatingActionButton myButton;
    private SensorManager sensorManager;
    private CameraManager cameraManager;
    private Sensor sensor_Acc;
    private String cameraId;
    private boolean isFlashlightOn = false;
    private static final int SHAKE_THRESHOLD = 6;
    private long lastShakeTimestamp = 0; // Time stamp is to avoid the flash goes on and off in the same movement. so we put a delay
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_device);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor_Acc = sensorManager.getDefaultSensor(TYPE_ACCELEROMETER);
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


        sensorManager.registerListener(this, sensor_Acc, SensorManager.SENSOR_DELAY_GAME);

/******************************* Button *********************/
        myButton = findViewById(R.id.my_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShakeDevice.this, ChoseApplication.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()== TYPE_ACCELEROMETER){ onAccelerometerChanged(event); }
    }
    private void onAccelerometerChanged(SensorEvent event) {
        long currentTimeMillis = System.currentTimeMillis();
        if ((currentTimeMillis - lastShakeTimestamp) > 1000) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float shakeMagnitude = (float) Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH;
            ;
            if (shakeMagnitude > SHAKE_THRESHOLD) {
                toggleFlashlight();
                lastShakeTimestamp = currentTimeMillis;
            }
        }


    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this); //Stop the sensor when the app is in the background to conserve battery
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL); // Resume listening to sensor events when the app is been reopened from the background
    }
    private void toggleFlashlight() {
        isFlashlightOn = !isFlashlightOn;
        try {
            cameraManager.setTorchMode(cameraId, isFlashlightOn);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}