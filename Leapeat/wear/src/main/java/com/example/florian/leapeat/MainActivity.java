package com.example.florian.leapeat;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Main","Starting");
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });


    }

    public void startLearning(View view)
    {
        Intent intent = new Intent(this, LearnActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra("MESSAGE", message);
        startActivity(intent);
    }

    public void startCategory(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra("MESSAGE", message);
        startActivity(intent);
    }

    public void startSettings(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra("MESSAGE", message);
        startActivity(intent);
    }

    protected void onPause() {
        // when the screen is about to turn off
        if (ScreenReceiver.wasScreenOn) {
            // this is the case when onPause() is called by the system due to a screen state change
            Log.i("A", "SCREEN TURNED OFF");
        } else {
            // this is when onPause() is called when the screen state has not changed
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        // only when screen turns on
        if (!ScreenReceiver.wasScreenOn) {
            // this is when onResume() is called due to a screen state change
            Log.i("A", "SCREEN TURNED ON");
        } else {
            // this is when onResume() is called when the screen state has not changed
        }
        super.onResume();
    }
}
