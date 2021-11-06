package com.example.topquiz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquiz.R;

import java.util.Arrays;

import model.Question;
import model.QuestionBank;

public class GameActivity extends AppCompatActivity implements View.OnClickListener
{
    private TextView mGameTextView;
    private Button mGameButton1;
    private Button mGameButton2;
    private Button mGameButton3;
    private Button mGameButton4;

    private int mRemainingQuestionCount;
    private int mScore;
    private Question mCurrentQuestion;

    private final QuestionBank mQuestionBank = generateQuestionBank();

    private QuestionBank generateQuestionBank()
    {
        Question question1 = new Question(
                "Who is the creator of Android?",
                Arrays.asList(
                        "Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"
                ),
                0
        );

        Question question2 = new Question(
                "When did the first man land on the moon?",
                Arrays.asList(
                        "1958",
                        "1962",
                        "1967",
                        "1969"
                ),
                3
        );

        Question question3 = new Question(
                "What is the house number of The Simpsons?",
                Arrays.asList(
                        "42",
                        "101",
                        "666",
                        "742"
                ),
                3
        );

        Question question4 = new Question(
                "Qui est le meilleur joueur de tous les temps ?",
                Arrays.asList(
                        "Diego Maradona",
                        "Lionel Messi",
                        "Christiano RONALDO",
                        "PELE"
                ),
                2
        );
        Question question5 = new Question(
                "Devinez l'âge du développeur de ce jeu !",
                Arrays.asList(
                        "20",
                        "30",
                        "19",
                        "17"
                ),
                2
        );

        return new QuestionBank(Arrays.asList(question1, question2, question3,question4,question5));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        mRemainingQuestionCount = 6;
        mScore = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGameTextView = findViewById(R.id.game_activity_textview_question);
        mGameButton1 = findViewById(R.id.game_activity_button_1);
        mGameButton2 = findViewById(R.id.game_activity_button_2);
        mGameButton3 = findViewById(R.id.game_activity_button_3);
        mGameButton4 = findViewById(R.id.game_activity_button_4);

        mGameButton1.setOnClickListener(this);
        mGameButton2.setOnClickListener(this);
        mGameButton3.setOnClickListener(this);
        mGameButton4.setOnClickListener(this);

        mRemainingQuestionCount--;

        if (mRemainingQuestionCount > 0)
        {
            mCurrentQuestion = mQuestionBank.getCurrentQuestion();
            displayQuestion(mCurrentQuestion);
        }
        else {
            // No question left, end the game
        }

    }

    private void displayQuestion(final Question question)
    {
        mGameTextView.setText(question.getQuestion());
        mGameButton1.setText(question.getChoiceList().get(0));
        mGameButton2.setText(question.getChoiceList().get(1));
        mGameButton3.setText(question.getChoiceList().get(2));
        mGameButton4.setText(question.getChoiceList().get(3));
        // Set the text for the question text view and the four buttons
    }

    @Override
    public void onClick(View v)
    {
        int index;
        if (v == mGameButton1)
        {
            index = 0;
        }
        else if (v == mGameButton2)
        {
            index = 1;
        }
        else if (v == mGameButton3)
        {
            index = 2;
        }
        else if (v == mGameButton4)
        {
            index = 3;
        }
        else {
            throw new IllegalStateException("Unknown clicked view : " + v);
        }

        if(index == mQuestionBank.getCurrentQuestion().getAnswerIndex())
        {
            mScore++;
            Toast.makeText(this, "Réponse Correct!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Réponse incorrect!", Toast.LENGTH_SHORT).show();
        }

        mRemainingQuestionCount--;
        if (mRemainingQuestionCount > 0)
        {
            mCurrentQuestion = mQuestionBank.getNextQuestion();
            displayQuestion(mCurrentQuestion);
        }
        else {
            // No question left, end the game
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Well done!")
                    .setMessage("Your score is " + mScore)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .create()
                    .show();
        }

    }

}