package com.example.questionbank;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.Objects;

public class SplashActivity extends AppCompatActivity {

    private static final String FILE_NAME = "data.txt";
    final Handler handler = new Handler();
    final Runnable r = this::checkandset;

    private void checkandset() {
        if (!isLogged()) {
            startActivity(new Intent(SplashActivity.this, SignUpActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }
    }

    private boolean isLogged() {
        File file = new File(getApplicationContext().getFilesDir(),FILE_NAME);
        return file.exists();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();

        handler.postDelayed(r, 1500);

    }
}
