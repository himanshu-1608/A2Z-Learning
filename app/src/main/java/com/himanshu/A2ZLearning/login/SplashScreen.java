package com.himanshu.a2zlearning.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.himanshu.a2zlearning.ui.activities.MainActivity;
import com.himanshu.a2zlearning.R;
import com.himanshu.a2zlearning.utils.Res;

public class SplashScreen extends AppCompatActivity {

    public static final int SPLASH_TIME_OUT=2000;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sp = getSharedPreferences(Res.sp1,MODE_PRIVATE);

        new Handler().postDelayed(() -> {
            if(FirebaseAuth.getInstance().getCurrentUser() != null) {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
            } else {
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
            }
            finish();
            /*boolean logged = sp.getBoolean("isLogged", false);
            if (logged) {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            } else {
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                finish();
            }*/
        },SPLASH_TIME_OUT);
    }

}
