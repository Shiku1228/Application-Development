
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
    private static final String EXTRA_ANSWER_SHOWN =
            "com.example.geoquiz.answer_shown";

    private TextView answerTextView;
    private Button showAnswerButton;
    private boolean answerIsTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answerIsTrue = getIntent().getBooleanExtra(
                EXTRA_ANSWER_IS_TRUE, false
        );

        answerTextView = findViewById(R.id.answer_text_view);
        showAnswerButton = findViewById(R.id.show_answer_button);

        //TODO: receive answers from intent

        showAnswerButton.setOnClickListener(v -> {
            if(answerIsTrue){
                answerTextView.setText(R.string.true_button);
            }else{
                answerTextView.setText(R.string.false_button);
            }

            answerTextView.setVisibility(TextView.VISIBLE);

            //tell main user that cheat is activated
            Intent data = new Intent();
            data.putExtra(EXTRA_ANSWER_SHOWN, true);
            setResult(RESULT_OK, data);
        });
    }
}