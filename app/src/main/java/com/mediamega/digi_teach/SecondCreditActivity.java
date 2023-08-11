package com.mediamega.digi_teach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondCreditActivity extends AppCompatActivity {

    private Button nextButton;
    private Button prevButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_credit);

        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondCreditActivity.this , ThirdCreditActivity.class);
                startActivity(intent);
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondCreditActivity.this, FirstCreditActivity.class);
                startActivity(intent);
            }
        });
    }
}