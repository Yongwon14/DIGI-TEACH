package com.mediamega.digi_teach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirdCreditActivity extends AppCompatActivity {

    private Button endButton;
    private Button prevButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_credit);

        endButton = findViewById(R.id.endButton);
        prevButton = findViewById(R.id.prevButton);

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdCreditActivity.this , ChooseCategoriesActivity.class);
                startActivity(intent);
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdCreditActivity.this, SecondCreditActivity.class);
                startActivity(intent);
            }
        });
    }
}