package com.example.florian.leapeat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenReceiver extends BroadcastReceiver {
    // thanks Jason
    public static boolean wasScreenOn = true;

    ScreenService ServiceForScreenOnDetection;

    ScreenReceiver(ScreenService Service)
    {
        ServiceForScreenOnDetection = Service;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.i("B","fdfgsrgr");
            wasScreenOn = false;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.i("C","aabbaab");

            Intent intent_learn = new Intent(context, QuizActivity.class);
            intent_learn.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent_learn);

            wasScreenOn = true;
        }
        else if(intent.getAction().equals("LeapeatStop"))
        {
            ServiceForScreenOnDetection.stopSelf();
            Log.i("C","Stelle, an der service gestoppt werden soll");
        }
    }

}