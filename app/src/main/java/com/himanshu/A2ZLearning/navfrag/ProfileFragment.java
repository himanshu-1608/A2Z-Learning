package com.himanshu.a2zlearning.navfrag;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.himanshu.a2zlearning.R;
import com.himanshu.a2zlearning.UploadImage;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    private StorageReference mStorageRef;
    private DatabaseReference mDataBaseRef;
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

        mStorageRef = FirebaseStorage.getInstance().getReference("Uploads");
        mDataBaseRef = FirebaseDatabase.getInstance().getReference("Uploads");
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

    private String getFileExtension(Uri uri) {
        ContentResolver cr = Objects.requireNonNull(getActivity()).getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {
            Uri mImage = data.getData();
            sp.edit().putBoolean("hasPic",true).apply();

            StorageReference fileRef = mStorageRef.child("ProfilePic");
            fileRef.putFile(mImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            UploadImage upload = new UploadImage("ProfilePic",
                                    Objects.requireNonNull(Objects.requireNonNull(taskSnapshot.getMetadata()).getReference()).getDownloadUrl().toString());

                            String uploadId = mDataBaseRef.push().getKey();
                            assert uploadId != null;
                            mDataBaseRef.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),"Error in Adding File",Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
