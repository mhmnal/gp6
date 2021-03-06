package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookingForm extends AppCompatActivity {

    EditText bookPn, bookCb, bookCm, bookCc, bookCp;
    Button bForm;
    String name, phonenumber, carbrand, carmodel, carcolor, carplate;
    private DatabaseReference reference3;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);

        bookPn = findViewById(R.id.etPhoneNumber);
        bookCb = findViewById(R.id.etCarBrand);
        bookCm = findViewById(R.id.etCarModel);
        bookCc = findViewById(R.id.etCarColor);
        bookCp = findViewById(R.id.etCarPlate);
        bForm = findViewById(R.id.btnBConfirm);

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

        bForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference3 = FirebaseDatabase.getInstance().getReference("Booking").child(name);

                phonenumber = bookPn.getEditableText().toString().trim();
                carbrand = bookCb.getEditableText().toString().trim();
                carmodel = bookCm.getEditableText().toString().trim();
                carcolor = bookCc.getEditableText().toString().trim();
                carplate = bookCp.getEditableText().toString().trim();

                bFormInfo bformInfo = new bFormInfo();
                bformInfo.setPhonenumber(phonenumber);
                bformInfo.setCarbrand(carbrand);
                bformInfo.setCarmodel(carmodel);
                bformInfo.setCarcolor(carcolor);
                bformInfo.setCarplate(carplate);
                reference3.setValue(bformInfo);

                startActivity(new Intent(BookingForm.this, ViewReceipt.class));
            }
        });
    }
}