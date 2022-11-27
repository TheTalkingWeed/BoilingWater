package com.example.boilingwater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mgr;
    private Sensor temp;
    private TextView text;
    private StringBuilder msg = new StringBuilder(2048);
    private ImageView img;
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        temp = mgr.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        text = (TextView) findViewById(R.id.tempretureLable);
        text.setTextColor(Color.WHITE);
        img = (ImageView) findViewById(R.id.imageView);


    }

    @Override
    protected void onResume() {
        mgr.registerListener(this, temp, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        mgr.unregisterListener(this, temp);
        super.onPause();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) {
        msg.replace(0,msg.length(),"");
        msg.append( event.values[0] + " Celsius" );
        if(event.values[0] >= 100){
            img.setImageResource(R.drawable.boil);
        } else {
            img.setImageResource(R.drawable.not_boil);
        }

        text.setText(msg);
        text.invalidate();
    }
}