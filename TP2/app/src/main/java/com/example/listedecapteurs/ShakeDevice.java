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
    private boolean hasFlashlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_device);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor_Acc = sensorManager.getDefaultSensor(TYPE_ACCELEROMETER);
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
            Boolean hasFlashlight = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


        sensorManager.registerListener(this, sensor_Acc, SensorManager.SENSOR_DELAY_UI);








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



    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}