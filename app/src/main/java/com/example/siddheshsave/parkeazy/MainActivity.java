package com.example.siddheshsave.parkeazy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3;
    EditText ed1,ed2;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    RelativeLayout rellay1,rellay2;
    Handler handler=new Handler();
    Runnable runnable = new Runnable(){
            @Override
            public void run() {
        rellay1.setVisibility(View.VISIBLE);
        rellay2.setVisibility(View.VISIBLE);
            }
        };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rellay1= (RelativeLayout)findViewById(R.id.rellay1);
        rellay2=(RelativeLayout)findViewById(R.id.rellay2);
        handler.postDelayed(runnable,2000);
        ed1=(EditText)findViewById(R.id.LoginEmail);
        ed2=(EditText)findViewById(R.id.LoginPassword);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        UserLoggedIn();
    }
    public void UserLoggedIn(){
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null&&firebaseUser.isEmailVerified()){
            Intent i=new Intent(MainActivity.this,Main4Activity.class);
            startActivity(i);
        }
    }
    public void LogIn(View v) {
        String email=ed1.getText().toString().trim();
        String password=ed2.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(MainActivity.this,"Please Enter E-mail",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length()<6){
            Toast.makeText(MainActivity.this,"Password Too Short",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging In");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    if(firebaseAuth.getCurrentUser().isEmailVerified()){
                        Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(MainActivity.this,Main4Activity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Please Verify Your E-mail To Login",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Login Failed. Try Again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void SignUp(View v) {
        b2=(Button)findViewById(R.id.button2);
        Intent i=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(i);
    }
    public void Forgot(View v) {
        Intent i=new Intent(MainActivity.this,Pop.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
}
