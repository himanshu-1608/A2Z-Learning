package com.himanshu.a2zlearning.navfrag;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.himanshu.a2zlearning.MainActivity;
import com.himanshu.a2zlearning.R;
import com.himanshu.a2zlearning.res.Res;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;


public class PasswordChanger extends Fragment {

    private final String DATA = Res.sp1;
    private EditText oldp,p1,p2;
    private SharedPreferences sp;
    private FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_password_changer, container, false);
        Button change = view.findViewById(R.id.changer);
        Objects.requireNonNull(((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setTitle("Change Password");
        oldp = view.findViewById(R.id.oldp);
        p1 = view.findViewById(R.id.p1);
        p2 = view.findViewById(R.id.p2);
        sp = Objects.requireNonNull(getContext()).getSharedPreferences(DATA, Context.MODE_PRIVATE);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        auth = FirebaseAuth.getInstance();
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checker = sp.getString("UserPassword","Null");
                String old = oldp.getText().toString().trim();
                final String newp = p1.getText().toString().trim();
                String cp = p2.getText().toString().trim();
                if(old.equals(checker)){
                    if(validate(newp)) {
                        if(newp.equals(cp)) {
                            auth.signInWithEmailAndPassword(sp.getString("UserEmail",""),old).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        assert user != null;
                                        user.updatePassword(newp).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()) {
                                                    sp.edit().putString("UserPassword",newp).apply();
                                                    Toast.makeText(getContext(),"Password Changed Successfully !!", Toast.LENGTH_LONG).show();
                                                    Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.frame,new ProfileFragment()).commit();
                                                } else {
                                                    Toast.makeText(getContext(),"Error in changing Password !"+task.getException(),Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                    } else {
                                        Toast.makeText(getContext(),"Error in process!!",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            p2.setError("New Password & Confirm Password Don't match");
                            p2.requestFocus();
                        }
                    }
                } else {
                    oldp.setError("Current Password is wrong.Use Reset Password by logging out if you don't remember your current password");
                    oldp.requestFocus();
                }
            }
        });

        return view;
    }

    private boolean validate(String newp) {
        if(!isValidPassword(newp)) {
            p1.setError("Create a strong password(Minimum Length 6)\nMinimum 1 Uppercase,1 Lowercase,1 Symbol,1 Digit");
            p1.requestFocus();
            return false;
        }
        return true;
    }

    private boolean isValidPassword(@NonNull String password) {
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN = getString(R.string.supp);
        pattern = compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
