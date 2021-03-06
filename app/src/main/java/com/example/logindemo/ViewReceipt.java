package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;


public class ViewReceipt extends AppCompatActivity {

    private TextView price, receiptDate, receiptTime, receiptHours, receiptPn, receiptCb, receiptCm, receiptCc, receiptCp,feedback;
    private Button bDasboard;
    private FirebaseAuth firebaseAuth;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_receipt);

       varscall();
        firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("UserInfo").child(firebaseAuth.getUid());

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                name = snapshot.child("userName").getValue().toString();
                final DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Booking").child(name);

                reference2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        bFormInfo bform = snapshot.getValue(bFormInfo.class);
                        receiptPn.setText(bform.getPhonenumber());
                        receiptCb.setText(bform.getCarbrand());
                        receiptCm.setText(bform.getCarmodel());
                        receiptCc.setText(bform.getCarcolor());
                        receiptCp.setText(bform.getCarplate());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                final DatabaseReference reference4 = FirebaseDatabase.getInstance().getReference("Coupon").child(name);
                reference4.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        CouponInfo couponInfo = snapshot.getValue(CouponInfo.class);
                        receiptDate.setText(couponInfo.getCal());
                        receiptTime.setText(couponInfo.getTimeOfDay());
                        receiptHours.setText(couponInfo.getHourss());

                        String hours = couponInfo.getHourss();
                        int newHours = Integer.parseInt(hours);
                        double price2 = newHours * 2;
                        DecimalFormat df = new DecimalFormat("0.00");
                        String newPrice = df.format(price2);

                        price.setText("RM " + newPrice);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewReceipt.this, "Not hello", Toast.LENGTH_SHORT).show();
            }
        });

        setOnClick();
    }

    public void varscall(){
        receiptDate = findViewById(R.id.tvReceiptDate);
        receiptTime = findViewById(R.id.tvReceiptTime);
        receiptHours = findViewById(R.id.tvReceiptHours);
        receiptPn = findViewById(R.id.tvPNumber);
        receiptCb = findViewById(R.id.tvCBrand);
        receiptCm = findViewById(R.id.tvcModel);
        receiptCc = findViewById(R.id.tvCColor);
        receiptCp = findViewById(R.id.tvCPlate);
        bDasboard = findViewById(R.id.tvBDashboard);
        price = findViewById(R.id.tvPrice);
        feedback = findViewById(R.id.feedback);

    }

    public void setOnClick(){
        bDasboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewReceipt.this, SecondActivity.class));
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewReceipt.this, WriteFeedback.class));
            }
        });
    }
}