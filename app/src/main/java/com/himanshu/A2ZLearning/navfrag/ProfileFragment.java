package com.himanshu.a2zlearning.navfrag;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.himanshu.a2zlearning.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView pic;
    private final static String DATA = "UserData";
    private Button nameedit;
    private EditText nameval;
    private TextView emailval,mobval;
    private SharedPreferences sp;

    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        sp = Objects.requireNonNull(this.getActivity()).getSharedPreferences(DATA, Context.MODE_PRIVATE);
        nameval = view.findViewById(R.id.nameval);
        emailval = view.findViewById(R.id.emailval);
        mobval = view.findViewById(R.id.mobval);
        nameedit = view.findViewById(R.id.nameedit);
        Button changepass = view.findViewById(R.id.changepass);
        pic = view.findViewById(R.id.profilePic);
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
                    sp.edit().putString("UserName",nameval.getText().toString().trim()).apply();
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

                getFragmentManager().beginTransaction().replace(R.id.frame,new PasswordChanger()).commit();
            }
        });

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        return view;
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    private void setValues() {
        nameval.setBackground(null);
        nameval.setEnabled(false);
        nameval.setFocusable(false);
        nameval.setText(sp.getString("UserName","User 786"));
        emailval.setText(sp.getString("UserEmail","No Email"));
        mobval.setText(sp.getString("UserPhone","No Mobile Number"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {
            Uri mImage = data.getData();
            Picasso.get().load(mImage).into(pic);
        }
    }
}
