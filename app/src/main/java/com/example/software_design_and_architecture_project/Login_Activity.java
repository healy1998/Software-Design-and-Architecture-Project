package com.example.software_design_and_architecture_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {

    private EditText emailTextView, passwordTextView;
    private Button Btn;
    private ProgressBar progressbar;
    private  String message;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // taking instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.password);
        Btn = findViewById(R.id.login);
        progressbar = findViewById(R.id.progressbar);

        // Set on Click Listener on Sign-in button
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginUserAccount();

            }
        });
    }

    private void loginUserAccount()
    {

        // show the visibility of progress bar to show loading
        progressbar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(email)) {
            message = "Please enter Email" ;
            ShowMessage(message);
            return;
        }

        if (TextUtils.isEmpty(password)) {
            message = "Please enter Password" ;
            ShowMessage(message);
            return;
        }

        // signin existing user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    // hide the progress bar
                                    progressbar.setVisibility(View.GONE);
                                    Toast.makeText(Login_Activity.this,"successfully logged in!", Toast.LENGTH_SHORT).show();
                                    // if sign-in is successful
                                    // intent to home activity
                                    Intent intent = new Intent(Login_Activity.this, Profile_Activity.class);
                                    startActivity(intent);
                                }

                                else {
                                        message = "login failed" ;
                                        ShowMessage(message);
                                        // hide the progress bar
                                        progressbar.setVisibility(View.GONE);
                                }
                            }
                        });
    }
    protected void ShowMessage(String message){
        AlertDialog show = new AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage(message)
                .setNeutralButton("OK", null)
                .show();
    }
}