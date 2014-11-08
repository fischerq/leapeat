package com.example.florian.leapeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;
import android.content.SharedPreferences;

public class SettingsActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });
    }

    public void setSee(View view)
    {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("AnswerMode", "See");
    }

    public void setAnswer(View view)
    {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("AnswerMode", "Answer");
    }

    public void setCheck(View view)
    {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("AnswerMode", "Check");
    }
}
