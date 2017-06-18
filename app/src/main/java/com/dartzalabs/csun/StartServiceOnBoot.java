package com.dartzalabs.csun;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartServiceOnBoot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startServiceIntent = new Intent(context, cSunService.class);
        context.startService(startServiceIntent);
    }


}

