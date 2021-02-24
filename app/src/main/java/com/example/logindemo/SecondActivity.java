package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.content.Intent;
import android.view.View;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    private Button clickProfile, Logout, bookingForm;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        clickProfile = findViewById(R.id.btnMyProfile);
        Logout = findViewById(R.id.btnLogout);
        bookingForm = findViewById(R.id.btnBookingForm);

        firebaseAuth = FirebaseAuth.getInstance();

        ///////////////////LOGOUT///////////////////////////////////////

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(SecondActivity.this, MainActivity.class));
            }
        });

        ////Direct buttons from SecondActivity to their own activities//////////////////////

        clickProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, ProfileActivity.class));
            }
        });

        bookingForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, BookingForm.class));
            }
        });
    }

    /////LOGOUT FUNCTION/////////////////////////////////////////////////

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
    }




}
