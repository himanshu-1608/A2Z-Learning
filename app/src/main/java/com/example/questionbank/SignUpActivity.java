package com.example.questionbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import static com.example.questionbank.R.string.sut12;
//import static com.example.questionbank.R.string.sut13;
import static java.util.regex.Pattern.compile;

public class SignUpActivity extends AppCompatActivity {

    private static final String NAME = "username.txt";
    private static final String GENDER = "gender.txt";
    private static final String EMAIL = "email.txt";
    private static final String PASSCODE = "passcode.txt";
    TextView tv5,tv7;
    Button sign;
    EditText name,mail,pass,cpass;
    CheckBox cb1,cb2;
    RadioButton male,female,other;
    RadioGroup rg;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
        builder.setTitle("Quit App")
                .setMessage("Are You Sure to quit the app?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                        System.out.println("Done");
                    }
                })
                .setNegativeButton("Cancel",null);
        AlertDialog alert = builder.create();
        alert.show();
    }

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
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        male = findViewById(R.id.rb1);
        female = findViewById(R.id.rb2);
        other = findViewById(R.id.rb3);
        rg = findViewById(R.id.rg);



        cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        cb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                cpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                cpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        pass.setOnFocusChangeListener(  (v, hasFocus) -> tv5.setVisibility(View.VISIBLE)  );

        cpass.setOnFocusChangeListener( (v, hasFocus) -> tv7.setVisibility(View.VISIBLE) );

        sign.setOnClickListener((v)->{
            final String userName = name.getText().toString().trim();
            final String emailID = mail.getText().toString().trim();
            final String one = pass.getText().toString().trim();
            final String two = cpass.getText().toString().trim();
            if(validate(userName,emailID,one,two)){
                System.out.println("Saving the signup details : ");
                String Salute = "";
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(NAME, MODE_PRIVATE);
                    fos.write(userName.getBytes());
                    System.out.println("Username : " + userName);
                } catch (IOException io) {
                    io.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException io) {
                            io.printStackTrace();
                        }
                    }
                }
                fos = null;
                try {
                    fos = openFileOutput(GENDER, MODE_PRIVATE);
                    int select = rg.getCheckedRadioButtonId();
                    RadioButton locate = rg.findViewById(select);
                    switch (locate.getText().toString()) {
                        case "Male"  :   Salute = "Mr.";
                                        break;
                        case "Female"  :   Salute = "Ms.";
                                        break;
                        default     :   break;
                    }
                    fos.write(Salute.getBytes());
                    System.out.println("Salute : " + Salute);
                } catch (IOException io) {
                    io.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException io) {
                            io.printStackTrace();
                        }
                    }
                }
                fos = null;
                try {
                    fos = openFileOutput(EMAIL, MODE_PRIVATE);
                    fos.write(emailID.getBytes());
                    System.out.println("Email ID : " + emailID);
                } catch (IOException io) {
                    io.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException io) {
                            io.printStackTrace();
                        }
                    }
                }
                fos = null;
                try {
                    fos = openFileOutput(PASSCODE, MODE_PRIVATE);
                    fos.write(one.getBytes());
                    System.out.println("Passcode : " + one);
                } catch (IOException io) {
                    io.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException io) {
                            io.printStackTrace();
                        }
                    }
                }
                Toast.makeText(this,"Welcome , " + Salute + userName,Toast.LENGTH_LONG).show();
                Intent it = new Intent(SignUpActivity.this , MainActivity.class);
                startActivity(it);
            }
        });
    }
    private boolean validate(@NonNull String userName,@NonNull String emailID,@NonNull String one,@NonNull String two) {
        if(userName.isEmpty()) {
            Toast.makeText(this,"Provide a username!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        if(TextUtils.isEmpty(emailID)) {
            Toast.makeText(this,"Provide valid Email Address!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        String emailPattern = getString(R.string.suep);
        if(!emailID.matches(emailPattern)) {
            Toast.makeText(this,"Enter valid Email Address!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailID).matches()) {
            Toast.makeText(this,"Enter valid Email Address!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        if(rg.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this,"Select a Gender!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        if((one.length() < 8) || (!isValidPassword(one))) {
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
        return true;
    }
    public boolean isValidPassword(String password) {
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN = getString(R.string.supp);
        pattern = compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
//    private boolean validEmail(String email)
//    {
//        // TODO Auto-generated method stub
//        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//        String emailPatternnew = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";
//        String domain = email.substring(email.indexOf('@'), email.length());
//        String last = domain.substring(domain.indexOf('.'),domain.length());
//        if (email.matches(emailPattern) && (last.length() ==3 || last.length() == 4)) // check @domain.nl or @domain.com or @domain.org
//        {
//            return true;
//        }
//        else if(email.matches(emailPatternnew) && last.length() == 6 && email.charAt(email.length()-3)== '.') //check for @domain.co.in or @domain.co.uk
//        {
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }













//set dialog message
//        alertDialogBuilder
//        .setMessage("your message")
//        .setCancelable(false)
//        .setPositiveButton("YES"),
//        new DialogInterface.OnClickListener() {
//public void onClick(DialogInterface dialog,
//        int id) {
//        // what to do if YES is tapped
//        finishAffinity();
//        System.exit(0);
//        }
//        })
//
//        .setNeutralButton("CANCEL"),
//        new DialogInterface.OnClickListener() {
//public void onClick(DialogInterface dialog,
//        int id) {
//        // code to do on CANCEL tapped
//        dialog.cancel();
//        }
//        })
//
//        .setNegativeButton("NO"),
//        new DialogInterface.OnClickListener() {
//public void onClick(DialogInterface dialog,
//        int id) {
//        // code to do on NO tapped
//        dialog.cancel();
//        }
//        });
//
//        AlertDialog alertDialog = alertDialogBuilder.create();
//
//        alertDialog.show();