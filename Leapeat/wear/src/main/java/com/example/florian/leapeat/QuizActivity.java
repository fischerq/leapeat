package com.example.florian.leapeat;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MotionEventCompat;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import java.util.Random;

public class QuizActivity extends FragmentActivity {

    //private TextView mTextView;
    public static QuizActivity the_activity = null;
    public final static String EXTRA_MESSAGE = "com.example.florian.leapeat.MESSAGE";
    public static Random generator = new Random();
    public static String[][][] Vocabulary = new String[2][3][2];
    static {
        Vocabulary[0][0][0] = "to learn";
        Vocabulary[0][0][1] = "lernen";
        Vocabulary[0][1][0] = "to drive";
        Vocabulary[0][1][1] = "fahren";
        Vocabulary[0][2][0] = "skill";
        Vocabulary[0][2][1] = "Fähigkeit";


        Vocabulary[1][0][0] = "force";
        Vocabulary[1][0][1] = "mass * acceleration";
        Vocabulary[1][1][0] = "kinetic energy";
        Vocabulary[1][1][1] = "1/2 * mass * (speed)^2";
        Vocabulary[1][2][0] = "area of a disk";
        Vocabulary[1][2][1] = "A =  pi * r^2";
    }

    public int AnswerRight = 0;
    public int AnswerFalse = 0;

    private boolean answer_correct;

    private int index;
    private int mode = 1;
    private String answer_mode;
    private int category;
    public TextView vocabularyText;
    public TextView answerText;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        the_activity = this;

        answer_mode = getSharedPreferences("Lepeat",MODE_PRIVATE).getString("AnswerMode", "Answer");
        category = getSharedPreferences("Lepeat", MODE_PRIVATE).getInt("Category", 0);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            index = extras.getInt("QUIZ_ID");
            if (index < 0) {
                index = generator.nextInt(Vocabulary.length);
            }
            mode = extras.getInt("MODE");
            Log.i("Quiz", "M: " + mode + " i: " + index+" ans: "+answer_mode+" cat "+category);
        }
        else
        {
            Log.i("Quiz", "Bad Intent");
        }

        startQuestion();
    }

    public void startQuestion()
    {
        if(answer_mode.equals("Answer")) {
            setContentView(R.layout.activity_quiz);

            final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
            stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
                @Override
                public void onLayoutInflated(WatchViewStub stub) {
                    // mTextView = (TextView) stub.findViewById(R.id.text);
                    vocabularyText = (TextView) findViewById(R.id.vocabText);
                    int category = getSharedPreferences("Lepeat", MODE_PRIVATE).getInt("Category", 0);
                    vocabularyText.setText(Vocabulary[category][index][0]);
                }
            });
        }
        else if(answer_mode.equals("See")){
            setContentView(R.layout.activity_see_stub);
            Log.i("quiz","start see");
            final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
            stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
                @Override
                public void onLayoutInflated(WatchViewStub stub) {
                    // mTextView = (TextView) stub.findViewById(R.id.text);
                    vocabularyText = (TextView) findViewById(R.id.vocabText);
                    answerText = (TextView) findViewById(R.id.answerText);
                    int category = getSharedPreferences("Lepeat", MODE_PRIVATE).getInt("Category", 0);
                    vocabularyText.setText(Vocabulary[category][index][0]);
                    answerText.setText(Vocabulary[category][index][1]);
                }
            });

            stub.setOnTouchListener(new android.view.View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {

                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        Log.d("DEBUG_TAG", "Action was DOWN");
                        return true;
                    //case (MotionEvent.ACTION_MOVE) :
                    //   Log.d("DEBUG_TAG","Action was MOVE");
                    //   return true;
                    case (MotionEvent.ACTION_UP):
                        Log.d("DEBUG_TAG", "Action was UP");

                        if(mode > 0)
                        {
                            the_activity.finish();
                        }
                        else {
                            index++;
                            if(index > 2)
                                index = 0;
                            startQuestion();
                        }
                        return true;
                    case (MotionEvent.ACTION_CANCEL):
                        Log.d("DEBUG_TAG", "Action was CANCEL");
                        return true;
                    case (MotionEvent.ACTION_OUTSIDE):
                        Log.d("DEBUG_TAG", "Movement occurred outside bounds of current screen element");
                        return true;
                    default:
                        Log.d("DEBUG_TAG", "Action Default for Touch Event");
                        return true;
                }

                }
            });
        }
        else if(answer_mode.equals("Check")){
            setContentView(R.layout.activity_check_stub);
            Log.i("quiz","start check");
            final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
            stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
                @Override
                public void onLayoutInflated(WatchViewStub stub) {
                    // mTextView = (TextView) stub.findViewById(R.id.text);
                    vocabularyText = (TextView) findViewById(R.id.vocabText);
                    int category = getSharedPreferences("Lepeat", MODE_PRIVATE).getInt("Category", 0);
                    vocabularyText.setText(Vocabulary[category][index][0]);
                }
            });
        }
    }

    public void startAnswer(){
        setContentView(R.layout.activity_show_answer_);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                Log.i("Answer",Vocabulary[category][index][1]);
                TextView answer = (TextView) findViewById(R.id.answer1);
                int category = getSharedPreferences("Lepeat", MODE_PRIVATE).getInt("Category", 0);
                answer.setText(Vocabulary[category][index][1]);

                if(answer_correct)
                    AnswerRight++;
                else
                    AnswerFalse++;

                if(answer_mode.equals("Check"))
                {
                    TextView View_resp = (TextView) findViewById(R.id.response);
                    if(answer_correct)
                        View_resp.setText("Correct!");
                    else
                        View_resp.setText("Incorrect");

                }

                index++;
                if (index >= 3)
                    index = 0;
            }
        });
        stub.setOnTouchListener(new android.view.View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        Log.d("DEBUG_TAG", "Action was DOWN");
                        return true;
                    //case (MotionEvent.ACTION_MOVE) :
                    //   Log.d("DEBUG_TAG","Action was MOVE");
                    //   return true;
                    case (MotionEvent.ACTION_UP):
                        Log.d("DEBUG_TAG", "Action was UP");

                        if(mode > 0)
                        {
                            the_activity.finish();
                        }
                        else {
                            startQuestion();
                        }
                        return true;
                    case (MotionEvent.ACTION_CANCEL):
                        Log.d("DEBUG_TAG", "Action was CANCEL");
                        return true;
                    case (MotionEvent.ACTION_OUTSIDE):
                        Log.d("DEBUG_TAG", "Movement occurred outside bounds of current screen element");
                        return true;
                    default:
                        Log.d("DEBUG_TAG", "Action Default for Touch Event");
                        return true;
                }

            }
        });
    }

    public void ActionButtonYes(View view)
    {
        answer_correct = true;
        startAnswer();
    }

    public void ActionButtonNo(View view)
    {
        answer_correct = false;
        startAnswer();
    }

    public void ActionButtonVoice(View view)
    {
        answer_correct = true;
        index++;
        if(index > 2)
            index = 0;
        Log.i("Voicecheck", "checking");
        startAnswer();
    }
    /*@Override
    public boolean onTouchEvent(MotionEvent event) {

        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                // finger touches the screen
                break;

            case MotionEvent.ACTION_MOVE:
                // finger moves on the screen
                break;

            case MotionEvent.ACTION_UP:
                System.out.println("asdfasdfasdf");
                break;
        }

        return true;
    }*/
/*
    GestureDetector.SimpleOnGestureListener simpleOnGestureListener= new SimpleOnGestureListener()
    {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {

            return super.onSingleTapUp(e);
        }
        GestureDetector gestureDetector
                = new GestureDetector(simpleOnGestureListener);
    };



    //GestureDetector.SimpleOnGestureListener()
    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                Log.d("DEBUG_TAG", "Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE) :
                Log.d("DEBUG_TAG","Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP) :
                Log.d("DEBUG_TAG","Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d("DEBUG_TAG","Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d("DEBUG_TAG","Movement occurred outside bounds of current screen element");
                return true;
            default :
                Log.d("DEBUG_TAG","Action Default for Touch Event");
                return super.onTouchEvent(event);
        }
    }*/

}
