package com.example.software_design_and_architecture_project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Upload_activity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
