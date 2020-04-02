package com.example.questionbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class CppBasics extends AppCompatActivity {

    DrawerLayout d1;
    ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpp_basics);

        d1 = findViewById(R.id.d1);
        abdt = new ActionBarDrawerToggle(this,d1,R.string.CppJavaOpen,R.string.CppJavaClose);
        abdt.setDrawerIndicatorEnabled(true);
        d1.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(menuItem -> {
            int id =  menuItem.getItemId();
            if(id == R.id.profile) {
                Toast.makeText(this,"Profile",Toast.LENGTH_LONG).show();
            }
            else if(id == R.id.settings) {
                Toast.makeText(this,"Settings",Toast.LENGTH_LONG).show();
            }
            else if(id == R.id.devinfo) {
                Toast.makeText(this,"Developer Information",Toast.LENGTH_LONG).show();
            }
            return true;
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
