package com.example.florian.leapeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.content.SharedPreferences;

public class SettingsActivity extends Activity {

    private TextView mTextView;
    private CheckBox CheckSee;
    private CheckBox CheckAnswer;
    private CheckBox CheckCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                //mTextView = (TextView) stub.findViewById(R.id.text);
                CheckSee = (CheckBox) findViewById(R.id.checkBox_SeeMode);
                CheckAnswer = (CheckBox) findViewById(R.id.checkBox_AnswerMode);
                CheckCheck = (CheckBox) findViewById(R.id.checkBox_CheckMode);

                String answer_mode = getSharedPreferences("Lepeat",MODE_PRIVATE).getString("AnswerMode", "Answer");

                if(answer_mode.equals("Answer"))                {
                    CheckAnswer.setChecked(true);CheckCheck.setChecked(false); CheckSee.setChecked(false);
                }
                else if(answer_mode.equals("See")){
                    CheckAnswer.setChecked(false);CheckCheck.setChecked(false); CheckSee.setChecked(true);
                }
                else if(answer_mode.equals("Check")){
                    CheckAnswer.setChecked(true);CheckCheck.setChecked(false); CheckSee.setChecked(false);
                }
            }
        });
    }

    public void setSee(View view)
    {
        SharedPreferences prefs = getSharedPreferences("Lepeat", MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("AnswerMode", "See");
        ed.commit();
        CheckAnswer.setChecked(false);CheckCheck.setChecked(false); CheckSee.setChecked(true);
    }

    public void setAnswer(View view)
    {
        SharedPreferences prefs = getSharedPreferences("Lepeat", MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("AnswerMode", "Answer");
        ed.commit();
        CheckAnswer.setChecked(true);CheckCheck.setChecked(false); CheckSee.setChecked(false);
    }

    public void setCheck(View view)
    {
        SharedPreferences prefs = getSharedPreferences("Lepeat", MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("AnswerMode", "Check");
        ed.commit();
        CheckAnswer.setChecked(false);CheckCheck.setChecked(true); CheckSee.setChecked(false);
    }
}
