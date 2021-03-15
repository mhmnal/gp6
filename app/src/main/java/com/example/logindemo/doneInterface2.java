package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class doneInterface2 extends AppCompatActivity {

    private static int SPLASH_TIMER = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_interface2);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(doneInterface2.this, SecondActivity.class);
                    startActivity(intent);
                    finish();

                }
            }, SPLASH_TIMER);
        }
    }
