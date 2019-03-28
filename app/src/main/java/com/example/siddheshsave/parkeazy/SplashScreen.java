package com.example.siddheshsave.parkeazy;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent i=new Intent(SplashScreen.this,Main4Activity.class);
                SplashScreen.this.startActivity(i);
                SplashScreen.this.finish();
            }
        },2000);
    }
}
