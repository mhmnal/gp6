package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class WriteFeedback extends AppCompatActivity {

    private TextView email,subject;
    private EditText emailtop;
    private Button send,back;
    private ImageButton copy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_feedback);
        email = findViewById(R.id.tvEmail);
        emailtop = findViewById(R.id.wrEmail);
        send = findViewById(R.id.btnSend);
        copy = findViewById(R.id.ibCopy);
        back = findViewById(R.id.backwritefeedback);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText", email.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(WriteFeedback.this, "Copied", Toast.LENGTH_SHORT).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WriteFeedback.this, SecondActivity.class));
            }
        });

    }
    private void sendMail(){
        String message = emailtop.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose an email client"));
    }
}