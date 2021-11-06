package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class QuestionBank implements Serializable
{
    private final List<Question> mQuestionList;
    private int mNextQuestionIndex = 0;

    public QuestionBank(List<Question> questionList)
    {
        mQuestionList = questionList;
        Collections.shuffle(mQuestionList);
        // Shuffle the question list before storing it
    }

    public Question getCurrentQuestion()
    {

        return mQuestionList.get(mNextQuestionIndex);
    }

    public Question getNextQuestion()
    {
        Collections.shuffle(mQuestionList);
        return getCurrentQuestion();
        // Loop over the questions and return a new one at each call
    }
}
