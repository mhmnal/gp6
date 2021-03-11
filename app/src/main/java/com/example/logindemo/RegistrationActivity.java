package com.example.logindemo;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.IOException;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    //"(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 4 characters
                    "$");

    //Variables////////////////////////////////////////////////////////////////////
    private EditText userName, userPassword, userEmail;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private ImageView userProfilePic;
    String email, name, password;
    private FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
    private StorageReference storageReference;


    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null) {
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                userProfilePic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    //To remove action and status bar///////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        //Integrate firebase shets////////////////////////////////////////////////

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        /////////////////////////////////////////////////////////////////


        userProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select image"), PICK_IMAGE);
            }
        });

        ///////////////////////////////////////////////////////////////////

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate() &&  validatePassword()) {
                    //Upload data to database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                sendEmailVerification();
                            } else {
                                Toast.makeText(RegistrationActivity.this, "Registration Fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void setupUIViews() {
        userName = findViewById(R.id.et_username);
        userPassword = findViewById(R.id.et_password);
        userEmail = findViewById(R.id.et_email);
        regButton = findViewById(R.id.btn_register);
        userLogin = findViewById(R.id.txt_alrlogin);
        userProfilePic = findViewById(R.id.ivProfilePic);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    private Boolean validate() {
        Boolean result = false;
        name = userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty() || imagePath == null) {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void sendEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        sendUserData();
                        Toast.makeText(RegistrationActivity.this, "SUCCESSFULLY REGISTWERED, VERIFICATION MAIL SENT", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    } else
                        Toast.makeText(RegistrationActivity.this, "Verification main has not been sent", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("UserInfo").child(firebaseAuth.getUid());

        //IMAGES DATABASE///////////////////////////////////////////////////////////////////////////////////////////////////////
        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");
        UploadTask uploadTask = imageReference.putFile(imagePath);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistrationActivity.this, "Upload failed", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(RegistrationActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        UserProfile userProfile = new UserProfile(email, name);
        myRef.setValue(userProfile);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean validatePassword(){
            String passwordInput = userPassword.getEditableText().toString().trim();

        if(!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            userPassword.setError("Password too weak");
            return false;

        }else {

            userPassword.setError(null);
            return true;
        }
    }
}