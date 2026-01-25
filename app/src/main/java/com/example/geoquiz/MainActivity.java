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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

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

        next_button = findViewById(R.id.next_button);
        next_button.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % questionBank.length;
            updateQuestion();
        });
    }

    private void updateQuestion(){
        int  questionResId = questionBank[currentIndex].getTextResId();
        question_text_view.setText(questionResId);
    }

    private void checkAnswer(boolean userAnswer) {
        boolean correctAnswer =
                questionBank[currentIndex].isAnswerTrue();

        int toastMessage = (userAnswer == correctAnswer)
                ? R.string.correct_toast
                : R.string.incorrect_toast;

        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }


}
