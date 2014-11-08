package com.example.florian.leapeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MotionEventCompat;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class QuizActivity extends FragmentActivity {

    //private TextView mTextView;
    public final static String EXTRA_MESSAGE = "com.example.florian.leapeat.MESSAGE";
    public static Random generator = new Random();
    public static String[][] Vocabulary = new String[3][2];
    static {
        Vocabulary[0][0] = "to learn";
        Vocabulary[0][1] = "lernen";
        Vocabulary[1][0] = "to drive";
        Vocabulary[1][1] = "fahren";
        Vocabulary[2][0] = "skill";
        Vocabulary[2][1] = "Fähigkeit";
    }
    public int index = 0;

    public int AnswerRight = 0;
    public int AnswerFalse = 0;

    private int mode = 1;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            index = extras.getInt("QUIZ_ID");
            if (index < 0) {
                index = generator.nextInt(Vocabulary.length);
            }
            mode = extras.getInt("MODE");
            Log.i("Quiz", "M: " + mode + " i: " + index);
        }
        else
        {
            Log.i("Quiz", "Bad Intent");
        }


        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
               // mTextView = (TextView) stub.findViewById(R.id.text);
                TextView vocabularyText = (TextView) findViewById(R.id.vocabText);
                vocabularyText.setText(Vocabulary[index][0]);
            }
        });
    }

    public void ActionButtonYes(View view)
    {
        setContentView(R.layout.activity_show_answer_);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                TextView View = (TextView) findViewById(R.id.answer1);
                View.setText(Vocabulary[index][1]);

                AnswerRight++;
                index++;
                if (index >= 3)
                    index = 0;
            }
        });
    }

    public void ActionButtonNo(View view)
    {
        setContentView(R.layout.activity_show_answer_);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                TextView View = (TextView) findViewById(R.id.answer1);
                View.setText(Vocabulary[index][1]);

                AnswerFalse++;
                index++;
                if(index >=3)
                    index = 0;
            }
        });
    }


    //GestureDetector.SimpleOnGestureListener()
    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                Log.d(DEBUG_TAG, "Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE) :
                Log.d(DEBUG_TAG,"Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP) :
                Log.d(DEBUG_TAG,"Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d(DEBUG_TAG,"Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d(DEBUG_TAG,"Movement occurred outside bounds of current screen element");
                return true;
            default :
                Log.d(DEBUG_TAG,"Action Default for Touch Event");
                return super.onTouchEvent(event);
        }
    }

}
