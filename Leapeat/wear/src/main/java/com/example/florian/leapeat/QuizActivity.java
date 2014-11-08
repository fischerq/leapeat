package com.example.florian.leapeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.LayoutInflater;
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
    public int index = 1;

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
        TextView vocabularyText = (TextView) findViewById(R.id.vocabText);
        index ++;
        if(index >=3)
            index = 0;

        vocabularyText.setText(Vocabulary[index][0]);
        AnswerRight++;

        Intent intent = new Intent(this, ShowAnswer_Activity.class);
        intent.putExtra(EXTRA_MESSAGE, Vocabulary[index][1]);
        startActivity(intent);
    }

    public void ActionButtonNo(View view)
    {
        AnswerFalse++;
        //Intent intent = new Intent(this, AnswerActivity.class);
        //intent.putExtra("MESSAGE", Vocabulary[index][1]);
        //startActivity(intent);
    }

}
