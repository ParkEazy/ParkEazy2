package com.example.siddheshsave.parkeazy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailVerification extends AppCompatActivity {
    Button b;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        b=(Button)findViewById(R.id.proceed);
        firebaseAuth=FirebaseAuth.getInstance();
    }
    public void Proceed(View v){
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser().isEmailVerified()){
            Intent i=new Intent(EmailVerification.this,OTP.class);
            startActivity(i);
        }
        else{
            Toast.makeText(EmailVerification.this,"Please Verify Your E-mail To Proceed",Toast.LENGTH_SHORT).show();
        }
    }
    public void Send(View v){
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog.setMessage("Sending Verification Mail");
        progressDialog.show();
        firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(EmailVerification.this,"Verification Mail Sent",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EmailVerification.this,"Unable To Send Verification Mail. Please Try Again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
