package com.example.questionbank;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordChanger extends AppCompatActivity {

    Button change;
    EditText oldp,p1,p2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_changer);
        Intent it = getIntent();
        String checker = it.getStringExtra("Passcode");
        change = findViewById(R.id.changer);
        oldp = findViewById(R.id.oldp);
        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);
        oldp.setHint("Enter old Password");
        p1.setHint("Enter new Password");
        p2.setHint("Re-Enter new Password");
        change.setText(R.string.passswordchangert1);
        change.setOnClickListener(v -> {
            String old = oldp.getText().toString().trim();
            String newp = p1.getText().toString().trim();
            String cp = p2.getText().toString().trim();
            if(old.equals(checker)){
                if(newp.equals(cp)) {
                    Toast.makeText(PasswordChanger.this,"Password Changed Successfully!!!",Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(PasswordChanger.this,"New Password doesn't match with confirmed password!!!",Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(PasswordChanger.this,"Current Password field is wrong!!!",Toast.LENGTH_LONG).show();
            }
        });
    }
}
