package com.example.videocategory.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.videocategory.MainActivity;
import com.example.videocategory.R;

public class LoadScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_load_screen );
        getSupportActionBar().hide();
        new Handler(  ).postDelayed( new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent( LoadScreen.this, MainActivity.class );
                startActivity( intent );
                finish();
            }
        },5000 );
    }
}