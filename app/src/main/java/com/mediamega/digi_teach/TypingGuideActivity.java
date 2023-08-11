package com.mediamega.digi_teach;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class TypingGuideActivity extends AppCompatActivity {

    private List<String> sentences;
    private TextView sentenceTextView;
    private EditText typingEditText;
    private Button submitButton;
    private int currentSentenceIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typing_guide);

        View rootView = findViewById(R.id.rootView);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
            }
        });

        Button prevButton = findViewById(R.id.prevButton);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPrevSentence();
            }
        });

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextSentence();
            }
        });

        sentences = SentenceProvider.getSentences();
        sentenceTextView = findViewById(R.id.sentenceTextView);
        typingEditText = findViewById(R.id.typingEditText);
        typingEditText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTyping();
            }
        });
        showNextSentence();
    }
    private void showPrevSentence() {
        currentSentenceIndex--;
        if (currentSentenceIndex >= 0 && currentSentenceIndex < sentences.size()) {
            sentenceTextView.setText(sentences.get(currentSentenceIndex));
            typingEditText.setText("");
        } else {
            currentSentenceIndex = 0; // 첫 번째 문장으로 되돌아가게 설정
        }
    }
    private void showNextSentence() {
        currentSentenceIndex++;
        if (currentSentenceIndex < sentences.size()) {
            sentenceTextView.setText(sentences.get(currentSentenceIndex));
            typingEditText.setText("");
        } else {
            showGameOverDialog();
        }
    }

    private void showGameOverDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Perfect Typing");
        builder.setMessage("You have typed all the sentences. Would you like to start again?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restartGame();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(TypingGuideActivity.this, ChooseCategoriesActivity.class));
                finish();
            }
        });
        builder.setCancelable(false); // 뒤로 가기 버튼을 눌러도 창이 닫히지 않도록 설정
        builder.show();
    }

    private void restartGame() {
        currentSentenceIndex = -1;
        showNextSentence();
        typingEditText.setEnabled(true);
        submitButton.setEnabled(true);
    }

    private void checkTyping() {
        String userInput = typingEditText.getText().toString().trim();
        String correctSentence = sentences.get(currentSentenceIndex).trim();

        if (userInput.equals(correctSentence)) {
            showNextSentence();
        } else {
            showRetryDialog();
        }
    }
    private void showRetryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error Feedback");
        builder.setMessage("- Make sure you have a good spacing \n \n" +
                           "- Make sure you have entered the special symbol correctly \n \n" +
                           "- Make sure you distinguish between uppercase and lowercase letters");
        builder.setPositiveButton("Re-enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 다시 입력하도록 유도
                typingEditText.setText("");
            }
        });
        builder.show();
    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}