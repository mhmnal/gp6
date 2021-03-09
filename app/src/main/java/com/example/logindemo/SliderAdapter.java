package com.example.logindemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int images[] = {

            R.drawable.prof,
            R.drawable.sit,
            R.drawable.welcome,
    };

    int headings[] = {

            R.string.onboard1,
            R.string.onboard2,
            R.string.onboard3,
    };

    int desc[] ={

            R.string.ponborad1,
            R.string.ponborad2,
            R.string.ponborad3,
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout,container,false);

        //Hooks
        ImageView imageView = view.findViewById(R.id.sliderImg);
        TextView heading = view.findViewById(R.id.sliderHeading);
        TextView descr = view.findViewById(R.id.sliderDesc);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        descr.setText(desc[position]);

        container.addView(view);

        return view;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
