package com.example.questionbank;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class PerformanceEvaluation extends AppCompatActivity {

    private final static String Total = "total.txt";
    private final static String Correct = "correct.txt";
    private final static String TestName = "testname.txt";
    TextView one,two,three,counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_evaluation);

        one     = findViewById(R.id.one);
        two     = findViewById(R.id.two);
        three   = findViewById(R.id.three);
        counter = findViewById(R.id.Counter);
        FileInputStream fis1 = null, fis2 = null,fis3 = null;
        if(!exists()) {
            Toast.makeText(PerformanceEvaluation.this, "No Tests Given till Now", Toast.LENGTH_LONG).show();
            counter.setText("0");
        }
        else {
            try {
                fis1 = openFileInput(Total);
                fis2 = openFileInput(Correct);
                fis3 = openFileInput(TestName);
                InputStreamReader isr1 = new InputStreamReader(fis1);
                InputStreamReader isr2 = new InputStreamReader(fis2);
                InputStreamReader isr3 = new InputStreamReader(fis3);
                BufferedReader br1 = new BufferedReader(isr1);
                BufferedReader br2 = new BufferedReader(isr2);
                BufferedReader br3 = new BufferedReader(isr3);
                StringBuilder sb1 = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                StringBuilder sb3 = new StringBuilder();
                String t1, t2, t3;
                while ((t1 = br1.readLine()) != null) {
                    sb1.append(t1);
                    sb1.append("qq");
                }
                while ((t2 = br2.readLine()) != null) {
                    sb2.append(t2);
                    sb2.append("qq");
                }
                while ((t3 = br3.readLine()) != null) {
                    sb3.append(t3);
                    sb3.append("qq");
                }
                int l1, l2, l3;
                l1 = sb1.indexOf("qq");
                l2 = sb2.indexOf("qq");
                String a = sb1.substring(0, l1);
                String b = sb2.substring(0, l2);
                char[] barr = b.toCharArray();
                String pehli = "", doosri = "", teesri = "";
                counter.setText("  " + a);
                String temp;
                int count = Integer.parseInt(a);
                for (int i = 0; i < count; i++) {
                    l3 = sb3.indexOf("qq");
                    temp = sb3.substring(0, l3);
                    sb3.delete(0, l3 + 2);
                    pehli += temp + "\n\n";
                    doosri += "4\n\n";
                    teesri += barr[i] + "\n\n";
                }
                one.setText(pehli);
                two.setText(doosri);
                three.setText(teesri);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis1 != null) {
                    try {
                        fis1.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis2 != null) {
                    try {
                        fis2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis3 != null) {
                    try {
                        fis3.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private boolean exists() {
        File file = new File(getApplicationContext().getFilesDir(),Total);
        return file.exists();
    }
}