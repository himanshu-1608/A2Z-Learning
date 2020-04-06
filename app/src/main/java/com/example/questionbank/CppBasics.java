package com.example.questionbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class CppBasics extends AppCompatActivity {

    DrawerLayout d1;
    ActionBarDrawerToggle abdt;
    NavigationView nav_view;
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpp_basics);

        //SET DRAWER
        d1 = findViewById(R.id.d1);
        abdt = new ActionBarDrawerToggle(this,d1,R.string.CppJavaOpen,R.string.CppJavaClose);
        abdt.setDrawerIndicatorEnabled(true);
        d1.addDrawerListener(abdt);
        abdt.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(menuItem -> {
            int id =  menuItem.getItemId();

            switch(id) {
                case R.id.profile :
                    Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.settings :
                    Toast.makeText(this,"Settings",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.devinfo :
                    Toast.makeText(this,"Developer Page",Toast.LENGTH_SHORT).show();
                    break;
                default : return true;
            }
            return true;
        });
        b1 = findViewById(R.id.b13);
        b2 = findViewById(R.id.b23);
        b3 = findViewById(R.id.b33);
        b1.setOnClickListener(v -> {
            Intent i1 = new Intent(this,CppCodes.class);
            startActivity(i1);
        });
        b2.setOnClickListener(v -> {
            Intent i2 = new Intent(this,CppVideos.class);
            startActivity(i2);
        });
        b3.setOnClickListener(v -> {
            Intent i3 = new Intent(this,CppTests.class);
            startActivity(i3);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}