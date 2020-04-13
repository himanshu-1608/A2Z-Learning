package com.example.questionbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class CoursesActivity extends AppCompatActivity {

    DrawerLayout d1;
    ActionBarDrawerToggle abdt;
    NavigationView nav_view;
    LinearLayout c1,c2,c3;

    @Override
    public void onBackPressed() {
        if(d1.isDrawerOpen(GravityCompat.START)){
            d1.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
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
                startActivity(new Intent(CoursesActivity.this,MyProfile.class));
            } else if(id == R.id.perf_eval) {
                startActivity(new Intent(CoursesActivity.this,PerformanceEvaluation.class));
            } else if(id == R.id.devinfo) {
                startActivity(new Intent(CoursesActivity.this,AboutUs.class));
            } else if(id == R.id.helpsupport) {
                startActivity(new Intent(CoursesActivity.this,HelpandSupport.class));
            } else if(id == R.id.faq) {
                startActivity(new Intent(CoursesActivity.this,Faq.class));
            }
            return true;
        });

        c1.setOnClickListener(v -> startActivity(new Intent(CoursesActivity.this,JeeMains.class)));
        c2.setOnClickListener(v -> startActivity(new Intent(CoursesActivity.this,CppBasics.class)));
        c3.setOnClickListener(v -> startActivity(new Intent(CoursesActivity.this,UpscGs.class)));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }


}
