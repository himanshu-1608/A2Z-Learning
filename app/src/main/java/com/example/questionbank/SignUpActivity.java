package com.example.questionbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import static java.util.regex.Pattern.compile;

public class SignUpActivity extends AppCompatActivity {

    private static final String DATA = "data.txt";
    TextView tv5,tv7;
    Button sign;
    EditText name,mail,pass,cpass,mob;
    CheckBox cb1,cb2;
    RadioButton male,female,other;
    RadioGroup rg;

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
        builder.setTitle("Quit App")
                .setCancelable(false)
                .setMessage("Are You Sure to quit the app?")
                .setPositiveButton("Ok",(dialog, which) -> {
                    finishAffinity();
                    System.out.println("Exiting the app!!!");
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
        mob = findViewById(R.id.et5);
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
            final String num = mob.getText().toString().trim();
            if(validate(userName,emailID,one,two,num)){
                System.out.println("Saving the signup details : ");


                FileOutputStream fos = null;


                String Salute = "";
                int select = rg.getCheckedRadioButtonId();
                RadioButton locate = rg.findViewById(select);
                switch (locate.getText().toString()) {
                    case "Male"  :   Salute = "Mr.";
                        break;
                    case "Female"  :   Salute = "Ms.";
                        break;
                    default     :   break;
                }

                String setter = "USERNAME:" +
                        userName +
                        "\n" +
                        "SALUTE:" +
                        Salute +
                        "\n" +
                        "EMAIL:" +
                        emailID +
                        "\n" +
                        "MOBILE:" +
                        num +
                        "\n" +
                        "PASSWORD:" +
                        one +
                        "\n";

                try {
                    fos = openFileOutput(DATA, MODE_PRIVATE);
                    fos.write(setter.getBytes());
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
    private boolean validate(@NonNull String userName,@NonNull String emailID,@NonNull String one,@NonNull String two,@NonNull String num) {
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
        //check for @domain.co.in or @domain.co.uk
        if (!email.matches(emailPattern) || (last.length() ==3 || last.length() == 4)) {// check @domain.nl or @domain.com or @domain.org
            return true;
        }
        else return email.matches(emailPatternnew) && last.length() == 6 && email.charAt(email.length() - 3) == '.';
    }
}



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