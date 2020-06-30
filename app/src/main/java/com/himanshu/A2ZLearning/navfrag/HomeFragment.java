package com.himanshu.a2zlearning.navfrag;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.himanshu.a2zlearning.R;
import com.himanshu.a2zlearning.res.Res;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private final String DATA = Res.sp1;
    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView name = view.findViewById(R.id.name);
        SharedPreferences sp = Objects.requireNonNull(getContext()).getSharedPreferences(DATA, Context.MODE_PRIVATE);
        name.setText("Hey " + sp.getString("UserName","User") + "!");
        return view;
    }
}
