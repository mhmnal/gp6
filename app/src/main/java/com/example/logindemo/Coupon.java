/*
package com.example.logindemo;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Coupon extends AppCompatActivity implements DatePickerDialog.OnDateSetListener , TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {

    public static final String CAL_RECEIPT ="com.example.logindemo.CAL_RECEIPT";
    public static final String CAL_TOD ="com.example.logindemo.CAL_TOD";
    public static final String CAL_HOURS ="com.example.logindemo.CAL_HOURS";
    TextView Date,TimeDay,Hourstv;
    public String currentDateString,timeofDay,clickedJamList,RecCal,RecTod,RecHours;
    Button Confirm,button,buttons;
    private DatabaseReference reference;
    private ArrayList<spinItem> mJamList;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_coupon);

            //DATE/////////////////////////////////////////////////////////////////////
            Calendar calendar = Calendar.getInstance();
            String currentDate = DateFormat.getDateInstance().format(calendar.getTime());


            //VARIABLES//////////////////////////////////////////////////////////////
            Confirm = findViewById(R.id.btnConfirm);
            TextView textView = findViewById(R.id.tvDate2);
            textView.setText(currentDate);
            button = findViewById(R.id.btnCalendar);
            buttons = findViewById(R.id.btnTimeSelect);
            Spinner spinnerJam = findViewById(R.id.btnTime);
            Hourstv = findViewById(R.id.tvHours2);

            ///////////FIREBASE GET INSTANCE/////////////////////////////////////
            FirebaseDatabase.getInstance();

            ////////////////////////DATE PICKER////////////////////////////////////
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment datePicker = new DatePickerFragment();
                    datePicker.show(getSupportFragmentManager(),"date picker");
                }
            });

            //////////////////////////////////////SPINNER HOURS////////////////////
            initList();
            spinAdapter mAdapter = new spinAdapter(this, mJamList);
            spinnerJam.setAdapter(mAdapter);

            spinnerJam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    spinItem clickedItem = (spinItem)parent.getItemAtPosition(position);
                    clickedJamList = clickedItem.getJam();
                    Hourstv.setText(clickedJamList);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ////////////////////TIME PICKER///////////////////////////////////////////////////////////////////////////////////
            buttons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment timePicker = new TimePickerDialogFragment();
                    timePicker.show(getSupportFragmentManager(),"time picker");
                }
            });


            ///////////////////////CONFIRM BUTTON CLICK//////////////////////////////////////////////////////////////
           Confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    reference = FirebaseDatabase.getInstance().getReference("Coupon").child(currentDateString);
                    //GET ALL THE VALUES
                    CouponInfo couponInfo = new CouponInfo();
                    couponInfo.setCal(currentDateString);
                    couponInfo.setTimeOfDay(timeofDay);
                    couponInfo.setHourss(clickedJamList);
                    reference.setValue(couponInfo);

                    Intent intent = new Intent(Coupon.this ,BookingForm.class );
                    startActivity(intent);

                }
            });


    }
                                ///////////END OF ONCREATE/////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        currentDateString = DateFormat.getDateInstance().format(c.getTime());
        Date = findViewById(R.id.tvDate3);
        Date.setText(currentDateString);

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TimeDay = findViewById(R.id.tvTimeOfTheDay);
        timeofDay = hourOfDay + ":" + minute;
        TimeDay.setText(timeofDay);
    }





///////////////////////////////SPINNER STUFFS///////////////////////////////////////////

    private void initList(){
        mJamList = new ArrayList<>();
        mJamList.add(new spinItem("1  Hour"));
        mJamList.add(new spinItem("2 Hours"));
        mJamList.add(new spinItem("3 Hours"));
        mJamList.add(new spinItem("4 Hours"));
        mJamList.add(new spinItem("5 Hours"));
        mJamList.add(new spinItem("6 Hours"));
        mJamList.add(new spinItem("7 Hours"));
        mJamList.add(new spinItem("8 Hours"));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}*/
