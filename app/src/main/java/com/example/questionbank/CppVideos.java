package com.example.questionbank;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CppVideos extends AppCompatActivity {

    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpp_videos);

        b1 = findViewById(R.id.b11);
        b2 = findViewById(R.id.b12);
        b1.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.youtube.com/watch?v=HgUOWB0StNE");
            Intent webIntent = new Intent(Intent.ACTION_VIEW,webpage);
            startActivity(webIntent);
        });
        b2.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.youtube.com/watch?v=nLmhmB6NzcM");
            Intent webIntent = new Intent(Intent.ACTION_VIEW,webpage);
            startActivity(webIntent);
        });
    }
}