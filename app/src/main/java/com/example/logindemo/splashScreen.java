package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class splashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 1500;

    SharedPreferences  onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);

                boolean isFirst = onBoardingScreen.getBoolean("firstTime", true);

                if(isFirst){

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();
                    Intent intent = new Intent(splashScreen.this, onboarding.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(splashScreen.this, LoginScreen.class);
                    startActivity(intent);
                    finish();
                }


            }
        }, SPLASH_TIMER);
    }
}