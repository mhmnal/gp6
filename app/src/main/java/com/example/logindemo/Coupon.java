package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Coupon extends AppCompatActivity implements DatePickerDialog.OnDateSetListener , SingleChoiceDialog.SingleChoiceListener , TimePickerDialog.OnTimeSetListener {

    TextView Hour,DisplayChoice;
    boolean[] selectedDay;
    ArrayList<Integer> daylist = new ArrayList<>();
    String[] dayArray = {"1 Hour", "2 Hours","3 Hours", "4 Hours","5 Hours", "6 Hours","7 Hours", "8 Hours","9 Hours", "10 Hours","11 Hours", "12 Hours"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_coupon);

                    //DATE/////////////////////////////////////////////////////////////////////
                    Calendar calendar = Calendar.getInstance();
                    String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

                    TextView textView = findViewById(R.id.tvDate2);
                    Hour = findViewById(R.id.tvTime);
                    textView.setText(currentDate);

                    Button button = (Button) findViewById(R.id.btnCalendar);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DialogFragment datePicker = new DatePickerFragment();
                            datePicker.show(getSupportFragmentManager(),"date picker");
                        }
                    });

                    //////////TIME PICKER///////////////////////////////////////////////////////
                    DisplayChoice = findViewById(R.id.tvTime);

                    Button Time = findViewById(R.id.btnTime);
                    Time.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DialogFragment singleChoiceDialog = new SingleChoiceDialog();
                            singleChoiceDialog.setCancelable(false);
                            singleChoiceDialog.show(getSupportFragmentManager(),"Single Choice Dialog");
                        }
                    });
                    ///////////////////////////////////////////////////////////////////////////////////////

            ////////////////TIME OF THE DAY PICKER////////////////////////////////////
            Button buttons = (Button) findViewById(R.id.btnTimeSelect);
            buttons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment timePicker = new TimePickerDialogFragment();
                    timePicker.show(getSupportFragmentManager(),"time picker");
                }
            });

    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());

        TextView textView = (TextView) findViewById(R.id.tvDate3);
        textView.setText(currentDateString);
    }

    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        DisplayChoice.setText("Selected Item = "+list[position]);
    }

    @Override
    public void onNegativeButtonClicked() {
        DisplayChoice.setText("Select Hour/s");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView = (TextView) findViewById(R.id.tvTimeOfTheDay);
        textView.setText(hourOfDay + ":" + minute);
    }
}