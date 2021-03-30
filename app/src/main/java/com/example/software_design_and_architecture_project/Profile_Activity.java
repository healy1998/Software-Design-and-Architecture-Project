package com.example.software_design_and_architecture_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Profile_Activity extends AppCompatActivity {
    final FirebaseAuth auth = FirebaseAuth.getInstance();
    TextView name, age;
    Button next;
    CheckBox horror, comedy, scifi, action, romance, disney;
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    HashMap<String, String> interests = new HashMap<>();
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        System.out.println("hi i got here");
        //mDatabase = FirebaseDatabase.getInstance().getReference("interest").child();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("/cs4227-project-default-rtdb");

        name = findViewById(R.id.PersonName);
        age = findViewById(R.id.age);
        horror = findViewById(R.id.horror);
        comedy = findViewById(R.id.comedy);
        scifi = findViewById(R.id.scifi);
        action = findViewById(R.id.action);
        romance = findViewById(R.id.romance);
        disney = findViewById(R.id.disney);
        next = findViewById(R.id.next);


        next.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                System.out.println("hi i got here");
               if(horror.isChecked()) {
                   interests.put("horror","horror");
                }

                if(comedy.isChecked()) {
                    interests.put("comedy","comedy");
                }

                if(scifi.isChecked()) {
                    interests.put("scifi","scifi");
                }

                if(action.isChecked()) {
                    interests.put("action","action");
                }
                if(romance.isChecked()) {
                    interests.put("romance","romance");
                }

                if(disney.isChecked()) {
                    interests.put("disney","disney");
                }
                mDatabaseReference.setValue(interests);

                if(!TextUtils.isEmpty(name.toString()) && !TextUtils.isEmpty(age.toString())) {
                    Intent homeIntent = new Intent(Profile_Activity.this,
                            Home_Activity.class);
                    startActivity(homeIntent);
                }
                else {
                    Toast.makeText(Profile_Activity.this,"name or age field is empty", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}