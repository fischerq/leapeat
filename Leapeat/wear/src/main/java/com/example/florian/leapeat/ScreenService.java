package com.example.florian.leapeat;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class ScreenService extends Service {
    private BroadcastReceiver myReceiver;
    public ScreenService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        // register receiver that handles screen on and screen off logic
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        myReceiver = new ScreenReceiver(this);
        registerReceiver(myReceiver, filter);
        Log.i("Service", "Started!");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        boolean screenOn = intent.getBooleanExtra("screen_state", false);
        if (!screenOn) {
            // your code
        } else {
            // your code
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("Service", "Bind??");
        return null;
    }

    public void onDestroy(){
        unregisterReceiver(myReceiver);
        Log.i("Service Destroyed","Destroyed" );
        super.onDestroy();
    }

}
