package com.example.logindemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class carAdapter extends ArrayAdapter<brandItem> {

    public carAdapter(Context context, ArrayList<brandItem> carList) {
        super(context, 0, carList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.car, parent, false
            );
        }

        TextView textViewName = convertView.findViewById(R.id.carxml);
        brandItem currentItem = getItem(position);

        if (currentItem != null) {
            textViewName.setText(currentItem.getCar());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }
}
