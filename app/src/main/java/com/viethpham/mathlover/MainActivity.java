package com.viethpham.mathlover;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    ConstraintLayout gameLayout;
    Button startButton, button0, button1, button2, button3, playButton;
    TextView sumTextView, resultTextView, pointsTextView, timerTextView;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAns;
    int score = 0;
    int numberOfQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLayout = findViewById(R.id.gameLayout);
        startButton = findViewById(R.id.startButton);
        playButton = findViewById(R.id.playButton);
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        timerTextView = findViewById(R.id.timerTextView);
        button0 = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
    }

    public void playGame(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(ConstraintLayout.VISIBLE);

        playButton.setVisibility(View.INVISIBLE);
        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");

        generateQuestion();
        new CountDownTimer(30100, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish()
            {
                playButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();
    }

    public void chooseAnswer(View view)
    {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAns)))
        {
            score++;
            resultTextView.setText("Correct!");
        }
        else
        {
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void generateQuestion()
    {
        answers.clear();
        Random rand = new Random();

        int a = rand.nextInt(21); // random number between 0 and 20
        int b = rand.nextInt(21); // random number between 0 and 20

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAns = rand.nextInt(4); // between 0 and 3

        int correctAns = a + b;
        int incorrectAns;
        for (int i = 0; i < 4; i++)
        {
            if (i == locationOfCorrectAns)
            {
                answers.add(correctAns);
            }
            else
            {
                incorrectAns = rand.nextInt(41);
                while (incorrectAns == correctAns)
                {
                    incorrectAns = rand.nextInt(41);
                }
                answers.add(incorrectAns);
            }
        }

        button0.setText(answers.get(0).toString());
        button1.setText(answers.get(1).toString());
        button2.setText(answers.get(2).toString());
        button3.setText(answers.get(3).toString());

    }
}
