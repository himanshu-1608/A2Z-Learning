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

public class TestLayout extends AppCompatActivity {

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
    String ans="";
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
    protected void showResults(){
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
