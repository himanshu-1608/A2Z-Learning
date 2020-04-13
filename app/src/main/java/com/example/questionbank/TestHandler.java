package com.example.questionbank;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestHandler extends TestLayout {

    View test;
    long timeofExercise;
    Boolean teststate;
    String filename,filesent;
    @Override
    public void onBackPressed() {
        if(teststate) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Quit App")
                    .setCancelable(false)
                    .setMessage("Are You Sure to quit the app?")
                    .setPositiveButton("Ok",(dialog, which) -> {
                        finishAffinity();
                        System.out.println("Exiting the app!!!");
                    })
                    .setNegativeButton("Cancel",null);

            AlertDialog alert = builder.create();
            alert.show();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler);
        Intent it = getIntent();
        filename = it.getStringExtra("filename");
        assert filename != null;
        filesent =filename.substring(0,filename.length()-4);
        test = findViewById(R.id.inflated_test);
        queCount = 4;
        teststate = false;
        timeofExercise = 300000;
        StartActivities(test,queCount,timeofExercise);
        allGone();

        startup.setOnClickListener(v -> {
            startup.setVisibility(View.GONE);
            endTest.setVisibility(View.VISIBLE);
            teststate = true;
            StringBuilder rawText = new StringBuilder();
            BufferedReader reader = null;
            try {
                assert filename != null;
                reader = new BufferedReader(new InputStreamReader(getAssets().open(filename)));
                String mLine;
                while((mLine = reader.readLine())!= null){
                    rawText.append(mLine);
                    rawText.append("qq");
                }
                setQuestions(rawText);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),"Error reading file !", Toast.LENGTH_LONG).show();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                allCame();
                startTimer();
            }
        });

        endTest.setOnClickListener(v -> {
            teststate = false;
            showResults(filesent);
            timer.cancel();
            endTest.setVisibility(View.GONE);
        });

    }

    private void startTimer() {
        timer = new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateText();
            }

            @Override
            public void onFinish() {
                showResults(filesent);
                teststate = false;
                endTest.setVisibility(View.GONE);
                timer.cancel();
            }
        }.start();
    }

    private void updateText() {
        int minutes =(int) timeLeft/60000;
        int seconds = (int) timeLeft % 60000 / 1000 ;
        String TimeText = "";
        if(minutes < 10) TimeText += "0";
        TimeText += minutes+":";
        if(seconds < 10) TimeText += "0";
        TimeText += seconds;
        timeText.setText(TimeText);
    }

}
