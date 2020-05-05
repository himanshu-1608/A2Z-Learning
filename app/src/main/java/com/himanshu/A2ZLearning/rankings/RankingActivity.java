package com.himanshu.a2zlearning.rankings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.himanshu.a2zlearning.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankingActivity extends AppCompatActivity {

    RecyclerView listRanks;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter rankAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        listRanks = findViewById(R.id.listRanks);
        List<basiclist> rankList = new ArrayList<>();
        rankList.add(new basiclist(10,"Check_best@1"));
        rankList.add(new basiclist(20,"Check_best@2"));
        rankList.add(new basiclist(20,"Check_best@3"));
        rankList.add(new basiclist(30,"Check_best@4"));
        rankList.add(new basiclist(40,"Check_best@5"));
        rankList.add(new basiclist(50,"Check_best@6"));
        Collections.sort(rankList, new Comparator<basiclist>() {
            @Override
            public int compare(basiclist o1, basiclist o2) {
                return o2.getMarks() - o1.getMarks();
            }
        });
        layoutManager = new LinearLayoutManager(this);
        rankAdapter = new rankAdapter(this,rankList);
        listRanks.setLayoutManager(layoutManager);
        listRanks.setAdapter(rankAdapter);

    }
}
