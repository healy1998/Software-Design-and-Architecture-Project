package com.example.software_design_and_architecture_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Profile_Activity extends AppCompatActivity {
    final FirebaseAuth auth = FirebaseAuth.getInstance();
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent homeIntent = new Intent(Profile_Activity.this,
                                              Home_Activity.class);
               startActivity(homeIntent);
            }
        });
    }
}