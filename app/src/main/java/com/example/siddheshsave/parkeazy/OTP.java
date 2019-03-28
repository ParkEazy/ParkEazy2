package com.example.siddheshsave.parkeazy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

public class OTP extends AppCompatActivity {
    Button b1,b2;
    EditText ed,ed1,ed2,ed3,ed4,ed5,ed6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        b1=(Button)findViewById(R.id.sendOTP);
        b2=(Button)findViewById(R.id.proceed);
        ed=(EditText)findViewById(R.id.editTextPhone);
        ed1=(EditText)findViewById(R.id.otp1);
        ed2=(EditText)findViewById(R.id.otp2);
        ed3=(EditText)findViewById(R.id.otp3);
        ed4=(EditText)findViewById(R.id.otp4);
        ed5=(EditText)findViewById(R.id.otp5);
        ed6=(EditText)findViewById(R.id.otp6);
        ed1.addTextChangedListener(new GenericTextWatcher(ed1));
        ed2.addTextChangedListener(new GenericTextWatcher(ed2));
        ed3.addTextChangedListener(new GenericTextWatcher(ed3));
        ed4.addTextChangedListener(new GenericTextWatcher(ed4));
        ed5.addTextChangedListener(new GenericTextWatcher(ed5));
        ed6.addTextChangedListener(new GenericTextWatcher(ed6));
    }
    public class GenericTextWatcher implements TextWatcher {
        private View view;
        private GenericTextWatcher(View view) {
            this.view = view;
        }
        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            switch(view.getId()) {
                case R.id.otp1:
                    if(text.length()==1)
                        ed2.requestFocus();
                    break;
                case R.id.otp2:
                    if(text.length()==1)
                        ed3.requestFocus();
                    else if(text.length()==0)
                        ed1.requestFocus();
                    break;
                case R.id.otp3:
                    if(text.length()==1)
                        ed4.requestFocus();
                    else if(text.length()==0)
                        ed2.requestFocus();
                    break;
                case R.id.otp4:
                    if(text.length()==1)
                        ed5.requestFocus();
                    else if(text.length()==0)
                        ed3.requestFocus();
                    break;
                case R.id.otp5:
                    if(text.length()==1)
                        ed6.requestFocus();
                    else if(text.length()==0)
                        ed4.requestFocus();
                    break;
                case R.id.otp6:
                    if(text.length()==0)
                        ed5.requestFocus();
                    break;
            }
        }
        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }
    public void SendOTP(View v){
        String phone=ed1.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(OTP.this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
            return;
        }
    }
    public void OTPVerification(View v){
        Intent i=new Intent(OTP.this,Main3Activity.class);
        startActivity(i);
    }
}
