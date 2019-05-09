package com.example.trainyourmath;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> ansArray;
    int locationOfAns;
    Button startBtn,button0, button1,button2,button3, playAgainBtn;
    Random random;
    TextView sumTV,resultTV, scoreTV, timerTV;
    int a,b, ans, wrongAns, score, totalQ;
    String aS,bS;
    ConstraintLayout gameLayout;

    public void start(View view){
        startBtn.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        refreshGame();

    }

    public void chooseAns(View view){
        if (view.getTag().toString().equals(Integer.toString(locationOfAns))){
            resultTV.setText("Correct!");
            generateQuestions();
            generateAnswers();
            setButtonText();
            score++;
        }else {
            resultTV.setText("Wrong!");
            generateQuestions();
            generateAnswers();
            setButtonText();
        }
        totalQ++;
        scoreTV.setText(Integer.toString(score)+"/"+Integer.toString(totalQ));
    }

    public void playAgain(View view){
        totalQ=0;
        score = 0;
        timerTV.setText("30s");
        scoreTV.setText("0/0");
        resultTV.setText("");
        playAgainBtn.setVisibility(View.INVISIBLE);
        refreshGame();
    }

    public void refreshGame(){
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished){
                timerTV.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish(){
                playAgainBtn.setVisibility(View.VISIBLE);
                timerTV.setText("0s");
                resultTV.setText("Your score is "+Integer.toString(score)+"/"+Integer.toString(totalQ));
                button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);

            }

        }.start();

        generateQuestions();
        generateAnswers();
        setButtonText();
        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
    }

    public void generateQuestions(){
        random = new Random();
        a = random.nextInt(21);
        b = random.nextInt(21);
        ans = a+b;
        aS = Integer.toString(a); bS = Integer.toString(b);

        sumTV = findViewById(R.id.sumTV);
        sumTV.setText(aS+" + "+bS);
    }

    public void generateAnswers(){
        ansArray = new ArrayList<>();
        locationOfAns = random.nextInt(4);
        for (int i=0; i<4;i++){
            if (i==locationOfAns){
                ansArray.add(ans);
            }else {
                wrongAns = random.nextInt(41);
                while (wrongAns==ans){
                    wrongAns = random.nextInt(41);
                }
                ansArray.add(wrongAns);
            }
        }
    }

    public void setButtonText(){
        button0.setText(Integer.toString(ansArray.get(0)));
        button1.setText(Integer.toString(ansArray.get(1)));
        button2.setText(Integer.toString(ansArray.get(2)));
        button3.setText(Integer.toString(ansArray.get(3)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV = findViewById(R.id.resultTV);
        scoreTV = findViewById(R.id.scoreTV);
        timerTV = findViewById(R.id.timerTV);
        gameLayout = findViewById(R.id.gameLayout);

        startBtn = findViewById(R.id.startBtn);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainBtn = findViewById(R.id.playAgainBtn);
        //test
    }
}

