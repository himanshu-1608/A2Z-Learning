package com.himanshu.a2zlearning.navfrag;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.himanshu.a2zlearning.InternetCheck;
import com.himanshu.a2zlearning.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

public class ProfileFragment extends Fragment {

    private DatabaseReference mDatabaseRef;
    private StorageReference storeDp;
    private StorageReference storeAll;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView pic;
    private ProgressBar profileBar;
    private final static String DATA = "UserData";
    private EditText txtName,txtEmail,txtPhone;
    private SharedPreferences sp;
    private final Boolean[] changer = {false,false,false};
    private ImageButton btnName,btnEmail,btnPhone;
    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        sp = Objects.requireNonNull(this.getActivity()).getSharedPreferences(DATA, Context.MODE_PRIVATE);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("UserData").child(sp.getString("UserID","Null"));
        StorageReference storeBase = FirebaseStorage.getInstance().getReference().child("Uploads").child(sp.getString("UserID", "Null")).child("ProfilePics");
        storeDp = storeBase.child("Current");
        storeAll = storeBase.child("All");

        pic = view.findViewById(R.id.profilePic);
        profileBar = view.findViewById(R.id.profileBar);

        txtName = view.findViewById(R.id.txtNameEdit);
        txtEmail = view.findViewById(R.id.txtEmailEdit);
        txtPhone = view.findViewById(R.id.txtPhoneEdit);

        btnName = view.findViewById(R.id.btnNameEdit);
        btnEmail = view.findViewById(R.id.btnEmailEdit);
        btnPhone = view.findViewById(R.id.btnPhoneEdit);

        Button changepass = view.findViewById(R.id.changepass);
        Button changeImage = view.findViewById(R.id.changeImage);

        setValues();

        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!changer[0]) {
                    txtName.setFocusableInTouchMode(true);
                    txtName.setEnabled(true);
                    changer[0] = true;
                    btnName.setImageDrawable(Objects.requireNonNull(getActivity()).getDrawable(R.drawable.ic_check));
                } else {
                    changeName();
                }
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!changer[1]) {
                    txtEmail.setFocusableInTouchMode(true);
                    txtEmail.setEnabled(true);
                    changer[1] = true;
                    btnEmail.setImageDrawable(Objects.requireNonNull(getActivity()).getDrawable(R.drawable.ic_check));
                } else {
                    changeEmail();
                }
            }
        });

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!changer[2]) {
                    txtPhone.setFocusableInTouchMode(true);
                    txtPhone.setEnabled(true);
                    changer[2] = true;
                    btnPhone.setImageDrawable(Objects.requireNonNull(getActivity()).getDrawable(R.drawable.ic_check));
                } else {
                    changePhone();
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

        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        return view;
    }

    private void changeName() {
        new InternetCheck(new InternetCheck.Consumer() {
            @Override
            public void accept(Boolean internet) {
                if(internet) {
                    sp.edit().putString("UserName",txtName.getText().toString().trim()).apply();
                    mDatabaseRef.child("UserName").setValue(txtName.getText().toString().trim());
                    txtName.setBackground(null);
                    txtName.setEnabled(false);
                    txtName.setFocusable(false);
                    btnName.setImageDrawable(Objects.requireNonNull(getActivity()).getDrawable(R.drawable.ic_edit));
                    changer[0] = false;
                } else {
                    Toast.makeText(getContext(),"Allow Internet to sync",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void changeEmail() {
        new InternetCheck(new InternetCheck.Consumer() {
            @Override
            public void accept(Boolean internet) {
                if(internet) {
                    sp.edit().putString("UserEmail",txtEmail.getText().toString().trim()).apply();
                    mDatabaseRef.child("UserEmail").setValue(txtEmail.getText().toString().trim());
                    txtEmail.setBackground(null);
                    txtEmail.setEnabled(false);
                    txtEmail.setFocusable(false);
                    btnEmail.setImageDrawable(Objects.requireNonNull(getActivity()).getDrawable(R.drawable.ic_edit));
                    changer[1] = false;
                } else {
                    Toast.makeText(getContext(),"Allow Internet to sync",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void changePhone() {
        new InternetCheck(new InternetCheck.Consumer() {
            @Override
            public void accept(Boolean internet) {
                if(internet) {
                    sp.edit().putString("UserPhone",txtPhone.getText().toString().trim()).apply();
                    mDatabaseRef.child("UserPhone").setValue(txtPhone.getText().toString().trim());
                    txtPhone.setBackground(null);
                    txtPhone.setEnabled(false);
                    txtPhone.setFocusable(false);
                    btnPhone.setImageDrawable(Objects.requireNonNull(getActivity()).getDrawable(R.drawable.ic_edit));
                    changer[2] = false;
                } else {
                    Toast.makeText(getContext(),"Allow Internet to sync",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    private void setValues() {
        txtName.setBackground(null);
        txtName.setEnabled(false);
        txtName.setFocusable(false);
        txtName.setText(sp.getString("UserName","User 786"));
        txtEmail.setBackground(null);
        txtEmail.setEnabled(false);
        txtEmail.setFocusable(false);
        txtEmail.setText(sp.getString("UserEmail","No Email"));
        txtPhone.setBackground(null);
        txtPhone.setEnabled(false);
        txtPhone.setFocusable(false);
        txtPhone.setText(sp.getString("UserPhone","No Mobile No."));
        if(sp.getBoolean("hasPic",true)) {
            new InternetCheck(new InternetCheck.Consumer() {
                @Override
                public void accept(Boolean internet) {
                    if(internet) {
                        storeDp.child("ProfilePic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Glide.with(Objects.requireNonNull(getContext()))
                                        .load(uri)
                                        .listener(new RequestListener<Drawable>() {
                                            @Override
                                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                profileBar.setVisibility(View.GONE);
                                                return false;
                                            }

                                            @Override
                                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                                profileBar.setVisibility(View.GONE);
                                                return false;
                                            }
                                        })
                                        .into(pic);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                pic.setImageResource(R.drawable.ic_dark_profile);
                                Toast.makeText(getContext(),"Failed to load image. Check internet connection!",Toast.LENGTH_LONG).show();
                                profileBar.setVisibility(View.GONE);
                            }
                        });
                    } else {
                        profileBar.setVisibility(View.GONE);
                        pic.setImageResource(R.drawable.ic_dark_profile);
                        Toast.makeText(getContext(),"Failed to load image. Check internet connection!",Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            Toast.makeText(getContext(),"Done Default Loading",Toast.LENGTH_SHORT).show();
            pic.setImageResource(R.drawable.ic_dark_profile);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {

            final Uri mImage = data.getData();

            new InternetCheck(new InternetCheck.Consumer() {
                @Override
                public void accept(Boolean internet) throws IOException {
                    if(internet) {
                        sp.edit().putBoolean("hasPic",true).apply();
                        Bitmap bmp;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            bmp = ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireContext().getContentResolver(), mImage));
                        } else {
                            bmp = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), mImage);
                        }
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bmp.compress(Bitmap.CompressFormat.JPEG, 15, baos);
                        byte[] databytes = baos.toByteArray();

                        //uploading the image
                        StorageReference fileRef = storeDp.child("ProfilePic");
                        UploadTask uploadTask = fileRef.putBytes(databytes);
                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getContext(),"ADDED online",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(),"Error in Adding File",Toast.LENGTH_SHORT).show();
                            }
                        });

                        Glide.with(Objects.requireNonNull(getContext()))
                                .load(mImage)
                                .into(pic);

                        storeAll.child(""+System.currentTimeMillis()+"."+getFileExtension(mImage)).putBytes(databytes);
                    } else {
                        Toast.makeText(getContext(),"Allow Internet to sync",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cr = Objects.requireNonNull(getActivity()).getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
}
