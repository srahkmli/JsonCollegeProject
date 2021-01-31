package com.example.jsontry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    ImageButton btnRegi , btnmediaplayer , btnlog , btngraph;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         btnRegi = findViewById(R.id.MainReg_Btn);
         btnlog =findViewById(R.id.ShowInfo_Btn);
         btnmediaplayer = findViewById(R.id.btn4);
         btngraph = findViewById(R.id.btn5);

    }

    public void MainLogin_Btn(View view) {
        Intent i = new Intent(getApplicationContext(), activity_login.class);
        startActivity(i);
    }

    public void MainRegister_Btn(View view) {
        Intent i = new Intent(getApplicationContext(), sturegister.class);
        startActivity(i);
    }


    public void BtnEntertainment(View view) {
        Intent i = new Intent(getApplicationContext(), musicplayer.class);
        startActivity(i);
    }

    public void Graph(View view) {
        Intent i = new Intent(getApplicationContext(), PieChartActivty.class);
        startActivity(i);
    }
    }

