package com.example.questionbank;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MyProfile extends AppCompatActivity {

    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        tv1 = findViewById(R.id.tv1);
        tv1.setOnClickListener(v -> {
            Toast.makeText(this,"HOOOOOWHKDVJHWVCJHVCJEVCFEWEFK",Toast.LENGTH_LONG).show();
        });


    }
}
