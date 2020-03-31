package com.example.questionbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "data.txt";

    EditText num1,num2;
    Button add;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isLogged()){
            Intent it = new Intent(MainActivity.this,SignUpActivity.class);
            startActivity(it);
        }

//        Here starts the actual execution of main Activity . Above this is the code of login activity.
        num1   = findViewById(R.id.et1);
        num2   = findViewById(R.id.et2);
        add    = findViewById(R.id.b1);
        result = findViewById(R.id.tv3);

        FileInputStream fis;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder out = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                out.append(text);
            }
            num1.setText(out);
        } catch (IOException e) {
            e.printStackTrace();
        }



        add.setOnClickListener(v -> {
            try{
                long i = Integer.parseInt(num1.getText().toString());
                long j = Integer.parseInt(num2.getText().toString());
                long k = i + j;
                StringBuilder sb = new StringBuilder();
                sb.append(getString(R.string.mat7));
                sb.append(k);
                result.setText(sb);
                Toast.makeText(MainActivity.this, "Added in main : " + k , Toast.LENGTH_LONG).show();
                Intent it = new Intent(MainActivity.this, SideActivity.class);
                it.putExtra("pehla",num1.getText().toString());
                it.putExtra("doosra",num2.getText().toString());
                startActivity(it);
            } catch(NumberFormatException nfe){
                Toast.makeText(this,"Enter a Valid Number Please",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isLogged() {
        File file = new File(getApplicationContext().getFilesDir(),FILE_NAME);
        return file.exists();
    }

}