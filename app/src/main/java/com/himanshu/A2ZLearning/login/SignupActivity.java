package com.himanshu.a2zlearning.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.himanshu.a2zlearning.InternetCheck;
import com.himanshu.a2zlearning.MainActivity;
import com.himanshu.a2zlearning.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class SignupActivity extends AppCompatActivity {

    private static final String DATA = "UserData";
    TextView tv5,tv7;
    ProgressBar progressBar;
    Button sign;
    private FirebaseAuth auth;
    EditText name,mail,pass,cpass,mob;
    Button eye1,eye2;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tv5 = findViewById(R.id.tv5);
        tv7 = findViewById(R.id.tv7);
        sign = findViewById(R.id.subutt);
        name = findViewById(R.id.et1);
        mail = findViewById(R.id.et2);
        pass = findViewById(R.id.et3);
        cpass = findViewById(R.id.et4);
        mob = findViewById(R.id.et5);
        eye1 = findViewById(R.id.eye1);
        eye2 = findViewById(R.id.eye2);
        progressBar = findViewById(R.id.progressBar);
        final Boolean[] e = {false,false};
        sp = getSharedPreferences(DATA,MODE_PRIVATE);
        auth = FirebaseAuth.getInstance();

        eye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!e[0]) {
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eye1.setBackgroundResource(R.drawable.ic_not_see);
                    e[0] = true;
                } else {
                    eye1.setBackgroundResource(R.drawable.ic_passcheck);
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    e[0] = false;
                }
            }
        });

        eye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!e[1]) {
                    cpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eye2.setBackgroundResource(R.drawable.ic_not_see);
                    e[1] = true;
                } else {
                    eye2.setBackgroundResource(R.drawable.ic_passcheck);
                    cpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    e[1] = false;
                }
            }
        });

        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    tv5.setVisibility(View.VISIBLE);
                } else {
                    tv5.setVisibility(View.GONE);
                }
            }
        });

        cpass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    tv7.setVisibility(View.VISIBLE);
                } else {
                    tv7.setVisibility(View.GONE);
                }
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InternetCheck(new InternetCheck.Consumer() {
                    @Override
                    public void accept(Boolean internet) {
                        if(internet) {
                            setSignup();
                        } else {
                            Toast.makeText(SignupActivity.this,"Allow Internet",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    private boolean validate(@NonNull String userName, @NonNull String emailID, @NonNull String one, @NonNull String two, @NonNull String num) {
        if(!isvalidName(userName)) {
            name.setError("Enter Valid Username");
            name.requestFocus();
            return false;
        }
        if(!isvalidEmail(emailID)){
            mail.setError("Invalid Email");
            mail.requestFocus();
            return false;
        }
        if(!isvalidPhoneNo(num)){
            mob.setError("Invalid Number");
            mob.requestFocus();
            return false;
        }
        if((one.length() < 6) || (!isValidPassword(one))) {
            pass.setError("Set a strong password");
            pass.requestFocus();
            return false;
        }
        if(two.isEmpty()) {
            cpass.setError("Confirm your password");
            cpass.requestFocus();
            return false;
        }
        if(!two.equals(one)) {
            cpass.setError("Both passwords should be same");
            cpass.requestFocus();
            return false;
        }
        return true;
    }

    private boolean isvalidPhoneNo(@NonNull String num) {
        boolean ans = false;
        if(num.length()==10) {
            if(Pattern.matches(getString(R.string.sump),num)) {
                ans=true;
            }
        }
        return ans;
    }

    private boolean isvalidName(@NonNull String userName) {
        return !(userName.isEmpty());
    }

    private boolean isValidPassword(@NonNull String password) {
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN = getString(R.string.supp);
        pattern = compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean isvalidEmail(@NonNull String email) {
        if(TextUtils.isEmpty(email)) {
            return false;
        }
        String emailPatterning = getString(R.string.suep);
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        }
        if(!email.matches(emailPatterning)) {
            return false;
        }
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPatternnew = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";
        String domain = email.substring(email.indexOf('@'));
        String last   = domain.substring(domain.indexOf('.'));
        if (!email.matches(emailPattern) || (last.length() ==3 || last.length() == 4)) {
            return true;
        }
        else return email.matches(emailPatternnew) && last.length() == 6 && email.charAt(email.length() - 3) == '.';
    }

    private void setSignup() {
        final String userName = name.getText().toString().trim();
        final String emailID = mail.getText().toString().trim();
        final String one = pass.getText().toString().trim();
        final String two = cpass.getText().toString().trim();
        final String num = mob.getText().toString().trim();

        if(validate(userName,emailID,one,two,num)) {
            progressBar.setVisibility(View.VISIBLE);
            Query signupQuery = FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("UserName").equalTo(userName);

            signupQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getChildrenCount()>0) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getBaseContext(),"Username already taken",Toast.LENGTH_LONG).show();
                    } else {
                        auth.createUserWithEmailAndPassword(emailID,one).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(SignupActivity.this,"Sign Up Failed",Toast.LENGTH_LONG).show();
                                } else {
                                    String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
                                    final DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                                    Map<String,String> details = new HashMap<>();
                                    details.put("UserName",userName);
                                    details.put("UserEmail",emailID);
                                    details.put("UserPhone",num);
                                    current_user_db.setValue(details);
                                    final DatabaseReference mCount = FirebaseDatabase.getInstance().getReference().child("UserCount");
                                    mCount.runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long value = mutableData.getValue(Long.class);
                                            if (value == null) { mutableData.setValue(1); }
                                            else { mutableData.setValue(value + 1); }
                                            return Transaction.success(mutableData);
                                        }
                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) { }
                                    });
                                    sp.edit().putString("UserName",userName).apply();
                                    sp.edit().putString("UserID",userId).apply();
                                    sp.edit().putString("UserEmail",emailID).apply();
                                    sp.edit().putString("UserPhone",num).apply();
                                    sp.edit().putBoolean("hasPic",false).apply();
                                    sp.edit().putBoolean("isLogged",true).apply();
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(SignupActivity.this,"Welcome , " + userName,Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(SignupActivity.this , MainActivity.class));
                                    finish();
                                }
                            }
                        }).addOnFailureListener(SignupActivity.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(SignupActivity.this,"Sign Up Failed\n(Network or Email Issues)",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(SignupActivity.this,"Oops!! Some Database Error Occured",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
