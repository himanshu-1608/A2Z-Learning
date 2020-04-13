package com.example.questionbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class UpscTests extends AppCompatActivity {

    Button b1,b2,b3,b11,b12,b13,b21,b22,b23,b31,b32,b33;
    Boolean aon,bon,con;
    LinearLayout l1,l2,l3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upsc_tests);

        aon = false;
        bon = false;
        con = false;
        work();
        l1gone();
        l2gone();
        l3gone();
        b1.setOnClickListener(v -> {
            if(!aon){
                showallA();
                aon = true;
            } else {
                removeallA();
                aon = false;
            }
        });

        b2.setOnClickListener(v -> {
            if(!bon){
                showallB();
                bon = true;
            } else {
                removeallB();
                bon = false;
            }
        });

        b3.setOnClickListener(v -> {
            if(!con){
                showallC();
                con = true;
            } else {
                removeallC();
                con = false;
            }
        });
        Button[] butgrp = new Button[] {b11,b12,b13,b21,b22,b23,b31,b32,b33};
        final String[] strgrp = new String[]{"UpscFullTest1.txt","UpscFullTest2.txt","UpscFullTest3.txt","UpscPolityTest1.txt","UpscPolityTest2.txt","UpscPolityTest3.txt","UpscEconomyTest1.txt","UpscEconomyTest2.txt","UpscEconomyTest3.txt"};
        for(int i = 0 ; i < 9 ; i++ ) {
            int finalI = i;
            butgrp[i].setOnClickListener(v -> {
                final String sentfile = strgrp[finalI];
                Intent it = new Intent(UpscTests.this,TestHandler.class);
                it.putExtra("filename",sentfile);
                startActivity(it);
            });
        }

    }

    private void l1came() {
        l1.setVisibility(View.VISIBLE);
    }
    private void l2came() {
        l2.setVisibility(View.VISIBLE);
    }
    private void l3came() {
        l3.setVisibility(View.VISIBLE);
    }
    private void l1gone() {
        l1.setVisibility(View.GONE);
    }
    private void l2gone() {
        l2.setVisibility(View.GONE);
    }
    private void l3gone() {
        l3.setVisibility(View.GONE);
    }
    private void showallA() {
        l1came();
        l2gone();
        l3gone();
        bon = false;
        con = false;
    }
    private void removeallA() {
        l1gone();
    }
    private void showallB() {
        l2came();
        l1gone();
        l3gone();
        aon = false;
        con = false;
    }
    private void removeallB() {
        l2gone();
    }
    private void showallC() {
        l3came();
        l1gone();
        l2gone();
        aon = false;
        bon = false;
    }
    private void removeallC() {
        l3gone();
    }

    private void work() {
        b1  = findViewById(R.id.b1);
        b2  = findViewById(R.id.b2);
        b3  = findViewById(R.id.b3);
        b11 = findViewById(R.id.b11);
        b12 = findViewById(R.id.b12);
        b13 = findViewById(R.id.b13);
        b21 = findViewById(R.id.b21);
        b22 = findViewById(R.id.b22);
        b23 = findViewById(R.id.b23);
        b31 = findViewById(R.id.b31);
        b32 = findViewById(R.id.b32);
        b33 = findViewById(R.id.b33);
        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
    }

}
