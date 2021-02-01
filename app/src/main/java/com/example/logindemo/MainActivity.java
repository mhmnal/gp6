package com.example.logindemo;

import android.app.ProgressDialog;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog  progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.txt_name);
        Password = (EditText)findViewById(R.id.txt_Password);
        Info = (TextView)findViewById(R.id.txt_attempts);
        Login = (Button)findViewById(R.id.btn_login);
        userRegistration = (TextView)findViewById(R.id.txt_register);

        Info.setText("Attempts: 5");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));

        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
            userRegistration.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity((new Intent(MainActivity.this, RegistrationActivity.class)));
                }
            }));
    }

    private void validate(String userName, String userPassword){

        //progress dialog is for fun
        progressDialog.setMessage("Welcome");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        startActivity((new Intent(MainActivity.this, RegistrationActivity.class)));
                }else{
                        Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                        counter--;
                        Info.setText("Attempts: " + counter);
                        progressDialog.dismiss();
                        if(counter == 0){
                            Login.setEnabled(false);
                        }
                }
            }
        });

    }
}