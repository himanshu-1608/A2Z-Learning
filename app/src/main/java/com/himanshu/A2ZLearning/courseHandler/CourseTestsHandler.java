package com.himanshu.a2zlearning.courseHandler;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.himanshu.a2zlearning.ui.activities.MainActivity;
import com.himanshu.a2zlearning.R;
import com.himanshu.a2zlearning.testhandling.TestHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class CourseTestsHandler extends Fragment {

    private List<String> topics;
    private Map<String,List<String>> map;

    public CourseTestsHandler() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_tests_handler, container, false);

        assert this.getArguments() != null;
        Objects.requireNonNull(((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setTitle("Tests");
        ExpandableListView exp = view.findViewById(R.id.expanded_list);
        fillData();
        ExpandableListAdapter listAdapter = new CourseTestsAdapter(getContext(), topics, map);
        exp.setAdapter(listAdapter);

        exp.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                ImageView img = v.findViewById(R.id.imgParent);
                float rotate = img.getRotation();
                rotate += 180;
                if(rotate>=360) rotate-=360;
                img.setRotation(rotate);
                return false;
            }
        });
        final int courseID = this.getArguments().getInt("CourseID");
        final String[] strgrp1 = new String[]{"JEEFullTest1.txt","JEEFullTest2.txt","JEEFullTest3.txt", "JEEChemistryTest1.txt","JEEChemistryTest2.txt","JEEChemistryTest3.txt","JEEPhysicsTest1.txt","JEEPhysicsTest2.txt","JEEPhysicsTest3.txt"};
        final String[] strgrp2 = new String[]{"CppFullTest1.txt","CppFullTest2.txt","CppFullTest3.txt","CppDPTest1.txt","CppDPTest2.txt","CppDPTest3.txt","CppOOPSTest1.txt","CppOOPSTest2.txt","CppOOPSTest3.txt"};
        final String[] strgrp3 = new String[]{"UpscFullTest1.txt","UpscFullTest2.txt","UpscFullTest3.txt","UpscPolityTest1.txt","UpscPolityTest2.txt","UpscPolityTest3.txt","UpscEconomyTest1.txt","UpscEconomyTest2.txt","UpscEconomyTest3.txt"};
        exp.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String sentfile="";
                switch(courseID) {
                    case 1:
                        sentfile = strgrp1[(int) id];
                        break;
                    case 2:
                        sentfile = strgrp2[(int) id];
                        break;
                    case 3:
                        sentfile = strgrp3[(int) id];
                        break;
                }
                Intent it = new Intent(getActivity(), TestHandler.class);
                it.putExtra("filename",sentfile);
                startActivity(it);
                return false;
            }
        });
        return view;
    }

    private void fillData() {
        topics = new ArrayList<>();
        map = new HashMap<>();
        assert this.getArguments() != null;
        topics = this.getArguments().getStringArrayList("TopicList");
        List<String> tests = this.getArguments().getStringArrayList("TestList");
        assert tests != null;
        List<String> testA = new ArrayList<>();
        List<String> testB = new ArrayList<>();
        List<String> testC = new ArrayList<>();
        for(int i = 0;i < 3 ; i++ ) {
            testA.add(tests.get(i));
            testB.add(tests.get(i));
            testC.add(tests.get(i));
        }
        map.put(topics.get(0), testA);
        map.put(topics.get(1), testB);
        map.put(topics.get(2), testC);
    }

}
