package com.example.questionbank;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UpscCodes extends AppCompatActivity {

    TextView scroll1;
    TextView scroll2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upsc_codes);

        scroll1 = findViewById(R.id.scroll1);
        scroll2 = findViewById(R.id.scroll2);
        setScroll1();
        setScroll2();

    }

    private void setScroll1() {
        StringBuilder text = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("BankACType.txt")));
            String mLine;
            while((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append("\n");

            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"Error reading file : Types of bank accounts!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        scroll1.setText(text);
    }

    private void setScroll2() {
        StringBuilder text = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("MoneyFunction.txt")));
            String mLine;
            while((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append("\n");
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"Error reading file : Functions of money!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        scroll2.setText(text);

    }

}
