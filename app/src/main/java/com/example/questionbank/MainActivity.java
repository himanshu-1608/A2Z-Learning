package com.example.questionbank;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {

        if(d1.isDrawerOpen(GravityCompat.START)){
            d1.closeDrawer(GravityCompat.START);
        }else {
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
    }

    private static final String FILE_NAME = "data.txt";
    DrawerLayout d1;
    ActionBarDrawerToggle abdt;
    NavigationView nav_view;
    TextView namevala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (!isLogged()) {
            Intent it = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(it);
        }

        namevala = findViewById(R.id.name);
        nav_view = findViewById(R.id.nav_view);
        d1 = findViewById(R.id.d1);
        abdt = new ActionBarDrawerToggle(this,d1,R.string.CppJavaOpen,R.string.CppJavaClose);
        abdt.setDrawerIndicatorEnabled(true);
        d1.addDrawerListener(abdt);
        abdt.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        nav_view.setNavigationItemSelectedListener(menuItem -> {
            int id =  menuItem.getItemId();
            d1.closeDrawer(GravityCompat.START);
            if(id == R.id.profile) {
                startActivity(new Intent(MainActivity.this,MyProfile.class));
            } else if(id == R.id.courses) {
                startActivity(new Intent(MainActivity.this,CoursesActivity.class));
            } else if(id == R.id.perf_eval) {
                startActivity(new Intent(MainActivity.this,PerformanceEvaluation.class));
            } else if(id == R.id.devinfo) {
                startActivity(new Intent(MainActivity.this,AboutUs.class));
            } else if(id == R.id.helpsupport) {
                startActivity(new Intent(MainActivity.this,HelpandSupport.class));
            } else if(id == R.id.faq) {
                startActivity(new Intent(MainActivity.this,Faq.class));
            }
            return true;
        });

        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text);
                sb.append("qqAB");
            }
            StringBuilder out;
            out = sb;
            int lastind = out.indexOf("qqAB");
            String namestring = out.substring(9, lastind);
            namevala.setText("Heyyy " + namestring + "!");
        } catch (IOException e) {
            System.out.println("Error Reading File");
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }









    }

    private boolean isLogged() {
        File file = new File(getApplicationContext().getFilesDir(),FILE_NAME);
        return file.exists();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

}