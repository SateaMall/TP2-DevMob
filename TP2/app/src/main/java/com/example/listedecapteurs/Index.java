package com.example.listedecapteurs;

import static java.lang.Character.getType;

import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Index extends AppCompatActivity {
    private FloatingActionButton myButton;
    private SensorManager sensorManager;
    private List <Sensor> sensors;
    private  String[] displayArray ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //Récupere une instance du service de gestion des capteurs
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        listSensor();

        ListView listView = findViewById(R.id.sensorListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayArray);
        listView.setAdapter(adapter);


/******************************* Button *********************/
        myButton = findViewById(R.id.my_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Index.this, ChoseApplication.class);
                startActivity(intent);
            }
        });
    }
    private void listSensor() {
        //Lister les capteurs :
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        displayArray = new String[sensors.size()];
        int i=0;
        for (Sensor sensor : sensors) {
            displayArray[i]="";
            displayArray[i] += "Name: " + sensor.getName() + "\r\n"+"\tType: " + getType(sensor.getType()) + "\r\n"+"\tVersion: " + sensor.getVersion();
            i++;
        }

}


}
