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

public class EmailGuideActivity extends AppCompatActivity {

    EditText editText, editText2, editText3;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_guide);

        View rootView = findViewById(R.id.rootView);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
            }
        });

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        sendButton = findViewById(R.id.button);
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = editText.getText().toString().trim();
                String input2 = editText2.getText().toString().trim();
                String input3 = editText3.getText().toString().trim();

                // 첫번째, 두번째, 세번째 EditText가 정확한 hint와 같은 문장을 입력한 경우
                if (input1.equals("mediamega@digiteach.com") &&
                        input2.equals("Mabuhay Mediamega!") &&
                        input3.equals("Hello MediaMega! Korea is a beautiful country.")) {
                    // 팝업창 띄우기
                    showRestartPopup();
                } else {
                    // 다시 입력하라는 팝업창 띄우기
                    showRetryPopup();
                }
            }
        });
    }

    private void showRetryPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(EmailGuideActivity.this);
        builder.setTitle("Re-enter");
        builder.setMessage("Please enter the correct text.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void showRestartPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(EmailGuideActivity.this);
        builder.setTitle("It's been transferred!");
        builder.setMessage("Do you want to start over?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 액티비티 다시 시작
                recreate();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(EmailGuideActivity.this, ChooseCategoriesActivity.class);
                startActivity(intent);
                finish(); // 현재 액티비티 종료
                dialog.dismiss();
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
