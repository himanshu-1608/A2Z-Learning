package com.himanshu.a2zlearning.navfrag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.himanshu.a2zlearning.handler.CourseDataHandler;
import com.himanshu.a2zlearning.R;


public class CoursesFragment extends Fragment {

    public CoursesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        LinearLayout c1 = view.findViewById(R.id.c1);
        LinearLayout c2 = view.findViewById(R.id.c2);
        LinearLayout c3 = view.findViewById(R.id.c3);

        final Bundle bundle = new Bundle();

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseDataHandler fragInfo = new CourseDataHandler(1);
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frame,fragInfo).commit();
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseDataHandler fragInfo = new CourseDataHandler(2);
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frame,fragInfo).commit();
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseDataHandler fragInfo = new CourseDataHandler(3);
                fragInfo.setArguments(bundle);
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frame,fragInfo).commit();
            }
        });
        return view;
    }
}
