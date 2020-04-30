package com.himanshu.a2zlearning.navfrag;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.himanshu.a2zlearning.MainActivity;
import com.himanshu.a2zlearning.R;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    private final static String DATA = "UserData";
    Button nameedit,changepass;
    String passstring;
    TextView nameval,emailval,mobval;
    SharedPreferences sp;

    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        sp = Objects.requireNonNull(getContext()).getSharedPreferences(DATA, Context.MODE_PRIVATE);
        nameval = view.findViewById(R.id.nameval);
        emailval = view.findViewById(R.id.emailval);
        mobval = view.findViewById(R.id.mobval);
        nameedit = view.findViewById(R.id.nameedit);
        changepass = view.findViewById(R.id.changepass);
        setValues();
        final Boolean[] namer = {false};
        nameedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!namer[0]) {
                    nameval.setFocusableInTouchMode(true);
                    nameval.setEnabled(true);
                    namer[0] = true;
                    nameedit.setBackgroundResource(R.drawable.ic_check);
                } else {
                    nameval.setBackground(null);
                    nameval.setEnabled(false);
                    nameval.setFocusable(false);
                    nameedit.setBackgroundResource(R.drawable.ic_edit);
                    namer[0] = false;
                }
            }
        });

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                Objects.requireNonNull(((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setTitle("Change Password");
                getFragmentManager().beginTransaction().replace(R.id.frame,new PasswordChanger()).commit();
            }
        });

        return view;
    }

    private void setValues() {
        nameval.setBackground(null);
        nameval.setEnabled(false);
        nameval.setFocusable(false);
        nameval.setText(sp.getString("UserName","User 786"));
        emailval.setText(sp.getString("UserEmail","No Email"));
        mobval.setText(sp.getString("UserPhone","No Mobile Number"));
    }
}
