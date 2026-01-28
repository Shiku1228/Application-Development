package com.example.geoquiz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView question_text_view;
    private Button true_button;
    private Button false_button;


    private Question[] questionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, true)
    };

    private int currentIndex = 0;
    private Button next_button;

    private boolean [] answered;
    private int answeredCount;



    //+++++++++++++++++++++++++++++++++++++++++++++

    private static final String KEY_INDEX = "currentQuestionIndex";
    private static final String KEY_ANSWERED = "answered";

    private int score = 0;

    private static final String KEY_SCORE = "score";
    private TextView score_text_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            score = savedInstanceState.getInt(KEY_SCORE, 0);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // CONNECT XML VIEWS
        question_text_view = findViewById(R.id.question_text_view);
        true_button = findViewById(R.id.true_button);
        false_button = findViewById(R.id.false_button);

        // TRUE BUTTON
        true_button.setOnClickListener(v -> checkAnswer(true));

        // FALSE BUTTON
        false_button.setOnClickListener(v -> checkAnswer(false));

        updateQuestion();

        if (answered[currentIndex]) {
            true_button.setEnabled(false);
            false_button.setEnabled(false);
        }

        next_button = findViewById(R.id.next_button);
        next_button.setOnClickListener(v -> {
            if(currentIndex == questionBank.length - 1){
                Toast.makeText(
                        this,
                        "Final Score: " + score + "/" + questionBank.length,
                        Toast.LENGTH_LONG
                ).show();

                score = 0; // reset
            }
            currentIndex = (currentIndex + 1) % questionBank.length;

            true_button.setEnabled(true);
            false_button.setEnabled(true);

            updateQuestion();
            updateScore();
        });

        score_text_view = findViewById(R.id.score_text_view);
        updateScore();

    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_INDEX, currentIndex);
        outState.putBooleanArray(KEY_ANSWERED, answered);
        outState.putInt("answeredCount", answeredCount);
        outState.putInt(KEY_SCORE, score);
    }

    private void updateQuestion(){
        int  questionResId = questionBank[currentIndex].getTextResId();
        question_text_view.setText(questionResId);
        
        true_button.setEnabled(!answered[currentIndex]);
        false_button.setEnabled(!answered[currentIndex]);
    }

    private void checkAnswer(boolean userAnswer) {

        if (answered[currentIndex]) return;

        boolean correctAnswer =
                questionBank[currentIndex].isAnswerTrue();

        if(userAnswer == correctAnswer){
            score++;
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }

        answered[currentIndex] = true;
        answeredCount++;

        //disable buttons after answering.
        true_button.setEnabled(false);
        false_button.setEnabled(false);

        updateScore();

        if(answeredCount == questionBank.length){
            showFinalScore();
        }
    }

    private void updateScore(){
        score_text_view.setText(
          "Score: " + score + " /" + questionBank.length
        );
    }

    private void showFinalScore() {
        int percentage = (int) ((score / (float) questionBank.length) * 100);

        Toast.makeText(
                this,
                "Final Score: " + score + "/" + questionBank.length +
                        " (" + percentage + "%)",
                Toast.LENGTH_LONG
        ).show();
    }
}
