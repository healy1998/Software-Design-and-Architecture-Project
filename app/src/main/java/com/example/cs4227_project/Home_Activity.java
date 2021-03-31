package com.example.cs4227_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_Activity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button upload;
        setContentView(R.layout.activity_home_);
        upload = findViewById(R.id.upload);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uploadIntent = new Intent(Home_Activity.this,
                        Upload_Activity.class);
                startActivity(uploadIntent);
            }
        });
    }
}