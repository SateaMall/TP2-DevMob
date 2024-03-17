package com.example.listedecapteurs;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;
import static android.hardware.Sensor.TYPE_GRAVITY;

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
/*
* on peux pas  calculer la direction du mouvement avec un unique capteur
* il te faut le capteur de gravité pour calculer la gravité et il te faut l’accéléromètre pour calculer la vitesse vers une direction
* l'accéléromètre va te fournir des valeur déformé par la gravité
* Du coup, tu soustrais les valeurs de l'accéléromètre de la gravité et hop t'as la direction
* */
public class Direction extends AppCompatActivity implements SensorEventListener {
    private FloatingActionButton myButton;
    private SensorManager sensorManager;
    private Sensor sensor_Acc;
    private Sensor sensorGravity;
    private float[] gravityValues = null;
    private float[] accelerometerValues = null;


    private TextView directionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor_Acc = sensorManager.getDefaultSensor(TYPE_ACCELEROMETER);
        sensorGravity = sensorManager.getDefaultSensor(TYPE_GRAVITY);
        directionTextView = findViewById(R.id.directionTextView);
        sensorManager.registerListener(this, sensor_Acc, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, sensorGravity, SensorManager.SENSOR_DELAY_UI);




        /******************************* Button *********************/
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
        switch (event.sensor.getType()) {
            case Sensor.TYPE_GRAVITY:
                gravityValues = event.values.clone();
                break;
            case Sensor.TYPE_ACCELEROMETER:
                accelerometerValues = event.values.clone();
                break;
        }

        if (gravityValues != null && accelerometerValues != null) {
            float[] linearAcceleration = new float[3];
            linearAcceleration[0] = accelerometerValues[0] - gravityValues[0];
            linearAcceleration[1] = accelerometerValues[1] - gravityValues[1];
            linearAcceleration[2] = accelerometerValues[2] - gravityValues[2];


            if (Math.abs(linearAcceleration[0]) > Math.abs(linearAcceleration[1])) {
                if (linearAcceleration[0] > 0) {
                    directionTextView.setText("Left");
                } else {
                    directionTextView.setText("Right");
                }
            } else {
                if (linearAcceleration[1] > 0) {
                    directionTextView.setText("Down");
                } else {
                    directionTextView.setText("Up");
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
}