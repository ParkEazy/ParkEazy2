package com.example.siddheshsave.parkeazy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.shobhitpuri.custombuttons.GoogleSignInButton;

public class Main3Activity extends AppCompatActivity {
    Button b1,b2,b3;
    EditText ed1,ed2;
    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInButton google_signin;
    //FirebaseAuth.AuthStateListener firebaseAuthListener;
    boolean doubleBackToExitPressedOnce=false;
    /*@Override
    protected void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        b1=(Button)findViewById(R.id.button1);
        ed1=(EditText)findViewById(R.id.Login3Email);
        ed2=(EditText)findViewById(R.id.Login3Password);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        progressDialog=new ProgressDialog(this);
        /*firebaseAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    Intent i=new Intent(Main3Activity.this,Main4Activity.class);
                    startActivity(i);
                }
            }
        };*/
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);
        google_signin=(GoogleSignInButton) findViewById(R.id.google_button);
        google_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent=mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent,101);
            }
        });
    }
    /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build();*/
    /*public void Google(View v){
        signIn();
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {

            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount account)
    {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(Main3Activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseUser = firebaseAuth.getCurrentUser();
                            finish();
                            Intent i=new Intent(Main3Activity.this,Main4Activity.class);
                            startActivity(i);
                            Toast.makeText(Main3Activity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Main3Activity.this,"Login Failed. Try Again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void LogIn(View v) {
        String email = ed1.getText().toString().trim();
        String password = ed2.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(Main3Activity.this, "Please Enter E-mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(Main3Activity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(Main3Activity.this, "Password Too Short", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging In");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    if(firebaseAuth.getCurrentUser().isEmailVerified()){
                        Toast.makeText(Main3Activity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(Main3Activity.this,Main4Activity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(Main3Activity.this,"Please Verify Your E-mail To Login",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Main3Activity.this, "Login Failed. Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void SignUp(View v) {
        Intent i=new Intent(Main3Activity.this,Main2Activity.class);
        startActivity(i);
    }
    public void Forgot(View v) {
        Intent i=new Intent(Main3Activity.this,Pop.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            ActivityCompat.finishAffinity(this);
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(Main3Activity.this, "Press Again To Exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
