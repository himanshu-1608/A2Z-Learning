package com.himanshu.a2zlearning.handler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.himanshu.a2zlearning.MainActivity;
import com.himanshu.a2zlearning.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CourseNotesHandler extends Fragment {

    private List<String> topics;
    private Map<String,List<String>> map;

    public CourseNotesHandler() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_notes_handler, container, false);

        Objects.requireNonNull(((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setTitle("Study Material");
        ExpandableListView exp = view.findViewById(R.id.expanded_list);
        fillData();
        ExpandableListAdapter listAdapter = new CourseNotesAdapter(getContext(), topics, map);
        exp.setAdapter(listAdapter);

        return view;
    }

    private void fillData() {
        topics = new ArrayList<>();
        map = new HashMap<>();
        assert this.getArguments() != null;
        topics = this.getArguments().getStringArrayList("TopicList");
        List<String> files = this.getArguments().getStringArrayList("FileList");
        assert files != null;
        String s1 = setScroll(files.get(0));
        String s2 = setScroll(files.get(1));
        map.put(topics.get(0), Collections.singletonList(s1));
        map.put(topics.get(1), Collections.singletonList(s2));
    }

    private String setScroll(String fileName) {
        StringBuilder text = new StringBuilder();
        BufferedReader reader = null;
        try {
            assert Objects.requireNonNull(getActivity()).getAssets()!=null;
            reader = new BufferedReader(new InputStreamReader(getActivity().getAssets().open(fileName)));
            String mLine;
            while((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append("\n");

            }
        } catch (IOException e) {
            Toast.makeText(getContext(),"Error reading file !", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return text.toString();
    }

}
