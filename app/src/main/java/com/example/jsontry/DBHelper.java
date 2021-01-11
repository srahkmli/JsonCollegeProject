package com.example.jsontry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create Table Userdetails(name TEXT primary key ,family TEXT , fav TEXT, gender TEXT, address TEXT, city TEXT, number TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("drop Table if exists Userdetails");
    }
    public  Boolean insertuserdata(String name , String family , String fav, String gender, String address, String city, String number){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("name",name);
        contentValues.put("family",family);
        contentValues.put("fav",fav);
        contentValues.put("gender",gender);
        contentValues.put("address",address);
        contentValues.put("city",city);
        contentValues.put("number",number);
        long result = db.insert("Userdetails", null,contentValues);
        if (result==1)
        {  return false;
        }else { return true; }
    }
    public Cursor getdata(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery("Select * from Userdetails",null);
        return cursor;
    }

}
