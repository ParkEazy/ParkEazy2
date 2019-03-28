package com.example.siddheshsave.parkeazy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firebaseFirestore;
    EditText ed1,ed2,ed3,ed4,ed5,ed6;
    Button b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b4=(Button)findViewById(R.id.button4);
        ed1=(EditText)findViewById(R.id.editTextFirstName);
        ed2=(EditText)findViewById(R.id.editTextLastName);
        ed4=(EditText)findViewById(R.id.editTextNumberPlate);
        ed3=(EditText)findViewById(R.id.editTextEmail);
        ed5=(EditText)findViewById(R.id.editTextPassword);
        ed6=(EditText)findViewById(R.id.editTextConfirmPassword);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        firebaseFirestore=FirebaseFirestore.getInstance();
    }
    public void SignUp(View v) {
        final String firstName = ed1.getText().toString().trim();
        final String lastName = ed2.getText().toString().trim();
        final String numberPlate = ed4.getText().toString().trim();
        final String email = ed3.getText().toString().trim();
        final String password = ed5.getText().toString().trim();
        final String confirmPassword = ed6.getText().toString().trim();
        if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(Main2Activity.this, "Please Enter First Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(Main2Activity.this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(Main2Activity.this, "Please Enter E-mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(Main2Activity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(numberPlate)) {
            Toast.makeText(Main2Activity.this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(Main2Activity.this, "Please Confirm Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(Main2Activity.this, "Password Too Short", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.equals(confirmPassword)) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.setMessage("Sending Verification Mail");
                    progressDialog.show();
                    if (task.isSuccessful()) {
                        Map<String,String> user=new HashMap<String,String>();
                        user.put("FirstName",firstName);
                        user.put("LastName",lastName);
                        user.put("CarNumberPlate",numberPlate);
                        user.put("E-mail",email);
                        firebaseFirestore.collection("Users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressDialog.dismiss();
                                        if(task.isSuccessful()){
                                            Toast.makeText(Main2Activity.this,"Verification Mail Has Been Sent To Your E-mail ID. Please Verify Your E-mail To Proceed",Toast.LENGTH_SHORT).show();
                                            Intent i=new Intent(Main2Activity.this,Main3Activity.class);
                                            startActivity(i);
                                        }
                                        else{
                                            Toast.makeText(Main2Activity.this,"Unable To Send Verification Mail. Please Try Again",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(Main2Activity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                                firebaseUser.delete();
                            }
                        });
                    }
                    else {
                        Toast.makeText(Main2Activity.this, "Registration Failed. Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(Main2Activity.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}
