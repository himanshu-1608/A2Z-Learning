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

public class UpscGs extends AppCompatActivity {

    DrawerLayout d1;
    ActionBarDrawerToggle abdt;
    NavigationView nav_view;
    LinearLayout l1,l2,l3;

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
        setContentView(R.layout.activity_upsc_gs);

        //SET DRAWER
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
                startActivity(new Intent(UpscGs.this,MyProfile.class));
            } else if(id == R.id.courses) {
                super.onBackPressed();
            } else if(id == R.id.perf_eval) {
                startActivity(new Intent(UpscGs.this,PerformanceEvaluation.class));
            } else if(id == R.id.devinfo) {
                startActivity(new Intent(UpscGs.this,AboutUs.class));
            } else if(id == R.id.helpsupport) {
                startActivity(new Intent(UpscGs.this,HelpandSupport.class));
            } else if(id == R.id.faq) {
                startActivity(new Intent(UpscGs.this,Faq.class));
            }
            return true;
        });
        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
        l1.setOnClickListener(v -> {
            Intent i1 = new Intent(this,UpscCodes.class);
            startActivity(i1);
        });
        l2.setOnClickListener(v -> {
            Intent i2 = new Intent(this,UpscVideos.class);
            startActivity(i2);
        });
        l3.setOnClickListener(v -> {
            Intent i3 = new Intent(this,UpscTests.class);
            startActivity(i3);
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

}
