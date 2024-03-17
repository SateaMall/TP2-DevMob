package com.example.listedecapteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SensorAvailability extends AppCompatActivity {
    private FloatingActionButton myButton;
    private ListView sensorListView;
    private TextView sensorStatusTextView;
    private SensorManager sensorManager;
    private String[] sensorTypes ;
    private Map<String, Integer> sensorTypeMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_availability);

        //SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //Get view components
        sensorListView = findViewById(R.id.sensorListView);
        sensorStatusTextView = findViewById(R.id.sensorStatusTextView);

        //Give values to the attributes
        sensorTypes = new String[]{"Accelerometer", "Gyroscope", "Magnetic Field", "Light", "Proximity","Barometer","Temperature","Relative Humidity","Gravity","Linear Acceleration","Step Detector","Step Counter","Significant Motion","Heart Rate"};
        sensorTypeMap = new HashMap<>();
        sensorTypeMap.put("Accelerometer", Sensor.TYPE_ACCELEROMETER);
        sensorTypeMap.put("Gyroscope", Sensor.TYPE_GYROSCOPE);
        sensorTypeMap.put("Magnetic Field", Sensor.TYPE_MAGNETIC_FIELD);
        sensorTypeMap.put("Light", Sensor.TYPE_LIGHT);
        sensorTypeMap.put("Proximity", Sensor.TYPE_PROXIMITY);
        sensorTypeMap.put("Barometer", Sensor.TYPE_PRESSURE);
        sensorTypeMap.put("Temperature", Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorTypeMap.put("Relative Humidity", Sensor.TYPE_RELATIVE_HUMIDITY);
        sensorTypeMap.put("Gravity", Sensor.TYPE_GRAVITY);
        sensorTypeMap.put("Linear Acceleration", Sensor.TYPE_LINEAR_ACCELERATION);
        sensorTypeMap.put("Rotation Vector", Sensor.TYPE_ROTATION_VECTOR);
        sensorTypeMap.put("Step Detector", Sensor.TYPE_STEP_DETECTOR);
        sensorTypeMap.put("Step Counter", Sensor.TYPE_STEP_COUNTER);
        sensorTypeMap.put("Significant Motion", Sensor.TYPE_SIGNIFICANT_MOTION);
        sensorTypeMap.put("Heart Rate", Sensor.TYPE_HEART_RATE);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sensorTypes);
        sensorListView.setAdapter(adapter);


        Sensor defaultProximitySensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        Log.v("SensorActivity", "defaultProximitySensor = " +
                defaultProximitySensor.getName());

        sensorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedSensor = sensorTypes[position];
                checkSensorAvailability(selectedSensor);
            }
        });
/******************************* Button *********************/

        myButton = findViewById(R.id.my_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SensorAvailability.this, ChoseApplication.class);
                startActivity(intent);
            }
        });
    }

    private void checkSensorAvailability(String sensorName) {
        int sensorType = sensorTypeMap.get(sensorName);
        Sensor sensor = sensorManager.getDefaultSensor(sensorType);
        if (sensor != null) {
            sensorStatusTextView.setText(sensorName + " is available.");
        } else {
            sensorStatusTextView.setText(sensorName + " is not available on your device.");
        }
    }

}