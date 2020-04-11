package com.example.questionbank;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestLayout extends AppCompatActivity {

    private final static String Total = "total.txt";
    private final static String Correct = "correct.txt";
    private final static String TestName = "testname.txt";
    int queCount;
    CountDownTimer timer;
    long timeLeft;
    RadioButton r11,r12,r13,r14,r21,r22,r23,r24,r31,r32,r33,r34,r41,r42,r43,r44;
    TextView q1,q2,q3,q4;
    RadioGroup rg1,rg2,rg3,rg4;
    RadioButton[] rblist;
    RadioGroup[] rglist;
    TextView[] qlist;
    Button startup,endTest;
    TextView no_of_ques,timeText,Result;
    String ans="",tot,cor,nam;
    int CorrectAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_layout);

    }

    protected void StartActivities(View v,int quecount,long timeleft) {
        r11 =  v.findViewById(R.id.op11);
        r12 =  v.findViewById(R.id.op12);
        r13 =  v.findViewById(R.id.op13);
        r14 =  v.findViewById(R.id.op14);
        r21 =  v.findViewById(R.id.op21);
        r22 =  v.findViewById(R.id.op22);
        r23 =  v.findViewById(R.id.op23);
        r24 =  v.findViewById(R.id.op24);
        r31 =  v.findViewById(R.id.op31);
        r32 =  v.findViewById(R.id.op32);
        r33 =  v.findViewById(R.id.op33);
        r34 =  v.findViewById(R.id.op34);
        r41 =  v.findViewById(R.id.op41);
        r42 =  v.findViewById(R.id.op42);
        r43 =  v.findViewById(R.id.op43);
        r44 =  v.findViewById(R.id.op44);
        rg1 =  v.findViewById(R.id.rg1);
        rg2 =  v.findViewById(R.id.rg2);
        rg3 =  v.findViewById(R.id.rg3);
        rg4 =  v.findViewById(R.id.rg4);
        q1  =  v.findViewById(R.id.q1);
        q2  =  v.findViewById(R.id.q2);
        q3  =  v.findViewById(R.id.q3);
        q4  =  v.findViewById(R.id.q4);
        Result = v.findViewById(R.id.Result);
        rblist = new RadioButton[]{r11,r12,r13,r14,r21,r22,r23,r24,r31,r32,r33,r34,r41,r42,r43,r44};
        rglist = new RadioGroup[]{rg1,rg2,rg3,rg4};
        qlist = new TextView[]{q1,q2,q3,q4};
        startup = v.findViewById(R.id.start_button);
        no_of_ques = v.findViewById(R.id.no_of_ques);
        queCount = quecount;
        no_of_ques.setText("No.of Questions : " + queCount);
        timeText = v.findViewById(R.id.count_time);
        endTest = v.findViewById(R.id.endTest);
        endTest.setVisibility(View.GONE);
        Result.setVisibility(View.GONE);
        timeLeft = timeleft;
        updateText();
    }

    protected void allGone() {
        for ( RadioGroup rg : rglist ) {
            rg.setVisibility(View.GONE);
        }
        for ( TextView q : qlist ) {
            q.setVisibility(View.GONE);
        }
    }

    protected void setQuestions(StringBuilder rawText) {
        for (int i = 0; i < queCount; i++) {
            int lastind = rawText.indexOf("qq");
            String ss = rawText.substring(0, lastind);
            qlist[i].setText(ss);
            rawText.delete(0, lastind + 2);
        }
        for (int i = 0; i < (4 * queCount); i++) {
            int lastind = rawText.indexOf("qq");
            String ss = rawText.substring(0, lastind);
            rblist[i].setText(ss);
            rawText.delete(0, lastind + 2);
        }
        for(int i = 0 ; i < queCount ; i++) {
            int lastind = rawText.indexOf("qq");
            String ss =  rawText.substring(0,lastind);
            ans += ss;
            rawText.delete(0,lastind+2);
        }
    }

    protected void allCame() {
        for ( RadioGroup rg : rglist ) {
            rg.setVisibility(View.VISIBLE);
        }
        for ( TextView q : qlist ) {
            q.setVisibility(View.VISIBLE);
        }
    }
    protected void showResults(String filename){
        int Score = 0;
        char checker;
        no_of_ques.setVisibility(View.GONE);
        RadioButton[][] check = {{r11,r12,r13,r14},{r21,r22,r23,r24},{r31,r32,r33,r34},{r41,r42,r43,r44}};
        char[] charArr1 = ans.toCharArray();
        for(int i = 0 ; i < queCount ; i++ ) {
            int select = rglist[i].getCheckedRadioButtonId();
            RadioButton locate = rglist[i].findViewById(select);
            if (locate == check[i][0]) {
                checker = '1';
                if(checker == charArr1[i]) {
                    Score++;
                    locate.setBackgroundColor(Color.parseColor("#d4edda"));
                } else {
                    locate.setBackgroundColor(Color.parseColor("#ffcccc"));
                }
            }
            if (locate == check[i][1]) {
                checker = '2';
                if(checker == charArr1[i]) {
                    Score++;
                    locate.setBackgroundColor(Color.parseColor("#d4edda"));
                } else {
                    locate.setBackgroundColor(Color.parseColor("#ffcccc"));
                }
            }
            if (locate == check[i][2]) {
                checker = '3';
                if(checker == charArr1[i]) {
                    Score++;
                    locate.setBackgroundColor(Color.parseColor("#d4edda"));
                } else {
                    locate.setBackgroundColor(Color.parseColor("#ffcccc"));
                }
            }
            if (locate == check[i][3]) {
                checker = '4';
                if(checker == charArr1[i]) {
                    Score++;
                    locate.setBackgroundColor(Color.parseColor("#d4edda"));
                } else {
                    locate.setBackgroundColor(Color.parseColor("#ffcccc"));
                }
            }

        }
        for(int i = 0 ; i < queCount ; i++ ) {
            if (charArr1[i] == '1') {
                check[i][0].setBackgroundColor(Color.parseColor("#d4edda"));
            }
            else if (charArr1[i] == '2') {
                check[i][1].setBackgroundColor(Color.parseColor("#d4edda"));
            }
            else if (charArr1[i] == '3') {
                check[i][2].setBackgroundColor(Color.parseColor("#d4edda"));
            }
            else if (charArr1[i] == '4') {
                check[i][3].setBackgroundColor(Color.parseColor("#d4edda"));
            }

        }
        for(int i=0;i<4*queCount;i++){
            rblist[i].setClickable(false);
        }
        CorrectAnswers = Score;
        Result.setText("Total No. of Questions : "+queCount+"\n"+"Your correct answers : "+CorrectAnswers);
        Result.setVisibility(View.VISIBLE);
        updatePerformance(filename);
    }

    private void updatePerformance(String filename) {
        String puttotal,putcorrect,putname;
        if (!exists()) {
            puttotal = "1\n";
            putcorrect = ""+CorrectAnswers;
            putname = filename+"\n";
            FileOutputStream fos1 = null,fos2 = null,fos3 = null;
            try {
                fos1 = openFileOutput(Total, MODE_PRIVATE);
                fos2 = openFileOutput(Correct, MODE_PRIVATE);
                fos3 = openFileOutput(TestName, MODE_PRIVATE);
                fos1.write(puttotal.getBytes());
                fos2.write(putcorrect.getBytes());
                fos3.write(putname.getBytes());
            } catch (IOException io) {
                io.printStackTrace();
            } finally {
                if (fos1 != null) {
                    try {
                        fos1.close();
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
                if (fos2 != null) {
                    try {
                        fos2.close();
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
                if (fos3 != null) {
                    try {
                        fos3.close();
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
            }
        } else {
            FileInputStream fis1 = null,fis2 = null,fis3 = null;
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
                String t1,t2,t3;
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
                    sb3.append("\n");
                }
                int l1,l2;
                l1 = sb1.indexOf("qq");
                l2 = sb2.indexOf("qq");
                tot = sb1.substring(0,l1);
                cor = sb2.substring(0,l2);
                nam = sb3.toString();
                l1 = Integer.parseInt(tot);
                l1 += 1;
                tot = ""+ l1;
                cor += CorrectAnswers;
                nam += filename+"\n";
            } catch (IOException e) {
                System.out.println("Error Reading File");
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
            FileOutputStream fos1 = null,fos2 = null,fos3 = null;
            try {
                fos1 = openFileOutput(Total, MODE_PRIVATE);
                fos1.write(tot.getBytes());
                fos2 = openFileOutput(Correct, MODE_PRIVATE);
                fos2.write(cor.getBytes());
                fos3 = openFileOutput(TestName, MODE_PRIVATE);
                fos3.write(nam.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos1 != null) {
                    try {
                        fos1.close();
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
                if (fos2 != null) {
                    try {
                        fos2.close();
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
                if (fos3 != null) {
                    try {
                        fos3.close();
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
            }
        }
    }

    private boolean exists() {
        File file = new File(getApplicationContext().getFilesDir(),Total);
        return file.exists();
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
