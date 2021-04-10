package com.example.cs4227_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Home_Activity extends AppCompatActivity {
    private ArrayList<String> recommendations;
    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button upload;
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
        RecyclerAdapter adapter = new RecyclerAdapter(recommendations);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setInfo() {
        recommendations.add("Godzilla vs King Kong");
        recommendations.add("The Green Mile");
        recommendations.add("Shawshank Redemption");
        recommendations.add("Avengers: End Game");
        recommendations.add("How to Train Your Dragon");
        recommendations.add("The Godfather");
    }
}