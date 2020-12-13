package com.example.stepcounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView stepCounter;
    private SensorManager manager;
    private Sensor sensor;
    private boolean sensorPresent;
    int stepCount=0;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            //ask for permission
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        stepCounter = (TextView) findViewById(R.id.counter);
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
        {
            sensor = manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            sensorPresent = true;
        }else
        {
            stepCounter.setText("Sensor not present");
            sensorPresent = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorPresent) {
            stepCount = (int) sensorEvent.values[0];
            stepCounter.setText(String.valueOf(stepCount));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorPresent = false;
/*        if(manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
        {
            manager.unregisterListener(this, sensor);
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
        {
            manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI);
        }
    }
}