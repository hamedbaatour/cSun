package com.dartzalabs.csun;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;

import org.json.JSONObject;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Automatic session tracking
        Branch.getAutoInstance(getApplicationContext(), false);

        //App Intro
        //  Declare a new thread to do a preference check
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

                //  If the activity has never started before...
                if (isFirstStart) {

                    //  Launch app intro
                    Intent i = new Intent(MainActivity.this, GettingStarted.class);
                    startActivity(i);

                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();

                    //  Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firstStart", false);

                    //  Apply changes
                    e.apply();
                }
            }
        });

        // Start the thread
        t.start();


        //Activate Button
        Button clickButton = (Button) findViewById(R.id.button1);
        clickButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (!android.provider.Settings.System.canWrite(MainActivity.this)) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        startActivity(intent);

                    } else {

                        SuperToast superToast = new SuperToast(getApplicationContext(), Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN));
                        superToast.setDuration(SuperToast.Duration.SHORT);
                        superToast.setText("cSUN! Activated");
                        superToast.setIcon(SuperToast.Icon.Dark.INFO, SuperToast.IconPosition.LEFT);
                        superToast.show();
                    }


                } else {
                    Intent i = new Intent(MainActivity.this, cSunService.class);
                    startService(i);

                    SuperToast superToast = new SuperToast(getApplicationContext(), Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN));
                    superToast.setDuration(SuperToast.Duration.SHORT);
                    superToast.setText("cSUN! Activated");
                    superToast.setIcon(SuperToast.Icon.Dark.INFO, SuperToast.IconPosition.LEFT);
                    superToast.show();
                }

            }

        });


        //Deactivate Button
        Button clickButton2 = (Button) findViewById(R.id.button2);
        clickButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                stopService(new Intent(MainActivity.this, cSunService.class));

                SuperToast superToast = new SuperToast(getApplicationContext(), Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN));
                superToast.setDuration(SuperToast.Duration.SHORT);
                superToast.setText("cSUN! Deactivated");
                superToast.setIcon(SuperToast.Icon.Dark.INFO, SuperToast.IconPosition.LEFT);
                superToast.show();
            }
        });


        //Deactivate Button
        Button clickButton3 = (Button) findViewById(R.id.p50);
        clickButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                SharedPreferences sp = getSharedPreferences("prefs", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("brightness", 127);
                editor.apply();

                //Super Toast
                SuperToast superToast = new SuperToast(getApplicationContext(), Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN));
                superToast.setDuration(200);
                superToast.setText("Profile: 50%");
                superToast.setIcon(SuperToast.Icon.Dark.INFO, SuperToast.IconPosition.LEFT);
                superToast.show();


            }
        });


        //Deactivate Button
        Button clickButton4 = (Button) findViewById(R.id.p75);
        clickButton4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                SharedPreferences sp = getSharedPreferences("prefs", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("brightness", 190);
                editor.apply();

                //Super Toast
                SuperToast superToast = new SuperToast(getApplicationContext(), Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN));
                superToast.setDuration(200);
                superToast.setText("Profile: 75%");
                superToast.setIcon(SuperToast.Icon.Dark.INFO, SuperToast.IconPosition.LEFT);
                superToast.show();

            }
        });


        //Deactivate Button
        Button clickButton5 = (Button) findViewById(R.id.p100);
        clickButton5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                SharedPreferences sp = getSharedPreferences("prefs", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("brightness", 255);
                editor.apply();

                //Super Toast
                SuperToast superToast = new SuperToast(getApplicationContext(), Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN));
                superToast.setDuration(200);
                superToast.setText("Profile: 100%");
                superToast.setIcon(SuperToast.Icon.Dark.INFO, SuperToast.IconPosition.LEFT);
                superToast.show();

            }
        });


        //profiles : cloudy sunny ...

        ImageView img = (ImageView) findViewById(R.id.cloudy);
        img.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                SharedPreferences lux = getSharedPreferences("lux_prefs", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = lux.edit();
                editor.putInt("lux_value_set_user", 3000);
                editor.apply();

                //Super Toast
                SuperToast superToast = new SuperToast(getApplicationContext(), Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN));
                superToast.setDuration(200);
                superToast.setText("Profile: Cloudy");
                superToast.setIcon(SuperToast.Icon.Dark.INFO, SuperToast.IconPosition.LEFT);
                superToast.show();

            }

        });


        ImageView img2 = (ImageView) findViewById(R.id.shady);
        img2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                SharedPreferences lux = getSharedPreferences("lux_prefs", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = lux.edit();
                editor.putInt("lux_value_set_user", 6000);
                editor.apply();

                //Super Toast
                SuperToast superToast = new SuperToast(getApplicationContext(), Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN));
                superToast.setDuration(200);
                superToast.setText("Profile: Shady");
                superToast.setIcon(SuperToast.Icon.Dark.INFO, SuperToast.IconPosition.LEFT);
                superToast.show();

            }

        });


        ImageView img3 = (ImageView) findViewById(R.id.sunny);
        img3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                SharedPreferences lux = getSharedPreferences("lux_prefs", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = lux.edit();
                editor.putInt("lux_value_set_user", 9000);
                editor.apply();

                //Super Toast
                SuperToast superToast = new SuperToast(getApplicationContext(), Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN));
                superToast.setDuration(200);
                superToast.setText("Profile: Sunny");
                superToast.setIcon(SuperToast.Icon.Dark.INFO, SuperToast.IconPosition.LEFT);
                superToast.show();

            }

        });



        mCheckBox = (CheckBox) findViewById(R.id.start_on_boot);
        // Set the initial state of the check box based on saved value
        mCheckBox.setChecked(isCheckedSettingEnabled());

        mCheckBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {

                    ComponentName receiver = new ComponentName(getApplicationContext(), StartServiceOnBoot.class);
                    PackageManager pm = getApplicationContext().getPackageManager();

                    pm.setComponentEnabledSetting(receiver,
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                            PackageManager.DONT_KILL_APP);

                    //Super Toast
                    SuperToast superToast = new SuperToast(getApplicationContext(), Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN));
                    superToast.setDuration(200);
                    superToast.setText("Start on boot: ON");
                    superToast.setIcon(SuperToast.Icon.Dark.INFO, SuperToast.IconPosition.LEFT);
                    superToast.show();

                } else {
                    ComponentName receiver = new ComponentName(getApplicationContext(), StartServiceOnBoot.class);
                    PackageManager pm = getApplicationContext().getPackageManager();

                    pm.setComponentEnabledSetting(receiver,
                            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                            PackageManager.DONT_KILL_APP);

                    //Super Toast
                    SuperToast superToast = new SuperToast(getApplicationContext(), Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN));
                    superToast.setDuration(200);
                    superToast.setText("Start on boot: OFF");
                    superToast.setIcon(SuperToast.Icon.Dark.INFO, SuperToast.IconPosition.LEFT);
                    superToast.show();
                }


            }
        });




    }

    //start on boot check box
    private static final String SETTING_CHECK_BOX = "checkbox_setting";

    private CheckBox mCheckBox;

    @Override
    public void onPause() {
        super.onPause();

        // Persist the setting. Could also do this with an OnCheckedChangeListener.
        setCheckedSettingEnabled(mCheckBox.isChecked());
    }

    /**
     * Returns true if the setting has been saved as enabled,
     * false by default
     */
    private boolean isCheckedSettingEnabled() {
        return PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean(SETTING_CHECK_BOX, false);
    }

    /**
     * Persists the new state of the setting
     *
     * @param enabled the new state for the setting
     */
    private void setCheckedSettingEnabled(boolean enabled) {
        PreferenceManager.getDefaultSharedPreferences(this)
                .edit()
                .putBoolean(SETTING_CHECK_BOX, enabled)
                .apply();
    }





    //branch
        @Override
     public void onStart() {
        super.onStart();

        Branch branch = Branch.getInstance();
        branch.initSession(new Branch.BranchReferralInitListener() {
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                if (error == null) {
                    // params are the deep linked params associated with the link that the user clicked before showing up
                    Log.i("BranchConfigTest", "deep link data: " + referringParams.toString());
                }
            }
        }, this.getIntent().getData(), this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }


}



