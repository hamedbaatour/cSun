package com.dartzalabs.csun;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;


public class cSunService extends Service implements SensorEventListener {

    float brightness;
    int BrightnessMode;
    boolean HasLightSensor;



    protected void getBrightMode(ContentResolver resolver) {
        try {
            BrightnessMode = Settings.System.getInt(resolver, Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Exception e) {
            Log.d("tag", e.toString());
        }
    }


    public SensorManager sensorManager = null;
    public Sensor sensor = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this, sensor,
                SensorManager.SENSOR_DELAY_FASTEST);


        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {

        // First check if device has a light sensor or not
        HasLightSensor = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_SENSOR_LIGHT);

        if (!HasLightSensor) {
            // device doesn't support light sensor
            // Show alert message and close the application
            AlertDialog alert = new AlertDialog.Builder(cSunService.this)
                    .create();
            alert.setTitle("Error");
            alert.setMessage("Sorry, your device doesn't support SUN! as it has no light sensor");
        } else
            getBrightMode(getContentResolver());

        return null;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // do nothing
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        // Check Current Screen Brightness
        try {
            float curBrightnessValue = android.provider.Settings.System.getInt(
                    getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS);



            SharedPreferences lux = getSharedPreferences("lux_prefs", Activity.MODE_PRIVATE);
            int lux_value = lux.getInt("lux_value_set_user", 3000);

            if ((event.values[0] > lux_value && BrightnessMode == 0 && curBrightnessValue < 80)) {

                // This is important. In the next line 'brightness'
                // should be a float number between 0.0 and 1.0
                int brightnessInt = (int) (brightness*255);

                SharedPreferences sp = getSharedPreferences("prefs", Activity.MODE_PRIVATE);
                int brightness_level = sp.getInt("brightness", 255);

                //Check that the brightness is not 0, which would effectively
                //switch off the screen, and we don't want that:
                if(brightnessInt<1) {brightnessInt=brightness_level;}

                // Set system wide brightness setting.
                Settings.System.putInt(getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brightnessInt);

                // Apply brightness by creating a dummy activity
                Intent intent = new Intent(getBaseContext(), UpdateScreenBrightness.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("brightness value", brightness);
                getApplication().startActivity(intent);


            }
        }
        catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

    }


}



