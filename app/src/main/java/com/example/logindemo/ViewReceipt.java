package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewReceipt extends AppCompatActivity {

    private TextView receiptDate,receiptTime,receiptHours,receiptPn,receiptCb,receiptCm,receiptCc,receiptCp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_view_receipt);

        Intent intent3 = getIntent();
        String RecCal = intent3.getStringExtra(ScrollableBookingForm.CAL_RECEIPT);
        String RecTod = intent3.getStringExtra(ScrollableBookingForm.CAL_TOD);
        String RecHours = intent3.getStringExtra(ScrollableBookingForm.CAL_HOURS);
        String phonenumber = intent3.getStringExtra(ScrollableBookingForm.PN_RECEIPT);
        String carbrand = intent3.getStringExtra(ScrollableBookingForm.CB_RECEIPT);
        String carmodel = intent3.getStringExtra(ScrollableBookingForm.CM_RECEIPT);
        String carcolor = intent3.getStringExtra(ScrollableBookingForm.CC_RECEIPT);
        String carplate = intent3.getStringExtra(ScrollableBookingForm.CP_RECEIPT);

        receiptDate = findViewById(R.id.tvReceiptDate);
        receiptTime = findViewById(R.id.tvReceiptTime);
        receiptHours = findViewById(R.id.tvReceiptHours);
        receiptPn = findViewById(R.id.tvPNumber);
        receiptCb = findViewById(R.id.tvCBrand);
        receiptCm = findViewById(R.id.tvcModel);
        receiptCc = findViewById(R.id.tvCColor);
        receiptCp = findViewById(R.id.tvCPlate);



        receiptDate.setText(RecCal);
        receiptTime.setText(RecTod);
        receiptHours.setText(RecHours);
        receiptPn.setText(phonenumber);
        receiptCb.setText(carbrand);
        receiptCm.setText(carmodel);
        receiptCc.setText(carcolor);
        receiptCp.setText(carplate);

        /*firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference  databaseReference = firebaseDatabase.getReference("Coupon").child("");
       final DatabaseReference databaseReference1 = firebaseDatabase.getReference("Booking").child("");
*/


       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CouponInfo classCouponInfo = dataSnapshot.getValue(CouponInfo.class);
               *//* receiptDate.setText(classCouponInfo.getCal());
                receiptTime.setText(classCouponInfo.getTimeOfDay());*//*
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewReceipt.this, error.getCode(), Toast.LENGTH_SHORT).show();
            }


        });

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bFormInfo classbFormInfo = dataSnapshot.getValue(bFormInfo.class);
                receiptCb.setText(classbFormInfo.getCarbrand());
                receiptCm.setText(classbFormInfo.getCarmodel());
                receiptCc.setText(classbFormInfo.getCarcolor());
                receiptCp.setText(classbFormInfo.getCarplate());
                receiptPn.setText(classbFormInfo.getPhonenumber());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });*/


    }

}