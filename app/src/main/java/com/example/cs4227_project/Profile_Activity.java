package com.example.cs4227_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile_Activity extends AppCompatActivity {

    final FirebaseAuth auth = FirebaseAuth.getInstance();
    TextView name, age;
    Button next;
    CheckBox horror, comedy, scifi, action, romance, disney;
    String dis,sc,roma,act,hr,cmdy, a, n;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("UserInfo");
                profileSetup profileSetup = new profileSetup();

                if (horror.isChecked()){
                    hr = "Horror";
                    profileSetup.setHorror(hr);
                }
                if (comedy.isChecked()){
                    cmdy = "Comedy";
                    profileSetup.setComedy(cmdy);
                }
                if (scifi.isChecked()){
                    sc = "Scifi";
                    profileSetup.setScifi(sc);
                }
                if (action.isChecked()){
                    act  = "Action";
                    profileSetup.setAction(act);
                }
                if (romance.isChecked()){
                    roma = "Romance";
                    profileSetup.setRomance(roma);
                }
                if (disney.isChecked()){
                    dis = "Disney";
                    profileSetup.setDisney(dis);
                }
                n = name.getEditableText().toString();
                profileSetup.setName(n);
                a = age.getEditableText().toString();
                profileSetup.setAge(a);
                profileSetup profileSetup1= new profileSetup(n,a,hr,cmdy,sc,act,roma,dis);
                myRef.setValue(profileSetup1);
            }
        });
    }

}