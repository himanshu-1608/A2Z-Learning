package com.example.questionbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "passcode.txt";

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

        Intent aaya = getIntent();
        String one = aaya.getStringExtra("pehla");
        String two = aaya.getStringExtra("doosra");
        num1.setText(one);
        num2.setText(two);

        add.setOnClickListener(v -> {
            int i = Integer.parseInt(num1.getText().toString());
            int j = Integer.parseInt(num2.getText().toString());
            int k = i + j;
            result.setText("Result is : " + k);
            Toast.makeText(MainActivity.this, "Added in main : " + k , Toast.LENGTH_LONG).show();
            Intent it = new Intent(MainActivity.this, SideActivity.class);
            it.putExtra("pehla",num1.getText().toString());
            it.putExtra("doosra",num2.getText().toString());
            startActivity(it);
        });
    }

    private boolean isLogged() {
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text);
            }
            String no = "nanchang";
            if( sb.toString().equals(no) ){
                Intent it = new Intent(MainActivity.this , SignUpActivity.class);
                startActivity(it);
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            setInitial();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setInitial() {
        String text = "nanchang";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            System.out.println("Saved the password for first time : " + text);
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
            check();
        }
    }
}
