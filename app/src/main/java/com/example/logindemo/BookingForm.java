package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookingForm extends AppCompatActivity {

    //VARIABLES
    EditText bookPn,bookCb,bookCm,bookCc,bookCp;
    Button bForm;
   private FirebaseDatabase rootNode;
   private DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);

        bookPn = (EditText) findViewById(R.id.etPhoneNumber);
        bookCb = (EditText)findViewById(R.id.etCarBrand);
        bookCm = (EditText)findViewById(R.id.etCarModel);
        bookCc = (EditText)findViewById(R.id.etCarColor);
        bookCp = (EditText)findViewById(R.id.etCarPlate);
        bForm = (Button) findViewById(R.id.btnBookingFormConfirm);


        bForm.setOnClickListener(new View.OnClickListener() {
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
            }
        });




    }
}