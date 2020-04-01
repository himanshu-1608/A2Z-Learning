package com.example.questionbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {

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

    private static final String FILE_NAME = "data.txt";
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!isLogged()) {
            Intent it = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(it);
        }

        lv = findViewById(R.id.lv1);

        String[] vals = {"JEE Mains","C++ Basics","LLM Entrance"};

        ArrayAdapter arrayadapter = new ArrayAdapter<>(this,R.layout.simple_list1, Arrays.asList(vals));

        lv.setAdapter(arrayadapter);

        lv.setOnItemClickListener((parent, view, position, id) -> {

            Toast.makeText(this,"Checked : " + vals[position] , Toast.LENGTH_LONG).show();
            Intent it = new Intent(this, SideActivity.class);
            startActivity(it);
        });
    }
    private boolean isLogged() {
        File file = new File(getApplicationContext().getFilesDir(),FILE_NAME);
        return file.exists();
    }

}