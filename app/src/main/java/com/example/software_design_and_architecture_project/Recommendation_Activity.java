package com.example.software_design_and_architecture_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Recommendation_Activity extends AppCompatActivity {
    private ArrayList<String> recommendations;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation_);
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
    }
}