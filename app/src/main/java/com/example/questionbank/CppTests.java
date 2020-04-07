package com.example.questionbank;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CppTests extends AppCompatActivity {

    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpp_tests);

        b1 = findViewById(R.id.b11);
        b2 = findViewById(R.id.b12);
        b3 = findViewById(R.id.b13);

        b1.setOnClickListener(v -> {
            startActivity(new Intent(this,CppFullLength.class));
        });

        b2.setOnClickListener(v -> {
            startActivity(new Intent(this,TestLayout.class));
        });
    }
}
