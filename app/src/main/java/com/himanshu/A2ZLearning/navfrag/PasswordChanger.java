package com.himanshu.a2zlearning.navfrag;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.himanshu.a2zlearning.MainActivity;
import com.himanshu.a2zlearning.R;

import java.util.Objects;


public class PasswordChanger extends Fragment {

    private static final String DATA = "UserData";
    private EditText oldp,p1,p2;
    private SharedPreferences sp;

    public PasswordChanger() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_password_changer, container, false);
        Button change = view.findViewById(R.id.changer);
        oldp = view.findViewById(R.id.oldp);
        p1 = view.findViewById(R.id.p1);
        p2 = view.findViewById(R.id.p2);
        sp = Objects.requireNonNull(getContext()).getSharedPreferences(DATA, Context.MODE_PRIVATE);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checker = sp.getString("UserPassword","Null");
                String old = oldp.getText().toString().trim();
                String newp = p1.getText().toString().trim();
                String cp = p2.getText().toString().trim();
                if(old.equals(checker)){
                    if(newp.equals(cp)) {
                        Toast.makeText(getContext(),"Password Changed Successfully!!!",Toast.LENGTH_LONG).show();
                        Objects.requireNonNull(((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setTitle("My Profile");
                        assert getFragmentManager() != null;
                        getFragmentManager().beginTransaction().replace(R.id.frame,new ProfileFragment()).commit();
                    } else {
                        Toast.makeText(getContext(),"New Password doesn't match with confirmed password!!!",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(),"Input Correct current password!!!",Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
