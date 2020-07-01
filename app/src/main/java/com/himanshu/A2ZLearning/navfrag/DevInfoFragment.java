package com.himanshu.a2zlearning.navfrag;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.himanshu.a2zlearning.R;


public class DevInfoFragment extends Fragment {

    public DevInfoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dev_info, container, false);

        TextView git = view.findViewById(R.id.github);
        TextView gmail = view.findViewById(R.id.gmail);
        TextView suggest = view.findViewById(R.id.suggest);

        git.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/himanshu-1608/A2Z-Learning"))));

        gmail.setMovementMethod(LinkMovementMethod.getInstance());
        suggest.setMovementMethod(LinkMovementMethod.getInstance());
        return view;
    }
}
