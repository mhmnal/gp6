package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Coupon extends AppCompatActivity implements DatePickerDialog.OnDateSetListener , SingleChoiceDialog.SingleChoiceListener , TimePickerDialog.OnTimeSetListener {

    TextView Hour,DisplayChoice,Date,TimeDay;
    String calendar,time,Hour2;
    Button Confirm,button,Time,buttons;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase rootNode;
    private DatabaseReference  reference;
    boolean[] selectedDay;
    ArrayList<Integer> daylist = new ArrayList<>();
    String[] dayArray = {"1 Hour", "2 Hours","3 Hours", "4 Hours","5 Hours", "6 Hours","7 Hours", "8 Hours","9 Hours", "10 Hours","11 Hours", "12 Hours"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_coupon);

                    ////////////FIREBASE////////////////////////////////////////////////////////
                     firebaseAuth = FirebaseAuth.getInstance();

                    //DATE/////////////////////////////////////////////////////////////////////
                    Calendar calendar = Calendar.getInstance();
                    String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

                    storedVariables();
                    TextView textView = findViewById(R.id.tvDate2);
                    textView.setText(currentDate);

                    button = (Button) findViewById(R.id.btnCalendar);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DialogFragment datePicker = new DatePickerFragment();
                            datePicker.show(getSupportFragmentManager(),"date picker");
                        }
                    });

                    ////////////////////////////////////Direct from COUPON TO BOOKING FORM//////////////////////////

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("Coupon");
                    //Upload data to database
                    String cal = Date.getText().toString().trim();
                    String timeOfDay = TimeDay.getText().toString().trim();
                    String Hourss = DisplayChoice.getText().toString().trim();

                    CouponInfo couponInfo = new CouponInfo(cal, timeOfDay, Hourss);
                    reference.setValue(couponInfo);

                }
            }

        });
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Coupon.this ,BookingForm.class ));
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////


                    //////////TIME PICKER////////////////////////////////////////////////////////////////////////
                    //////////////////VARIABLES/////////////////////////////////////////////////

                     Time = findViewById(R.id.btnTime);
                     ///////////////////////////////////////////////////////////////////////////
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
            buttons = (Button) findViewById(R.id.btnTimeSelect);
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

        Date = (TextView) findViewById(R.id.tvDate3);
        Date.setText(currentDateString);
    }



    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        DisplayChoice.setText(list[position]);
    }

    @Override
    public void onNegativeButtonClicked() {
        DisplayChoice.setText("Select Hour/s");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TimeDay = (TextView) findViewById(R.id.tvTimeOfTheDay);
        TimeDay.setText(hourOfDay + ":" + minute);
    }


    private void storedVariables(){
        Confirm = (Button) findViewById(R.id.btnConfirm);
        DisplayChoice = findViewById(R.id.tvTime);

    }

    private Boolean validate(){
        Boolean result = false;
        calendar = Date.getText().toString();
        time = TimeDay.getText().toString();
        Hour2 = DisplayChoice.getText().toString();



        return result;
    }


    ////////////SENDING DATA BACK TO DATABASE/////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////

}