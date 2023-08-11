package com.mediamega.digi_teach;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MessageGuideActivity extends AppCompatActivity {

    EditText editText2;
    TextView MessageText;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        View rootView = findViewById(R.id.rootView);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
            }
        });

        editText2 = findViewById(R.id.editText2);
        MessageText = findViewById(R.id.MessageText);
        sendButton = findViewById(R.id.sendButton);
        editText2.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editText2.getText().toString();

                // EditText에 입력된 값이 "Yes, it's helpful!"인 경우 MessageText에 설정
                if (inputText.equals("Yes, it's helpful!")) {
                    MessageText.setText(inputText);

                    AlertDialog.Builder builder = new AlertDialog.Builder(MessageGuideActivity.this);
                    builder.setTitle("Your message was sent well!");
                    builder.setMessage("Shall we start over?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editText2.setText("");
                            MessageText.setText("");
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MessageGuideActivity.this, ChooseCategoriesActivity.class);
                            startActivity(intent);
                            // 현재 액티비티 종료 (팝업창을 닫음)
                            finish();
                        }
                    });
                    builder.show();
                } else {
                    // 다른 문장을 입력한 경우 팝업 창 띄우기
                    AlertDialog.Builder builder = new AlertDialog.Builder(MessageGuideActivity.this);
                    builder.setTitle("Enter it again!");
                    builder.setMessage("- Type the sentences that are blurry(Yes, it's helpful!)\n" +
                                       "- If it's difficult, I recommend you visit the Typing Guide first");
                    builder.setNegativeButton("Re-enter", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editText2.setText("");
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            }
        });
    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
