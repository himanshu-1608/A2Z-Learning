package com.example.questionbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    private static final String FILE_NAME = "password.txt";
    TextView tv5,tv7;
    Button b;
    EditText pass,et3,et4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        tv5 = findViewById(R.id.tv5);
        tv7 = findViewById(R.id.tv7);
        b = findViewById(R.id.button);
        pass = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);

        b.setOnClickListener((v)->{
            String text = pass.getText().toString();
            FileOutputStream fos = null;
            try {
                fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                fos.write(text.getBytes());
                System.out.println("Saved the signup details : " + text);
            } catch (IOException io) {
                io.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
            }
        });

        et4.setOnFocusChangeListener((v, hasFocus) -> {
            tv5.setVisibility(View.VISIBLE);
        });

        pass.setOnFocusChangeListener((v, hasFocus) -> {
            tv7.setVisibility(View.VISIBLE);
        });
    }
}
