package com.example.questionbank;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SideActivity extends AppCompatActivity {
    EditText et1,et2;
    Button b1,b2;
    TextView tv1,tv2,tv3;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        b1  = findViewById(R.id.b1);
        b2  = findViewById(R.id.b2);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);

        setAllInVisible();

        b1.setOnClickListener(v -> {
            int i = Integer.parseInt(et1.getText().toString());
            int j = Integer.parseInt(et2.getText().toString());
            int k = i + j;
            tv3.setText("Result is : " + k);
        });
        b2.setOnClickListener(v -> {
            tv1.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);
            et1.setVisibility(View.VISIBLE);
            et2.setVisibility(View.VISIBLE);
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.INVISIBLE);
        });
    }

    private void setAllInVisible() {
        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        et1.setVisibility(View.INVISIBLE);
        et2.setVisibility(View.INVISIBLE);
        b1.setVisibility(View.INVISIBLE);
    }
}
