package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class splashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 500;

    SharedPreferences  onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(splashScreen.this, LoginScreen.class);
                startActivity(intent);
                finish();

            }
        }, SPLASH_TIMER);
    }
}