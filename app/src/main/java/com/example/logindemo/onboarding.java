package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.slider.Slider;

public class onboarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;

    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button start;
    Animation animation;
    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dotss);
        start = findViewById(R.id.getStart);

        //call adapter
        sliderAdapter = new SliderAdapter(this);

        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(onboarding.this, LoginScreen.class));
            }
        });
    }

    public void skip(View view){
        startActivity(new Intent(onboarding.this, LoginScreen.class));
        finish();
    }

    public void next(View view){
        viewPager.setCurrentItem(currentPosition + 1);
    }

    private void addDots(int position) {

        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.parseColor("#ff7860"));

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.white_primary));
        }

    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPosition = position;

            if (position == 0) {
                start.setVisibility(View.INVISIBLE);
            } else if (position == 1) {
                start.setVisibility(View.INVISIBLE);
            } else {
                animation = AnimationUtils.loadAnimation(onboarding.this,R.anim.bottom_anim);
                start.setAnimation(animation);
                start.setVisibility(View.VISIBLE);
            }}

            @Override
            public void onPageScrollStateChanged ( int state){

            }
        }

        ;
    }