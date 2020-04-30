package com.himanshu.a2zlearning.login;

import androidx.annotation.NonNull;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.himanshu.a2zlearning.MainActivity;
import com.himanshu.a2zlearning.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class SignupActivity extends AppCompatActivity {

    private static final String DATA = "UserData";
    TextView tv5,tv7;
    Button sign;
    EditText name,mail,pass,cpass,mob;
    Button eye1,eye2;
    RadioButton male,female,other;
    RadioGroup rg;
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
        male = findViewById(R.id.rb1);
        female = findViewById(R.id.rb2);
        other = findViewById(R.id.rb3);
        rg = findViewById(R.id.rg);
        final Boolean[] e = {false,false};
        sp = getSharedPreferences(DATA,MODE_PRIVATE);

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
                final String userName = name.getText().toString().trim();
                final String emailID = mail.getText().toString().trim();
                final String one = pass.getText().toString().trim();
                final String two = cpass.getText().toString().trim();
                final String num = mob.getText().toString().trim();
                if(validate(userName,emailID,one,two,num)){
                    String Salute = "";
                    int select = rg.getCheckedRadioButtonId();
                    RadioButton locate = rg.findViewById(select);
                    String s = locate.getText().toString();
                    if ("Male".equals(s)) {
                        Salute = "Mr.";
                    } else if ("Female".equals(s)) {
                        Salute = "Ms.";
                    }

                    sp.edit().putString("UserName",userName).apply();
                    sp.edit().putString("Salute",Salute).apply();
                    sp.edit().putString("UserEmail",emailID).apply();
                    sp.edit().putString("UserPhone",num).apply();
                    sp.edit().putString("UserPassword",one).apply();
                    sp.edit().putBoolean("isLogged",true).apply();

                    Toast.makeText(SignupActivity.this,"Welcome , " + Salute + userName,Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignupActivity.this , MainActivity.class));
                    finish();
                }
            }
        });

    }
    private boolean validate(@NonNull String userName, @NonNull String emailID, @NonNull String one, @NonNull String two, @NonNull String num) {
        if(!isvalidName(userName)) {
            Toast.makeText(this,"Provide a valid username!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        if(!isvalidEmail(emailID)){
            Toast.makeText(this,"Enter valid Email Address!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        if(rg.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this,"Select a Gender!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        if((one.length() < 4) || (!isValidPassword(one))) {
            Toast.makeText(this,"Use a strong Password!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        if(two.isEmpty()) {
            Toast.makeText(this,"Confirm your Password!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        if(!two.equals(one)) {
            Toast.makeText(this,"Password and Confirm Password should be same!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        if(!isvalidPhoneNo(num)){
            Toast.makeText(this,"Enter valid phone no.!!!",Toast.LENGTH_LONG).show();
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
        boolean result = false;
        if (!userName.isEmpty()) {
            String regx = getString(R.string.sunp);
            Pattern pattern = compile(regx, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(userName);
            result = matcher.find();
        }
        return result;
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
        if(!email.matches(emailPatterning)) {
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
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
}
