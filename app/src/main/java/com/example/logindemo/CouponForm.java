package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.SimpleTimeZone;

public class CouponForm extends AppCompatActivity implements
        /*DatePickerDialog.OnDateSetListener , TimePickerDialog.OnTimeSetListener*/ AdapterView.OnItemSelectedListener {
    public String name, clickedJamList;
    TextView currentDateString, showTime;
    Button cForm,back;
    Spinner spinnerHour;
    private DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    private ArrayList<spinItem> mJamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_form);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String cDate = simpleDateFormat.format(calendar.getTime());
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("hh:mm:ss");
        String cTime = simpleDateFormat1.format(calendar.getTime());
        /*Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());*/

        currentDateString = findViewById(R.id.tvDateFinal);
        currentDateString.setText(cDate);
        showTime = findViewById(R.id.tvTimeShow);
        showTime.setText(cTime);
        cForm = findViewById(R.id.btnBookingForm);
        spinnerHour = findViewById(R.id.bu2);
        back = findViewById(R.id.backcouponform);

        firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("UserInfo").child(firebaseAuth.getUid());

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                name = snapshot.child("userName").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        initList();
        spinAdapter mAdapter = new spinAdapter(this, mJamList);
        spinnerHour.setAdapter(mAdapter);
        spinnerHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinItem clickedItem = (spinItem) parent.getItemAtPosition(position);
                clickedJamList = clickedItem.getJam();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        cForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference = FirebaseDatabase.getInstance().getReference("Coupon").child(name);
                CouponInfo couponInfo = new CouponInfo();
                couponInfo.setCal(cDate);
                couponInfo.setTimeOfDay(cTime);
                couponInfo.setHourss(clickedJamList);
                reference.setValue(couponInfo);
                Toast.makeText(CouponForm.this, "Coupon Booked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CouponForm.this, doneInterface2.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CouponForm.this, SecondActivity.class));
            }
        });

    }

///////////////////////////////SPINNER STUFFS///////////////////////////////////////////

    private void initList() {
        mJamList = new ArrayList<>();
        mJamList.add(new spinItem("1"));
        mJamList.add(new spinItem("2"));
        mJamList.add(new spinItem("3"));
        mJamList.add(new spinItem("4"));
        mJamList.add(new spinItem("5"));
        mJamList.add(new spinItem("6"));
        mJamList.add(new spinItem("7"));
        mJamList.add(new spinItem("8"));
        mJamList.add(new spinItem("9"));
        mJamList.add(new spinItem("10"));
        mJamList.add(new spinItem("11"));
        mJamList.add(new spinItem("12"));
        mJamList.add(new spinItem("13"));
        mJamList.add(new spinItem("14"));
        mJamList.add(new spinItem("15"));
        mJamList.add(new spinItem("16"));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

