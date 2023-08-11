package com.mediamega.digi_teach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseCategoriesActivity extends AppCompatActivity {

    private Button TypingGuide;
    private Button CameraGuide;
    private Button MessageCall;
    private Button Email;

    private Button Credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_categories);

        TypingGuide = findViewById(R.id.TypingGuide);
        CameraGuide = findViewById(R.id.CameraGuide);
        MessageCall = findViewById(R.id.MessageCall);
        Email = findViewById(R.id.Email);
        Credit = findViewById(R.id.Credit);

        TypingGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoriesActivity.this , TypingTutorialActivity.class);
                startActivity(intent);
            }
        });

        CameraGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoriesActivity.this , CameraTutorialActivity.class);
                startActivity(intent);
            }
        });
        MessageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoriesActivity.this , MessageTutorialActivity.class);
                startActivity(intent);
            }
        });
        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoriesActivity.this , EmailTutorialActivity.class);
                startActivity(intent);
            }
        });
        Credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCategoriesActivity.this , FirstCreditActivity.class);
                startActivity(intent);
            }
        });
    }
}