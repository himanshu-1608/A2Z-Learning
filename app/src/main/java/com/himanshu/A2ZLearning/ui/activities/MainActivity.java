package com.himanshu.a2zlearning.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.himanshu.a2zlearning.R;
import com.himanshu.a2zlearning.courseHandler.CourseDataHandler;
import com.himanshu.a2zlearning.courseHandler.CourseNotesHandler;
import com.himanshu.a2zlearning.courseHandler.CourseTestsHandler;
import com.himanshu.a2zlearning.courseHandler.CourseVideosHandler;
import com.himanshu.a2zlearning.login.LoginActivity;
import com.himanshu.a2zlearning.navfrag.CoursesFragment;
import com.himanshu.a2zlearning.navfrag.DevInfoFragment;
import com.himanshu.a2zlearning.navfrag.HelpSupportFragment;
import com.himanshu.a2zlearning.navfrag.HomeFragment;
import com.himanshu.a2zlearning.navfrag.PasswordChanger;
import com.himanshu.a2zlearning.navfrag.PerformanceFragment;
import com.himanshu.a2zlearning.navfrag.ProfileFragment;
import com.himanshu.a2zlearning.utils.Res;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    CoordinatorLayout coordinatorLayout;
    Toolbar toolbar;
    FrameLayout frame;
    NavigationView navView;
    ActionBarDrawerToggle abdt;
    ImageButton img1,img2;
    SharedPreferences sp,altsp;
    TextView headerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout        = findViewById(R.id.drawerLayout);
        coordinatorLayout   = findViewById(R.id.coordinatorLayout);
        toolbar             = findViewById(R.id.toolbar);
        frame               = findViewById(R.id.frame);
        navView             = findViewById(R.id.navView);
        img1                = findViewById(R.id.imgInsta);
        img2                = findViewById(R.id.imgFB);
        String DATAFILE = Res.sp1;
        sp = getSharedPreferences(DATAFILE,MODE_PRIVATE);
        altsp = getSharedPreferences(Res.sp2,MODE_PRIVATE);

        //setup toolbar
        setUpToolBar();

        //setup image links
        img1.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/himansh_1608/"))));

        img2.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/16.himanshu.08"))));

        //initial fragment setter
        fragmentSetter(new HomeFragment(),getResources().getString(R.string.app_name));
        navView.setCheckedItem(R.id.home);

        //set onclick for navigation menu
        navView.setNavigationItemSelectedListener(menuItem -> {
            assert getSupportActionBar() != null;
            switch(menuItem.getItemId()) {
                case R.id.home:
                    fragmentSetter(new HomeFragment(),getResources().getString(R.string.app_name));
                    break;
                case R.id.profile:
                    fragmentSetter(new ProfileFragment(),"My Profile");
                    break;
                case R.id.courses:
                    fragmentSetter(new CoursesFragment(),"Courses");
                    break;
                case R.id.perf_eval:
                    fragmentSetter(new PerformanceFragment(),"My Performance");
                    break;
                case R.id.devinfo:
                    fragmentSetter(new DevInfoFragment(),"About Us");
                    break;
                case R.id.helpsupport:
                    fragmentSetter(new HelpSupportFragment(),"Help & Support");
                    break;
                case R.id.logout:sendAlert();
                    return true;
            }
            drawerLayout.closeDrawers();
            return true;
        });
    }

    private void sendAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Log Out")
                .setCancelable(false)
                .setMessage("Are You sure want to log out?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    FirebaseAuth.getInstance().signOut();
                    sp.edit().putBoolean("isLogged",false).apply();
                    sp.edit().remove("UserName").apply();
                    sp.edit().remove("UserID").apply();
                    sp.edit().remove("UserPhone").apply();
                    sp.edit().remove("UserPassword").apply();
                    sp.edit().remove("UserEmail").apply();
                    sp.edit().remove("UserName").apply();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                })
                .setNegativeButton("Cancel",null);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void setUpToolBar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("A2Z Learning");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        abdt = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(abdt);
        abdt.syncState();
        View header = navView.getHeaderView(0);
        headerText = header.findViewById(R.id.headerText);
        headerText.setText(sp.getString("UserName",""));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void fragmentSetter(Fragment setFrag,String titleText) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(titleText);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame,setFrag)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        }
        else {
            Fragment frag = getSupportFragmentManager().findFragmentById(R.id.frame);
            assert frag!=null;
            if (frag instanceof HomeFragment) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Quit App")
                        .setCancelable(false)
                        .setMessage("Are you sure want to quit the app?")
                        .setPositiveButton("Ok", (dialog, which) -> finishAffinity())
                        .setNegativeButton("Cancel",null);
                AlertDialog alert = builder.create();
                alert.show();
            } else if(frag instanceof CoursesFragment || frag instanceof DevInfoFragment || frag instanceof HelpSupportFragment || frag instanceof PerformanceFragment || frag instanceof ProfileFragment) {
                fragmentSetter(new HomeFragment(),getResources().getString(R.string.app_name));
                navView.setCheckedItem(R.id.home);
            } else if(frag instanceof CourseDataHandler){
                fragmentSetter(new CoursesFragment(),"Courses");
            } else if(frag instanceof CourseNotesHandler || frag instanceof CourseVideosHandler || frag instanceof CourseTestsHandler) {
                CourseDataHandler fragInfo;
                switch(altsp.getInt("myMessage",1)) {
                    case 1 :
                        fragInfo = new CourseDataHandler(1);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragInfo).commit();
                        break;
                    case 2 :
                        fragInfo = new CourseDataHandler(2);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragInfo).commit();
                        break;
                    case 3 :
                        fragInfo = new CourseDataHandler(3);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragInfo).commit();
                        break;
                }
            } else if(frag instanceof PasswordChanger) {
                fragmentSetter(new ProfileFragment(),"My Profile");
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
        }
    }
}
