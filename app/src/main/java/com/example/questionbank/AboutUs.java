package com.example.questionbank;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutUs extends AppCompatActivity {

    TextView git;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        git = findViewById(R.id.github); 
        git.setOnClickListener(v -> {
            Uri link = Uri.parse("https://github.com/himanshu-1608/Question-Bank");
            Intent webpack = new Intent(Intent.ACTION_VIEW,link);
            startActivity(webpack);
        });
        
    }
}
