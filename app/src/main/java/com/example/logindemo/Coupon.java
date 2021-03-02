package com.example.logindemo;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Coupon extends AppCompatActivity implements DatePickerDialog.OnDateSetListener , SingleChoiceDialog.SingleChoiceListener , TimePickerDialog.OnTimeSetListener{

    TextView DisplayChoice,Date,TimeDay;
    String calendar,time,Hour2,currentDateString,timeofDay;
    Button Confirm,button,Time,buttons;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
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


                    //variables
                    Confirm = findViewById(R.id.btnConfirm);
                    DisplayChoice = findViewById(R.id.tvTime);
                    TextView textView = findViewById(R.id.tvDate2);
                    textView.setText(currentDate);
                    button = findViewById(R.id.btnCalendar);
                    Time = findViewById(R.id.btnTime);
                    buttons = findViewById(R.id.btnTimeSelect);



                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DialogFragment datePicker = new DatePickerFragment();
                            datePicker.show(getSupportFragmentManager(),"date picker");
                        }
                    });


                    Time.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DialogFragment singleChoiceDialog = new SingleChoiceDialog();
                            singleChoiceDialog.setCancelable(false);
                            singleChoiceDialog.show(getSupportFragmentManager(),"Single Choice Dialog");
                        }
                    });



            ////////////////TIME OF THE DAY PICKER////////////////////////////////////
            buttons = (Button) findViewById(R.id.btnTimeSelect);
            buttons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment timePicker = new TimePickerDialogFragment();
                    timePicker.show(getSupportFragmentManager(),"time picker");
                }
            });


        ///////////////////////ON BUTTON CLICK//////////////////////////////////////////////////////////////
       Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference("Coupon");
                //GET ALL THE VALUES

                CouponInfo couponInfo = new CouponInfo();
                couponInfo.setCal(currentDateString);
                couponInfo.setTimeOfDay(timeofDay);
               /* couponInfo.setCal("currentDateString");
                couponInfo.setTimeOfDay("timeofDay");
                couponInfo.setHourss("timeofDay");*/
                reference.setValue(couponInfo);


                startActivity(new Intent(Coupon.this ,BookingForm.class ));
            }
        });
    }



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
    public void onPositiveButtonClicked(String[] list, int position) {
        DisplayChoice.setText(list[position]);
    }

    @Override
    public void onNegativeButtonClicked() {
        DisplayChoice.setText("Select Hour/s");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TimeDay = findViewById(R.id.tvTimeOfTheDay);
        timeofDay = hourOfDay + ":" + minute;
        TimeDay.setText(timeofDay);
    }



    private Boolean validate(){
        Boolean result = false;
        calendar = Date.getText().toString();
        time = TimeDay.getText().toString();
        Hour2 = DisplayChoice.getText().toString();


        return result;
    }



}