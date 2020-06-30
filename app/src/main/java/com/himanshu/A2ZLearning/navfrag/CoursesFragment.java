package com.himanshu.a2zlearning.navfrag;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.himanshu.a2zlearning.courseHandler.CourseDataHandler;
import com.himanshu.a2zlearning.R;
import com.himanshu.a2zlearning.res.Res;

import java.util.Objects;


public class CoursesFragment extends Fragment {

    public CoursesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);
        final SharedPreferences sp = Objects.requireNonNull(getActivity()).getSharedPreferences(Res.sp2, Context.MODE_PRIVATE);
        LinearLayout c1 = view.findViewById(R.id.c1);
        LinearLayout c2 = view.findViewById(R.id.c2);
        LinearLayout c3 = view.findViewById(R.id.c3);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseDataHandler fragInfo = new CourseDataHandler(1);
                sp.edit().putInt("myMessage",1).apply();
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragInfo).commit();
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseDataHandler fragInfo = new CourseDataHandler(2);
                sp.edit().putInt("myMessage",2).apply();
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragInfo).commit();
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseDataHandler fragInfo = new CourseDataHandler(3);
                sp.edit().putInt("myMessage",3).apply();
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragInfo).commit();
            }
        });
        return view;
    }
}
