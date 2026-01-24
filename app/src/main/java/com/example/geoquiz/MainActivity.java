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

    private TextView questionTextView;
    private Button trueButton;
    private Button falseButton;

    private boolean correctAnswer = true; // Canberra is capital of Australia

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
        questionTextView = findViewById(R.id.question_text_view);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);

        // TRUE BUTTON
        trueButton.setOnClickListener(v -> checkAnswer(true));

        // FALSE BUTTON
        falseButton.setOnClickListener(v -> checkAnswer(false));
    }

    private void checkAnswer(boolean userAnswer) {
        int toastMessage;

        if (userAnswer == correctAnswer) {
            toastMessage = R.string.correct_toast;
        } else {
            toastMessage = R.string.incorrect_toast;
        }

        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }
}
