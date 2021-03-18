package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class BookingForm extends AppCompatActivity {

    EditText bookPn, bookCm, bookCc, bookCp;
    Button bForm,back;
    String name, phonenumber, carmodel, carcolor, carplate;
    Spinner spinnerCar;
    private DatabaseReference reference3;
    private FirebaseAuth firebaseAuth;
    public String clickedCarList;
    private ArrayList<brandItem> mCarList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);

        bookPn = findViewById(R.id.etPhoneNumber);
        bookCm = findViewById(R.id.etCarModel);
        bookCc = findViewById(R.id.etCarColor);
        bookCp = findViewById(R.id.etCarPlate);
        bForm = findViewById(R.id.btnBConfirm);
        spinnerCar = findViewById(R.id.spinnerCarBrand);
        back = findViewById(R.id.backbookingform);

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
        carAdapter cAdapter = new carAdapter(this, mCarList);
        spinnerCar.setAdapter(cAdapter);
        spinnerCar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                brandItem clickedItem = (brandItem) parent.getItemAtPosition(position);
                clickedCarList = clickedItem.getCar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        bForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                reference3 = FirebaseDatabase.getInstance().getReference("Booking").child(name);

                phonenumber = bookPn.getEditableText().toString().trim();
                carmodel = bookCm.getEditableText().toString().trim();
                carcolor = bookCc.getEditableText().toString().trim();
                carplate = bookCp.getEditableText().toString().trim();

                bFormInfo bformInfo = new bFormInfo();
                bformInfo.setPhonenumber(phonenumber);
                bformInfo.setCarbrand(clickedCarList);
                bformInfo.setCarmodel(carmodel);
                bformInfo.setCarcolor(carcolor);
                bformInfo.setCarplate(carplate);
                reference3.setValue(bformInfo);

                Toast.makeText(BookingForm.this, "Car Details Updated", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(BookingForm.this, doneInterface2.class));
            }}
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookingForm.this, SecondActivity.class));
            }
        });


    }

    private void initList() {
        mCarList = new ArrayList<brandItem>();
        mCarList.add(new brandItem("Perodua"));
        mCarList.add(new brandItem("Proton"));
        mCarList.add(new brandItem("Honda"));
        mCarList.add(new brandItem("Nissan"));
        mCarList.add(new brandItem("Mazda"));
        mCarList.add(new brandItem("Toyota"));
        mCarList.add(new brandItem("Mini"));
        mCarList.add(new brandItem("Haval"));
        mCarList.add(new brandItem("Mitsubishi"));
        mCarList.add(new brandItem("Volkswagen"));
        mCarList.add(new brandItem("Kia"));
        mCarList.add(new brandItem("Ford"));
        mCarList.add(new brandItem("Mercedes"));
        mCarList.add(new brandItem("Chevrolet"));
        mCarList.add(new brandItem("Volvo"));
        mCarList.add(new brandItem("BMW"));
        mCarList.add(new brandItem("Subaru"));
        mCarList.add(new brandItem("Renault"));


    }

    private Boolean validate() {
        Boolean result = false;
        phonenumber = bookPn.getText().toString();
        carmodel = bookCm.getText().toString();
        carcolor = bookCc.getText().toString();
        carplate = bookCp.getText().toString();


        if (phonenumber.isEmpty() || carmodel.isEmpty() || carcolor.isEmpty() || carplate.isEmpty() || clickedCarList.isEmpty()) {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }
}