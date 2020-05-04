package com.himanshu.a2zlearning.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.himanshu.a2zlearning.MainActivity;
import com.himanshu.a2zlearning.R;
import com.himanshu.a2zlearning.RankingActivity;

public class SplashScreen extends AppCompatActivity {

    public static final int SPLASH_TIME_OUT=2000;
    private static final String FILE_NAME = "UserData";
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sp = getSharedPreferences(FILE_NAME,MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean logged = sp.getBoolean("isLogged", false);
                if (logged) {
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    finish();
                }
            }
        },SPLASH_TIME_OUT);
    }
}
