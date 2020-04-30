package com.himanshu.a2zlearning.navfrag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.himanshu.a2zlearning.R;


public class HelpSupportFragment extends Fragment {

    public HelpSupportFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_support, container, false);

        TextView t1 = view.findViewById(R.id.t1);
        t1.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }
}
