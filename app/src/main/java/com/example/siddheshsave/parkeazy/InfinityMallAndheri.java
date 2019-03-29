package com.example.siddheshsave.parkeazy;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;
import java.util.Calendar;

public class InfinityMallAndheri extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button b1;
    TextView Name,Location;
    Button button;
    Button button2;
    Button button3;
    TextView tv2;
    TextView tv;
    Spinner spinner;
    ImageView image;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinity_mall_andheri);
        image=(ImageView)findViewById(R.id.mall);
        b1=(Button)findViewById(R.id.button1);
        Name=(TextView)findViewById(R.id.name);
        Location=(TextView)findViewById(R.id.location);
        button = (Button) findViewById(R.id.date);
        tv = (TextView) findViewById(R.id.textView3);
        button2=(Button) findViewById(R.id.time);
        tv2= (TextView) findViewById(R.id.textView4);
        button3=(Button)findViewById(R.id.check);
        spinner=(Spinner)findViewById(R.id.spinner_hours);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.no_of_hours, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Intent i=getIntent();
        String name=i.getExtras().getString("Name");
        String location=i.getExtras().getString("Location");
        Name.setText(name);
        Location.setText(location);
        if(name.equals("Infinity Mall")&&location.equals("Andheri")){
            image.setImageResource(R.drawable.infinity_mall_andheri);
        }
        if(name.equals("Infinity Mall")&&location.equals("Malad")){
            image.setImageResource(R.drawable.infinity_mall_malad);
        }
        if(name.equals("Oberoi Mall")&&location.equals("Goregaon")){
            image.setImageResource(R.drawable.oberoi_mall);
        }
        if(name.equals("Inorbit Mall")&&location.equals("Malad")){
            image.setImageResource(R.drawable.inorbit_mall);
        }
        if(name.equals("Raghuleela Mall")&&location.equals("Kandivali")){
            image.setImageResource(R.drawable.raghuleela_mall);
        }
        if(name.equals("Viviana Mall")&&location.equals("Thane")){
            image.setImageResource(R.drawable.viviana_mall);
        }
        if(name.equals("Phoenix Market City")&&location.equals("Kurla")){
            image.setImageResource(R.drawable.phoenix_market_city);
        }
        if(name.equals("Growels 101 Mall")&&location.equals("Kandivali")){
            image.setImageResource(R.drawable.growels_101);
        }
        if(name.equals("Palladium Mall")&&location.equals("Lower Parel")){
            image.setImageResource(R.drawable.palladium_mall);
        }
        if(name.equals("Atria Mall")&&location.equals("Worli")){
            image.setImageResource(R.drawable.atria_mall);
        }
    }
    public void Back(View v){
        Intent i=new Intent(InfinityMallAndheri.this,Main4Activity.class);
        startActivity(i);
        finish();
    }
    public void DatePicker(View v){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datePickerDialog = new DatePickerDialog(InfinityMallAndheri.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String s;
                        // set day of month , month and year value in the edit text
                        tv.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    public void TimePicker(View v){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(InfinityMallAndheri.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tv2.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, false);//Yes 12 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
    public void changeActivity(View v) {
        Intent myIntent = new Intent(InfinityMallAndheri.this, Payment.class);
        startActivity(myIntent);
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        parent.getItemAtPosition(pos);
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
