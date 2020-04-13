package com.example.questionbank;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class JeeVideos extends AppCompatActivity {

    Button b1,b2,b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jee_videos);

        b1 = findViewById(R.id.b11);
        b2 = findViewById(R.id.b12);
        b3 = findViewById(R.id.b13);
        b4 = findViewById(R.id.b14);
        final Button[] bgrp = new Button[]{b1,b2,b3,b4};

        final String[] linklist = new String[]{"PL7rZUH8srnuMBYGxK7WsYmsD5ln7sEc73","PL7rZUH8srnuOubmBqCWQ6Uj-MG-FQREcr","PL7rZUH8srnuMd3PVxiJIOFaBdZKu0hcEQ","PL7rZUH8srnuMWp2vnqHB64jbCM7U-ryuM"};

        for(int i = 0 ; i < bgrp.length ; i++) {
            int finalI = i;
            bgrp[i].setOnClickListener(v -> {
                String str = "https://www.youtube.com/playlist?list=" + linklist[finalI];
                Uri webpage = Uri.parse(str);
                startActivity(new Intent(Intent.ACTION_VIEW,webpage));
            });
        }
    }
}
