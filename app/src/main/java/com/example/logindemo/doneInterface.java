package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class doneInterface extends AppCompatActivity {

    private static int SPLASH_TIMER = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_interface);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(doneInterface.this, ProfileActivity.class);
                startActivity(intent);
                finish();

            }
        }, SPLASH_TIMER);
    }
}