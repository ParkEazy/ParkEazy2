package com.example.siddheshsave.parkeazy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Pop extends Activity {
    Button b;
    EditText ed;
    FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.4));
        b=(Button)findViewById(R.id.button_recover);
        ed=(EditText)findViewById(R.id.RecoverPassword);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
    }
    public void Recover(View v){
        String email = ed.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplication(), "Please Enter E-mail", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Sending Confirmation Mail");
        progressDialog.show();
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(Pop.this,"Check E-mail To Reset Password",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(Pop.this, "Error. Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
