package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SecondActivity extends AppCompatActivity {

    private Button clickProfile, Logout, coupon, aboutus, viewRec;
    private String name;
    private TextView contactus, name2;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        clickProfile = findViewById(R.id.btnMyProfile);
        Logout = findViewById(R.id.btnLogout);
        coupon = findViewById(R.id.btnCoupon);
        aboutus = findViewById(R.id.btnAboutUs);
        contactus = findViewById(R.id.tvContactUs);
        viewRec = findViewById(R.id.btnViewReceipt);
        name2 = findViewById(R.id.tvNameD);

        firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("UserInfo").child(firebaseAuth.getUid());

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                name = snapshot.child("userName").getValue().toString();
                name2.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        ///////////////////LOGOUT///////////////////////////////////////

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();


                startActivity(new Intent(SecondActivity.this, LoginScreen.class));

            }
        });

        ////Direct buttons from SecondActivity to their own activities/////////////////////////////////////////////////

        clickProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, ProfileActivity.class));
            }
        });

        coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, CouponForm.class));
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, AboutUs.class));
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, ContactUs.class));
            }
        });

        viewRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, ViewReceipt.class));
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////


    }


}
