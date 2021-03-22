package com.example.software_design_and_architecture_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class Home_Activity extends AppCompatActivity {

    @Override
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
                        Upload_activity.class);
                startActivity(uploadIntent);
            }
        });
    }
}