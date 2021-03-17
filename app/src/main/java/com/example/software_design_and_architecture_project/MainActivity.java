package com.example.software_design_and_architecture_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button loginbtn, registerbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginbtn = findViewById(R.id.loginBtn);
        registerbtn = findViewById(R.id.RegisterBtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button Clicked");

                Intent LoginActivityIntent = new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(LoginActivityIntent);

            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegActivityIntent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(RegActivityIntent);
            }
        });

    }
}