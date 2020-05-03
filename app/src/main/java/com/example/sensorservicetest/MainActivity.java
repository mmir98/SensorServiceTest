package com.example.sensorservicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView textView;

    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.text_view);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensor =  sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        textView.setText(sensor.getName());

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        double phone_angle = Math.abs(Math.toDegrees(Math.asin(event.values[2] / 9.81)));
        if(phone_angle >= 70 || Double.isNaN(phone_angle)){
            textView.append(phone_angle + "\n");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
