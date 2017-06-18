package com.dartzalabs.csun;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

public class UpdateScreenBrightness extends Activity {


    private static final int DELAYED_MESSAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what == DELAYED_MESSAGE) {
                    UpdateScreenBrightness.this.finish();

                }
                return false;
            }

        });

        Intent brightnessIntent = this.getIntent();
        float brightness = brightnessIntent.getFloatExtra("brightness value", 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = brightness;
        getWindow().setAttributes(lp);

        Message message = handler.obtainMessage(DELAYED_MESSAGE);
        //this next line is very important, you need to finish your activity with slight delay
        handler.sendMessageDelayed(message,1);
    }

}