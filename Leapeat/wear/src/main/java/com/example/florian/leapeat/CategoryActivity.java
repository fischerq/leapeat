package com.example.florian.leapeat;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;

public class CategoryActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });
    }

    public void setEnglish(View view)
    {
        SharedPreferences prefs = getSharedPreferences("Lepeat",MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putInt("Category", 0);
        ed.commit();
    }

    public void setPhysics(View view)
    {
        SharedPreferences prefs = getSharedPreferences("Lepeat",MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putInt("Category", 1);
        ed.commit();
    }
}
