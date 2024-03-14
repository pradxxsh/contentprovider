package com.example.contentprovider;

import static android.Manifest.permission.READ_CONTACTS;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.Manifest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private void getPhoneContacts(){
        if(ContextCompat.checkSelfPermission(this, READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {READ_CONTACTS}, 0);
        }
        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = ((ContentResolver) contentResolver).query(uri,null,null,null,null);
        Log.i("CONTACT_PROVIDER_DEMO" , "TOTAL # COUNTS :::"+cursor.getCount());
        if(cursor.getCount()> 0)
        {
            while(cursor.moveToNext()){
                @SuppressLint("Range")
                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range")
                String contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Log.i("Content_provider_demo","Name: # "+contactName+"Number: "+contactNumber);
            }
        }
    }
    public void btnGetContactPressed(View view){
        getPhoneContacts();
    }
}
