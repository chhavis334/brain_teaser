package com.example.brainteaser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
Button go,button0,button1,button2,button3,playagain;
TextView sum,correct,scoreTextView,timer;
ArrayList<Integer> answers  = new ArrayList<Integer>();
ConstraintLayout l1,l2;
    int score = 0 , numberOfQuestions = 0;
    int locationOfCorrectAnswer;
    public void chooseAnswer(View view) {

        if( Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString()))
        { correct = findViewById(R.id.correct);
            correct.setText("CORRECT!");
            score++;
        }
        else{
            correct.setText("INCORRECT!");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));
        newQuestion();


    }
    public  void newQuestion(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        sum.setText(Integer.toString(a)+ " + " + Integer.toString(b));
        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer)
            {
                answers.add(a+b);
            }
            else{
                int wronganswer = random.nextInt(41);
                while(wronganswer == a+b){
                    wronganswer = random.nextInt(41);

                }answers.add(wronganswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l1 = findViewById(R.id.layout1);
        l2 = findViewById(R.id.layout2);
        go = findViewById(R.id.go);
        sum = findViewById(R.id.sum);
        button0 = findViewById(R.id.button);
        button1 = findViewById(R.id.bt1);
        button2 = findViewById(R.id.bt2);
        button3 = findViewById(R.id.bt3);
        scoreTextView = findViewById(R.id.score);
        timer = findViewById(R.id.timer);
        playagain = findViewById(R.id.playagain);
        l2.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.score));

    }

    public void start(View view) {
        go.setVisibility(View.INVISIBLE);
        l2.setVisibility(View.VISIBLE);

    }


    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        scoreTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));
        newQuestion();
        playagain.setVisibility(View.INVISIBLE);
        correct.setText("");
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l/1000 + "s"));
            }

            @Override
            public void onFinish() {
                correct.setText("FINISH");
                playagain.setVisibility(View.VISIBLE);

            }
        }.start();
    }
}