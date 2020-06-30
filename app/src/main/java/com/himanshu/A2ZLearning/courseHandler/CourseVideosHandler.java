package com.himanshu.a2zlearning.courseHandler;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.himanshu.a2zlearning.ui.activities.MainActivity;
import com.himanshu.a2zlearning.R;

import java.util.ArrayList;
import java.util.Objects;


public class CourseVideosHandler extends Fragment {

    public CourseVideosHandler() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_course_videos_handler, container, false);

        Objects.requireNonNull(((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setTitle("Online Videos");
        assert getArguments() != null;
        ArrayList<String> texts = this.getArguments().getStringArrayList("TextList");
        final ArrayList<String> links = getArguments().getStringArrayList("LinkList");
        assert texts != null;
        assert links != null;
        Button b1 = view.findViewById(R.id.b11);
        Button b2 = view.findViewById(R.id.b12);
        Button b3 = view.findViewById(R.id.b13);
        Button b4 = view.findViewById(R.id.b14);
        TextView sourceText = view.findViewById(R.id.sourceText);

        final Button[] bgrp = new Button[]{b1, b2, b3, b4};

        sourceText.setText(texts.get(0));
        for(int i = 0 ; i < bgrp.length ; i++) {
            bgrp[i].setText(texts.get(i+1));
        }

        for(int i = 0 ; i < bgrp.length ; i++) {
            final int finalI = i;
            bgrp[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+ links.get(finalI))));
                }
            });
        }

        return view;
    }
}
