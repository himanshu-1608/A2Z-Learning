package com.himanshu.a2zlearning.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.himanshu.a2zlearning.MainActivity;
import com.himanshu.a2zlearning.R;

public class LoginActivity extends AppCompatActivity {

    private static final String DATA = "UserData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final SharedPreferences sp = getSharedPreferences(DATA, MODE_PRIVATE);
        final EditText phone = findViewById(R.id.etphone);
        final EditText password = findViewById(R.id.etpassword);
        Button btnlogin = findViewById(R.id.btnlogin);
        TextView txtforgot = findViewById(R.id.txtforgot);
        TextView txtsignup = findViewById(R.id.txtsignup);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String checkphone = sp.getString("UserPhone","void");
                String checkpass = sp.getString("UserPassword","void");
                String phoneno = phone.getText().toString();
                String passwordtext = password.getText().toString();

                if( (phoneno.equals(checkphone)) &&(passwordtext.equals(checkpass)) ) {
                    sp.edit().putBoolean("isLogged",true).apply();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,"User Not Found, Please Sign Up",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,SignupActivity.class));
                }
            }
        });

        txtforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgetActivity.class));
            }
        });

        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });

    }
}
