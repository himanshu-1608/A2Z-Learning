package com.himanshu.a2zlearning.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.himanshu.a2zlearning.R;

import java.util.Objects;

public class ForgetActivity extends AppCompatActivity {

    FirebaseAuth fireAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        Button btnnext = findViewById(R.id.btnnext);
        EditText etemail = findViewById(R.id.etemail);
        RelativeLayout prL = findViewById(R.id.progressLayout);
        RelativeLayout mainL = findViewById(R.id.mainLayout);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        fireAuth = FirebaseAuth.getInstance();

        btnnext.setOnClickListener( view -> {
            progressBar.setVisibility(View.VISIBLE);
            prL.setVisibility(View.VISIBLE);
            mainL.setVisibility(View.GONE);
            fireAuth.sendPasswordResetEmail(etemail.getText().toString().trim())
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);
                        prL.setVisibility(View.GONE);
                        mainL.setVisibility(View.VISIBLE);
                        if(task.isSuccessful()) {
                            Toast.makeText(ForgetActivity.this,
                                    "A reset password email is sent.",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ForgetActivity.this,
                                    Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }
}
