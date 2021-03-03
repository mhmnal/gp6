/*
package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookingForm extends AppCompatActivity {

    public static final String PN_RECEIPT ="com.example.logindemo.PN_RECEIPT";
    public static final String CB_RECEIPT ="com.example.logindemo.CB_RECEIPT";
    public static final String CM_RECEIPT ="com.example.logindemo.CM_RECEIPT";
    public static final String CC_RECEIPT ="com.example.logindemo.CC_RECEIPT";
    public static final String CP_RECEIPT ="com.example.logindemo.CP_RECEIPT";

    //VARIABLES
    EditText bookPn,bookCb,bookCm,bookCc,bookCp;
    Button bForm;
   private FirebaseDatabase rootNode;
   private DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);

        Intent intent2 = getIntent();

        bookPn = (EditText) findViewById(R.id.etPhoneNumber);
        bookCb = (EditText)findViewById(R.id.etCarBrand);
        bookCm = (EditText)findViewById(R.id.etCarModel);
        bookCc = (EditText)findViewById(R.id.etCarColor);
        bookCp = (EditText)findViewById(R.id.etCarPlate);
        bForm = (Button) findViewById(R.id.btnBookingFormConfirm);


        */
/*bForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                String phonenumber = bookPn.getEditableText().toString().trim();
                reference = rootNode.getReference("Booking").child(phonenumber);
                //GET ALL THE VAlUES
                String carbrand = bookCb.getEditableText().toString().trim();
                String carmodel = bookCm.getEditableText().toString().trim();
                String carcolor = bookCc.getEditableText().toString().trim();
                String carplate = bookCp.getEditableText().toString().trim();

                bFormInfo bformInfo = new bFormInfo(phonenumber,carbrand,carmodel,carcolor,carplate);
                reference.setValue(bformInfo);

                Intent intent3 = new Intent(BookingForm.this,ViewReceipt.class);
                intent3.putExtra(PN_RECEIPT, phonenumber);
                intent3.putExtra(CB_RECEIPT, carbrand);
                intent3.putExtra(CM_RECEIPT, carmodel);
                intent3.putExtra(CC_RECEIPT, carcolor);
                intent3.putExtra(CP_RECEIPT, carplate);
                startActivity(intent3);

            }*//*

       // });






    }
}*/
