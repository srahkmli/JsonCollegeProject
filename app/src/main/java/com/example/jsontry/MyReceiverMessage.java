package com.example.jsontry;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyReceiverMessage extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle bundle=intent.getExtras();
        if (bundle==null)  return;
        Object [] pdus= (Object[]) bundle.get("pdus");
        if (pdus==null) return;
        SmsMessage [] smsMessages=new SmsMessage[pdus.length];
        for (int i=0; i< pdus.length; i++)
        {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                String format= (String) bundle.get("format");
                smsMessages[i] =SmsMessage.createFromPdu((byte[]) pdus[i],format);
            }
            else
            {
                smsMessages[i] =SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            String text="SMS from " + smsMessages[i].getDisplayOriginatingAddress() + ":" +smsMessages[i].getMessageBody()+"\n";
            Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
        } }}
