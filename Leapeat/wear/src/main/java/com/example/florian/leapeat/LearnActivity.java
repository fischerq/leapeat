package com.example.florian.leapeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
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
            }
        });
    }

    public void startQuiz(View view)
    {
        Intent intent = new Intent(this, QuizActivity.class);
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
