package com.example.jsontry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_login extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;

    Button btn_Log;
    SharedPreferences shareddP;
    SharedPreferences.Editor editor;
    public static final String mypreference = "MyPrefers";
    public static  String MY_USERNAME = "nameKey";
    public static  String MY_PASSWORD = "passKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail=findViewById(R.id.user_email);
        editTextPassword=findViewById(R.id.user_password);
        btn_Log=findViewById(R.id.buttonLogin);
        shareddP=getSharedPreferences(mypreference,MODE_PRIVATE);

        btn_Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = editTextEmail.getText().toString();
                String pass = editTextPassword.getText().toString();
                if(!MY_USERNAME.isEmpty()&& !MY_PASSWORD.isEmpty()){
                    editor = shareddP.edit();
                    editor.putString(MY_USERNAME,user);
                    editor.putString(MY_PASSWORD,pass);
                    editor.apply();
                    Toast.makeText(activity_login.this, "Login succesfully", Toast.LENGTH_SHORT).show();
                  }
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);


    }
        });

}
}




