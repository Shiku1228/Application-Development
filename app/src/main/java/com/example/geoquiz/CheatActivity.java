package com.example.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN =
            "com.example.geoquiz.answer_shown";

    public static final String EXTRA_QUESTION_INDEX =
            "com.example.geoquiz.question_index";

    private static final String KEY_ANSWER_SHOWN = "answerShown";

    private boolean answerShown;
    private TextView answerTextView;
    private Button showAnswerButton;
    private boolean answerIsTrue;

    private int questionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        // 1️⃣ connect views FIRST
        answerTextView = findViewById(R.id.answer_text_view);
        showAnswerButton = findViewById(R.id.show_answer_button);

        // 2️⃣ get intent data
        questionIndex = getIntent().getIntExtra(EXTRA_QUESTION_INDEX, -1);

        answerIsTrue = getIntent().getBooleanExtra(
                EXTRA_ANSWER_IS_TRUE, false
        );

        // default result
        setAnswerShownResult(false);

        // 3️⃣ restore state AFTER views exist
        if (savedInstanceState != null) {
            answerShown = savedInstanceState.getBoolean(KEY_ANSWER_SHOWN, false);

            if (answerShown) {
                showAnswer();
            }
        }

        // 4️⃣ button behavior
        showAnswerButton.setOnClickListener(v -> {
            answerShown = true;
            showAnswer();
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_ANSWER_SHOWN, answerShown);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        data.putExtra(EXTRA_QUESTION_INDEX, questionIndex);
        setResult(RESULT_OK, data);
    }


    private void showAnswer(){
        if(answerIsTrue){
            answerTextView.setText(R.string.true_button);
        }else{
            answerTextView.setText(R.string.false_button);
        }

        answerTextView.setVisibility(TextView.VISIBLE);
        setAnswerShownResult(true);
    }

}

