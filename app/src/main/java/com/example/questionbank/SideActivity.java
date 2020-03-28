package com.example.questionbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class SideActivity extends AppCompatActivity {
    private static final String FILE_NAME = "password.txt";
    EditText et1,et2;
    Button b1,b2;
    TextView tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        b1   = (Button) findViewById(R.id.b1);
        b2   = (Button) findViewById(R.id.b2);
        tv3 = (TextView) findViewById(R.id.tv3);

        Intent aaya = getIntent();
        String one = aaya.getStringExtra("pehla");
        String two = aaya.getStringExtra("doosra");
        et1.setText(one);
        et2.setText(two);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(et1.getText().toString());
                int j = Integer.parseInt(et2.getText().toString());
                int k = i + j;
                tv3.setText("Result is : " + k);
                Toast.makeText(SideActivity.this, "You got answer" + k , Toast.LENGTH_LONG).show();
                Intent it = new Intent(SideActivity.this, MainActivity.class);
                it.putExtra("pehla",et1.getText().toString());
                it.putExtra("doosra",et2.getText().toString());
                startActivity(it);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String pass = et2.getText().toString();
            FileOutputStream fos = null;
            try {
                fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                fos.write(pass.getBytes());
                System.out.println("Saved the password for signup time : " + pass);
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

            Toast.makeText(SideActivity.this, "You got answer", Toast.LENGTH_LONG).show();
            Intent it = new Intent(SideActivity.this, MainActivity.class);
            it.putExtra("pehla",et1.getText().toString());
            it.putExtra("doosra",et2.getText().toString());
            startActivity(it);
            }
        });
    }
}
