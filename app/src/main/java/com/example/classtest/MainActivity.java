package com.example.classtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    private Sensor mTemp;
    private Sensor accel, mag, hum;
    private TextView temp, x, y, z, m1, m2, m3, pressure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temp = (TextView) findViewById(R.id.textView);
        x = (TextView) findViewById(R.id.textView2);
        y = (TextView) findViewById(R.id.textView3);
        z = (TextView) findViewById(R.id.textView4);
        m1 = (TextView) findViewById(R.id.textView5);
        m2 = (TextView) findViewById(R.id.textView6);
        m3 = (TextView) findViewById(R.id.textView7);
        pressure = (TextView) findViewById(R.id.textView8);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorManager.registerListener(this, mTemp, SensorManager.SENSOR_DELAY_NORMAL);
        if (mTemp != null) {
            sensorManager.registerListener(this, mTemp, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "Register mLight");
        } else {
            temp.setText(" Ambient Temperature not supported");
        }

        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accel != null) {
            sensorManager.registerListener(this, accel, 1000000);
            Log.d(TAG, "Register Accelerometr");
        } else {
            x.setText(" Accelerometer not supported");
        }

        mag = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (mag != null) {
            sensorManager.registerListener(this, mag, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "Register Magnetometer");
        } else {
            m1.setText(" Magnometer not supported");
        }

        hum = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (hum != null) {
            sensorManager.registerListener(this, hum, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "Register Pressure sensor");
        } else {
            pressure.setText(" Pressure not supported");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;

        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d(TAG, "onSensorChanged: X: " + sensorEvent.values[0]);
            Log.d(TAG, "onSensorChanged: Y: " + sensorEvent.values[1]);
            Log.d(TAG, "onSensorChanged: Z: " + sensorEvent.values[2]);
            x.setText(" x Accel: " + sensorEvent.values[0]);
            y.setText(" y Accel: " + sensorEvent.values[1]);
            z.setText(" z Accel: " + sensorEvent.values[2]);
        }

        else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            Log.d(TAG, "onSensorChanged: M1: " + sensorEvent.values[0]);
            Log.d(TAG, "onSensorChanged: M2: " + sensorEvent.values[1]);
            Log.d(TAG, "onSensorChanged: M3: " + sensorEvent.values[2]);
            m1.setText(" x: " + sensorEvent.values[0]);
            m2.setText(" y: " + sensorEvent.values[1]);
            m3.setText(" z: " + sensorEvent.values[2]);
        }

        else if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            temp.setText(" x: " + sensorEvent.values[0]);
        }
        else if (sensor.getType() == Sensor.TYPE_PRESSURE) {
            pressure.setText(" x: " + sensorEvent.values[0]);
        }

    }

}