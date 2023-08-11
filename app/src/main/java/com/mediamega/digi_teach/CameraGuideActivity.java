package com.mediamega.digi_teach;

import android.Manifest;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class CameraGuideActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 100;

    Button CameraOn;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_guide);

        CameraOn = (Button) findViewById(R.id.CameraOn);
        imageView = (ImageView) findViewById(R.id.imageView);

        Button cameraButton = findViewById(R.id.CameraOn);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 카메라 권한 확인 및 요청
                checkCameraPermission();
            }
        });
    }

    private void checkCameraPermission() {
        // 카메라 권한이 허용되어 있는지 확인
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            // 권한이 이미 허용되어 있으면 카메라 촬영을 시작
            startCamera();
        } else {
            // 권한이 허용되어 있지 않으면 권한 요청
            requestCameraPermission();
        }
    }

    private void requestCameraPermission() {
        // 카메라 권한 요청
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                REQUEST_CAMERA_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            // 권한 요청 결과 처리
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한이 허용되면 카메라 촬영을 시작
                startCamera();
            } else {
                // 권한이 거부되면 사용자에게 권한이 필요함을 알리는 메시지를 보여줄 수 있음
                // 이 부분은 사용자 경험을 위해 추가적인 처리를 할 수 있습니다.
            }
        }
    }

    private void startCamera() {
        // 카메라 기능을 Intent
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("That's a great picture!");
        builder.setMessage("Do you want to take the picture again??");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // "네"를 선택하면 다시 카메라 촬영을 시작
                startCamera();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(CameraGuideActivity.this, ChooseCategoriesActivity.class);
                startActivity(intent);
                // 현재 액티비티 종료 (팝업창을 닫음)
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(alertDialog.getWindow().getAttributes());
        int margin = getResources().getDimensionPixelSize(R.dimen.dialog_margin);
        layoutParams.y = margin; // y축으로 원하는 위치로 조정
        alertDialog.getWindow().setAttributes(layoutParams);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)    {
        super.onActivityResult(requestCode, resultCode, data);

        // 카메라 촬영을 하면 이미지뷰에 사진 삽입
        if(requestCode == 0 && resultCode == RESULT_OK) {
            // Bundle로 데이터를 입력
            Bundle extras = data.getExtras();

            // Bitmap으로 컨버전
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            // 이미지뷰에 Bitmap으로 이미지를 입력
            imageView.setImageBitmap(imageBitmap);

            //촬영 후 팝업창 띄우기
            showConfirmationDialog();
        }
    }
}