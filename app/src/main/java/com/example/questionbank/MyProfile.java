package com.example.questionbank;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyProfile extends AppCompatActivity {

    private static final String DATA = "data.txt";

    Button nameedit,changepass;
    String passstring;
    TextView nameval,emailval,mobval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        nameval = findViewById(R.id.nameval);
        emailval = findViewById(R.id.emailval);
        mobval = findViewById(R.id.mobval);
        nameedit = findViewById(R.id.nameedit);
        changepass = findViewById(R.id.changepass);
        setValues();
        final Boolean[] namer = {false};
        nameedit.setOnClickListener(v -> {
            if(!namer[0]) {
                nameval.setFocusableInTouchMode(true);
                nameval.setEnabled(true);
                namer[0] = true;
                nameedit.setBackgroundResource(R.drawable.ok);
            } else {
                nameval.setBackground(null);
                nameval.setEnabled(false);
                nameval.setFocusable(false);
                nameedit.setBackgroundResource(R.drawable.edit);
                namer[0] = false;
            }
        });

        changepass.setOnClickListener(v -> {
            Intent it = new Intent(MyProfile.this,PasswordChanger.class);
            it.putExtra("Passcode",passstring);
            startActivity(it);
        });

    }

    private void setValues() {
        nameval.setBackground(null);
        nameval.setEnabled(false);
        nameval.setFocusable(false);
        FileInputStream fis = null;

        try {
            fis = openFileInput(DATA);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text);
                sb.append("qqAB");
            }
            StringBuilder out;
            out = sb;
            int lastind = out.indexOf("qqAB");
            String namestring = out.substring(9, lastind);
            out.delete(0, lastind + 17);
            lastind = out.indexOf("emailID");
            out.delete(0, lastind + 8);
            lastind = out.indexOf("qqAB");
            String emailstring = out.substring(0,lastind);
            lastind = out.indexOf("MOBILE:");
            out.delete(0, lastind + 7);
            String mobnumstring = out.substring(0,10);
            out.delete(0, 23);
            passstring = out.toString();
            passstring = passstring.substring(0,passstring.length()-4);
            nameval.setText(namestring);
            emailval.setText(emailstring);
            mobval.setText(mobnumstring);
        } catch (IOException e) {
            System.out.println("Error Reading File");
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
