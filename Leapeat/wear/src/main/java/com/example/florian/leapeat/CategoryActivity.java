package com.example.florian.leapeat;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class CategoryActivity extends Activity {

    private TextView mTextView;
    private CheckBox CheckBoxEnglish;
    private CheckBox CheckBoxPhysics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);

                CheckBoxEnglish = (CheckBox) findViewById(R.id.checkBox_English);
                CheckBoxPhysics = (CheckBox) findViewById(R.id.checkBox_Physics);

                int answer_mode = getSharedPreferences("Lepeat",MODE_PRIVATE).getInt("Category", 0);

                if(answer_mode == 0){
                    CheckBoxEnglish.setChecked(true);CheckBoxPhysics.setChecked(false);
                }
                if(answer_mode == 1){
                    CheckBoxEnglish.setChecked(false);CheckBoxPhysics.setChecked(true);
                }

            }
        });
    }

    public void setEnglish(View view)
    {
        SharedPreferences prefs = getSharedPreferences("Lepeat",MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putInt("Category", 0);
        ed.commit();
        CheckBoxEnglish.setChecked(true);CheckBoxPhysics.setChecked(false);
    }

    public void setPhysics(View view)
    {
        SharedPreferences prefs = getSharedPreferences("Lepeat",MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putInt("Category", 1);
        ed.commit();
        CheckBoxEnglish.setChecked(false);CheckBoxPhysics.setChecked(true);
    }
}
