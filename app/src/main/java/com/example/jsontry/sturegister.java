package com.example.jsontry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class sturegister extends AppCompatActivity {

    String city , gender;
    EditText name , family , fav , address ,number;
    RadioButton female , male;
    Button btnReg , btnview;
    DBHelper db;
    Spinner Citylist;
    List<String> cities = new ArrayList<>();
    Animation animZoomIn,animZoomOut,animRotate,animBounce;
    public static final int PERMISSION_REQUEST_CODE=123;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sturegister);
        checkPermission();
        name =findViewById(R.id.input_name);
        family =findViewById(R.id.input_family);
        fav =findViewById(R.id.input_fav);
        address=findViewById(R.id.input_address);
        female=findViewById(R.id.radioButtonFemale);
        male=findViewById(R.id.radioButtonMale);
        Citylist = findViewById(R.id.Citylist);
        number = findViewById(R.id.input_number);
        btnReg = findViewById(R.id.buttonRegister);
        btnview = findViewById(R.id.btnviewdata);
        db = new DBHelper(this);
        animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate);
        animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_in);
        animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_out);
        animBounce = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnReg.setVisibility(View.VISIBLE);
                btnReg.startAnimation(animZoomOut);
                if (female.isChecked()) {
                    gender = female.getText().toString();
                } else if (male.isChecked()) {
                    gender = male.getText().toString();
                }else gender="none";
                String nameTXT = name.getText().toString();
                String familyTXT = family.getText().toString();
                String favTXT = fav.getText().toString();
                String addressTXT = address.getText().toString();
                String cityTXT = city;
                String numberTXT = number.getText().toString();
               String genderTXT = gender;

               Boolean checkinsertdata = db.insertuserdata(nameTXT,familyTXT , favTXT, genderTXT, addressTXT, cityTXT, numberTXT);
               if (checkinsertdata == true)
                    Toast.makeText(sturegister.this, "New Entry", Toast.LENGTH_SHORT).show();
             else Toast.makeText(sturegister.this, " Entry failed", Toast.LENGTH_SHORT).show();

                //Sending SMS--------------------
                sendSms();
            }
        });
         btnview.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 btnview.setVisibility(View.VISIBLE);
                 btnview.startAnimation(animBounce);
                 Cursor res = db.getdata();
                 if (res.getCount() == 0) {
                     Toast.makeText(sturegister.this, "No Entry", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 StringBuffer buffer = new StringBuffer();
                 while (res.moveToNext()) {
                     buffer.append("Name : "+res.getString(0) + "\n");
                     buffer.append("family : "+res.getString(1) + "\n");
                     buffer.append("Number : "+res.getString(6) + "\n");
                     buffer.append("gender : "+res.getString(3) + "\n");
                     buffer.append("City : "+res.getString(5) + "\n\n");
                 }

                 AlertDialog.Builder builder = new AlertDialog.Builder(sturegister.this);
                 builder.setCancelable(true);
                 builder.setTitle("Student");
                 builder.setMessage(buffer.toString());
                 builder.show();
             }
         });

        cities.add("اصفهان");
        cities.add("شیراز");
        cities.add("تهران");
        cities.add("بوشهر");
        cities.add("مشهد");
        cities.add("خوزستان");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(sturegister.this,
                android.R.layout.simple_spinner_dropdown_item, cities);
        Citylist.setAdapter(adapter);

        Citylist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                city=cities.get(i);
                Toast.makeText(sturegister.this, "شهر " + city + " انتخاب شد", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

}
    public void sendSms() {
        try {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(number.getText().toString(),null,"ثبت نام موفقیت امیز بود",null,null);
            Toast.makeText(this,"sms sent",Toast.LENGTH_SHORT).show();
            Log.i("smssent","sms sent ! "); }
        catch (Exception e) {
            Toast.makeText(this,"sms did not send, try again !",Toast.LENGTH_SHORT).show();
            e.printStackTrace(); }
    }



    private void checkPermission()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.SEND_SMS},PERMISSION_REQUEST_CODE);
            } } }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode==PERMISSION_REQUEST_CODE)
        {
            if(grantResults[0]!= PackageManager.PERMISSION_GRANTED && grantResults[1]!=PackageManager.PERMISSION_GRANTED && grantResults[2]!=PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show();
                finish();
            } } }
}