package com.example.geoquiz;

public class Question {

    private int textResId;
    private boolean answerTrue;

    public Question(int textResId, boolean answerTrue){
        this.textResId = textResId;
        this.answerTrue = answerTrue;
    }

    public int getTextResId(){
         return textResId;
    }

    public boolean isAnswerTrue(){
        return answerTrue;
    }
}
