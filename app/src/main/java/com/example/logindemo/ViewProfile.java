package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewProfile extends AppCompatActivity {

    private ImageView profilepicture;
    private TextView profilename, profileemail;
    private Button changeusername;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        profilepicture = findViewById(R.id.img_profilepicture);
        profilename = findViewById(R.id.txt_username);
        profileemail = findViewById(R.id.txt_useremail);
        changeusername = findViewById(R.id.btn_changuserename);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}