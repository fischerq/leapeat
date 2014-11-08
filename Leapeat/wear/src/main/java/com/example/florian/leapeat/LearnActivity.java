package com.example.florian.leapeat;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

public class LearnActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                CheckBox CheckBoxTime = (CheckBox) findViewById(R.id.checkBox_everyTime);
                CheckBox CheckBoxVoc = (CheckBox) findViewById(R.id.checkBox_VocoClock);

                if(isMyServiceRunning(ScreenService.class))
                {
                    CheckBoxTime.setChecked(true);
                    CheckBoxVoc.setChecked(false);
                }
                else if(false)
                {
                    CheckBoxTime.setChecked(false);
                    CheckBoxVoc.setChecked(true);
                }
                else {
                    CheckBoxTime.setChecked(false);
                    CheckBoxVoc.setChecked(false);
                }
            }
        });


    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            Log.i("Running", serviceClass.getName());
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    public void startQuiz(View view)
    {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("QUIZ_ID", 0);
        intent.putExtra("MODE", 0);
        startActivity(intent);
    }
    public void StartEveryTime(View view)
    {
        CheckBox CheckBoxTime = (CheckBox) findViewById(R.id.checkBox_everyTime);
        CheckBox CheckBoxVoc = (CheckBox) findViewById(R.id.checkBox_VocoClock);


        if (CheckBoxTime.isChecked()) {
            CheckBoxTime.setChecked(false);
            stopService(new Intent(this,ScreenService.class));
        }
        else{
            CheckBoxTime.setChecked(true);
            CheckBoxVoc.setChecked(false);

            Intent service = new Intent(this, ScreenService.class);
            startService(service);
        }

        //Intent intent = new Intent(this, EveryTimeActivity.class);
        //startActivity(intent);
    }
    public void startVocoClock(View view)
    {
        CheckBox CheckBoxVoc = (CheckBox) findViewById(R.id.checkBox_VocoClock);
        CheckBox CheckBoxTime = (CheckBox) findViewById(R.id.checkBox_everyTime);


        if (CheckBoxVoc.isChecked())
            CheckBoxVoc.setChecked(false);

        else{
            CheckBoxVoc.setChecked(true);
            CheckBoxTime.setChecked(false);
        }

        //Intent intent = new Intent(this, VocoClockActivity.class);
        // startActivity(intent);
    }

}
