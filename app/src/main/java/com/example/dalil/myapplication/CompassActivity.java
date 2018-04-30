package com.example.dalil.myapplication;

import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;


//this class is responsible for pointing to a given location (like a compass pointing there instead of to the north)

public class CompassActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "CompassActivity";

    private ImageView image;
    private float currentDegree = 0f;
    private final SensorManager mSensorManager;
    private final Sensor mAccelerometer;
    GeomagneticField geoField;

    //private TextView tvHeading;
    private Location location = new Location("A");
    private Location target = new Location("B");
    private LocationManager locationManager;

    public CompassActivity() {
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        image = findViewById(R.id.imageViewCompass);
       //tvHeading = (TextView) findViewById(R.id.tvHeading);

        location.setLatitude(54.903535);
        location.setLongitude(23.979342);

        target.setLatitude(54.904618);
        target.setLongitude(23.978782);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // get the angle around the z-axis rotated
        float degree = Math.round(sensorEvent.values[0]);
        degree += geoField.getDeclination();

        float bearing = location.bearingTo(target);
        degree = (bearing - degree) * -1;
        degree = normalizeDegree(degree);
        Log.d(TAG, "onSensorChanged: degree = " + degree);

        //tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");
        //tvHeading.setText(String.format("Heading: %s degrees", Float.toString(degree)));

        // create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        // how long the animation will take place
        ra.setDuration(210);

        // set the animation after the end of the reservation status
        ra.setFillAfter(true);

        // Start the animation
        image.startAnimation(ra);
        currentDegree = -degree;
    }

    private float normalizeDegree(float value) {
        if (value >= 0.0f && value <= 180.0f) {
            return value;
        } else {
            return 180 + (180 + value);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
            //don't need anything here
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this); // to stop the listener and save battery
    }
}
