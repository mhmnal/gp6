package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {

    private Button clickProfile, Logout, coupon,aboutus;
    private TextView contactus;
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
                startActivity(new Intent(SecondActivity.this, ScrollableBookingForm.class));
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
        /////////////////////////////////////////////////////////////////////////////////////////////////


    }

    /////LOGOUT FUNCTION/////////////////////////////////////////////////

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
    }




}
