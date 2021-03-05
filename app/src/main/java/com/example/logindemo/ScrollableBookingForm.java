/*
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ScrollableBookingForm extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener , TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {

    TextView Date,TimeDay,Hourstv;
    public String name,currentDateString,timeofDay,clickedJamList,phonenumber,carbrand,carmodel,carcolor,carplate;
    Button Confirm,button,buttons;
    EditText bookPn,bookCb,bookCm,bookCc,bookCp;
    Button bForm;
    private DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    private ArrayList<spinItem> mJamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollable_booking_form);

        //DATE/////////////////////////////////////////////////////////////////////
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());


        //VARIABLES//////////////////////////////////////////////////////////////
        bookPn = findViewById(R.id.etPhoneNumber3);
        bookCb = findViewById(R.id.eb);
        bookCm = findViewById(R.id.ec);
        bookCc = findViewById(R.id.ed);
        bookCp = findViewById(R.id.eelate);
        bForm =  findViewById(R.id.bw);
        Confirm = findViewById(R.id.btnConfirm);
        TextView textView = findViewById(R.id.tvDate2);
        textView.setText(currentDate);
        button = findViewById(R.id.ba);
        buttons = findViewById(R.id.bi);
        Spinner spinnerJam = findViewById(R.id.bu);
        Hourstv = findViewById(R.id.tvHours2);


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
        bForm.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 reference = FirebaseDatabase.getInstance().getReference("Coupon").child(name);

                 phonenumber = bookPn.getEditableText().toString().trim();
                 carbrand = bookCb.getEditableText().toString().trim();
                 carmodel = bookCm.getEditableText().toString().trim();
                 carcolor = bookCc.getEditableText().toString().trim();
                 carplate = bookCp.getEditableText().toString().trim();


                 bFormInfo bformInfo = new bFormInfo(phonenumber,carbrand,carmodel,carcolor,carplate,currentDateString,timeofDay,clickedJamList);
                 reference.setValue(bformInfo);



                 startActivity(new Intent(ScrollableBookingForm.this, ViewReceipt.class));

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

}
*/
