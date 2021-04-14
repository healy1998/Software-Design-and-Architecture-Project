package com.example.cs4227_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home_Activity extends AppCompatActivity implements RecyclerAdapter.ListItemClickListener {
    private ArrayList<String> recommendations;
    public static ArrayList<String> recommendationNames;
    public static ArrayList<String> recommendationImages;
    private RecyclerView recyclerView;
    private Boolean actionCheck = false;
    private Boolean comedyCheck = false;
    private Boolean disneyCheck = false;
    private Boolean scifiCheck = false;
    private Boolean horrorCheck = false;
    private Boolean romanceCheck = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button upload;

        /*
        //User
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        String Uuid;
        FirebaseAuth auth = FirebaseAuth.getInstance();
        Uuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = database.child(Uuid);
        //System.out.println("HERE");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child("Action").getValue(String.class).matches("Action")){
                        System.out.println("ACTION");
                        actionCheck = true;
                    }
                    else if(snapshot.child("Comedy").getValue(String.class).matches("Comedy")){
                        System.out.println("COMEDY");
                        comedyCheck = true;
                    }
                    else if(snapshot.child("Horror").getValue(String.class).matches("Horror")){
                        System.out.println("HORROR");
                        horrorCheck = true;
                    }
                    else if(snapshot.child("Disney").getValue(String.class).matches("Disney")){
                        System.out.println("DISNEY");
                        disneyCheck = true;
                    }
                    else if(snapshot.child("Scifi").getValue(String.class).matches("Scifi")){
                        System.out.println("SCIFI");
                        scifiCheck = true;
                    }
                    else if(snapshot.child("Romance").getValue(String.class).matches("Romance")){
                        System.out.println("ROMANCE");
                        scifiCheck = true;
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


        //Reading images from firebase
        if(actionCheck){
            //get file
            Recommendation recommendation = new RecommendationFull(); //pass file here
            recommendation.accept(new RecommendationDisplayVisitor());

        }

        //Button recommendation;
        setContentView(R.layout.activity_home_);
        upload = findViewById(R.id.upload);
        //recommendation = findViewById(R.id.recommendationsButton);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uploadIntent = new Intent(Home_Activity.this,
                        Upload_Activity.class);
                startActivity(uploadIntent);
            }
        });


        recyclerView = findViewById(R.id.RecommendationView);
        recommendations = new ArrayList<>();

        setInfo();
        setAdapter();
    }

    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(recommendations, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onListItemClick(int position) {
        Intent watchIntent = new Intent(Home_Activity.this,
                watch_Activity.class);
        startActivity(watchIntent);
    }

    private void setInfo() {
        recommendations.add("Godzilla vs King Kong");
        recommendations.add("The Green Mile");
        recommendations.add("Shawshank Redemption");
        recommendations.add("Avengers: End Game");
        recommendations.add("How to Train Your Dragon");
        recommendations.add("The Godfather");
    }

    public void setNameInfo(String name){
        recommendations.add(name);
    }

    public void setImageInfo(String url){
        recommendations.add(url);
    }
}