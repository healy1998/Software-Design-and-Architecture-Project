package com.example.cs4227_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Home_Activity extends AppCompatActivity {
    public ArrayList<ArrayList<String>> recommendations = new ArrayList<ArrayList<String>>();
    public  ArrayList<String> imageName = new ArrayList<String>();
    public ArrayList<String> url = new ArrayList<String>();
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

        //user genres
        System.out.println("'test'");
        String profset = getIntent().getStringExtra("profileSetup");
        String[] genres = profset.split(", ");
        System.out.println(profset);
        System.out.println(genres[5]);

        for(int i=0;i < genres.length;i++){
            String str = genres[i];
            switch(str){
                case "horror='Horror'":
                    horrorCheck = true;
                    break;
                case "comedy='Comedy'":
                    comedyCheck = true;
                    break;
                case "scifi='Scifi'":
                    scifiCheck = true;
                    break;
                case "action='Action'":
                    actionCheck = true;
                    break;
                case "romance='Romance'":
                    romanceCheck = true;
                    break;
                case "disney='Disney'}":
                    disneyCheck = true;
                    break;
                default:
                    System.out.println("nothing");
            }
        }

        System.out.println(disneyCheck);
        recyclerView = findViewById(R.id.RecommendationView);

        //Reading images from firebase
        if(actionCheck){
            //get file
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference listRef = storage.getReference().child("Action");
            makeArrays(listRef);
            //Recommendation recommendation = new RecommendationFull(listRef, recommendations); //pass file here
            //recommendations = recommendation.accept(new RecommendationDisplayVisitor());
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
        //System.out.println(recommendations.get(0).get(0));
        //recyclerView = findViewById(R.id.RecommendationView);
        //recommendations = new ArrayList<>();
        //setInfo();
        //System.out.println(recommendations.get(0).get(0));
        //setAdapter();
    }

    public void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(recommendations);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setInfo() {
        /*recommendations.add("Godzilla vs King Kong");
        recommendations.add("The Green Mile");
        recommendations.add("Shawshank Redemption");
        recommendations.add("Avengers: End Game");
        recommendations.add("How to Train Your Dragon");
        recommendations.add("The Godfather");*/
    }

    /*public void setNameInfo(String name){
        recommendations.add(name);
    }

    public void setImageInfo(String url){
        recommendations.add(url);
    }*/

    public void makeArrays(StorageReference file){
        int count = 0;
        file.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for(StorageReference item : listResult.getItems()){
                            item.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    int count;
                                    Uri uri = task.getResult();
                                    url.add(uri.toString());
                                    System.out.println("URL: " + uri.toString());
                                    if(url.size() == listResult.getItems().size()){
                                        startVisitor();
                                    }
                                }
                            });
                            imageName.add(item.toString());
                            System.out.println("NAME: " + item.toString());
                        }
                        //System.out.println("URL SIZE: " + imageName.size());
                        //image = new RecommendationImage(url);
                        //name = new RecommendationName(imageName);
                    }
                }).addOnCompleteListener(new OnCompleteListener<ListResult>() {
            @Override
            public void onComplete(@NonNull Task<ListResult> task) {
                /*Recommendation recommendation = new RecommendationFull(recommendations, imageName, url ); //pass file here
                recommendations = recommendation.accept(new RecommendationDisplayVisitor());
                recyclerView = findViewById(R.id.RecommendationView);
                setAdapter();*/
            }
            //return recommendationVisitor.visit(this);
        });
    }

    public void startVisitor(){
        Recommendation recommendation = new RecommendationFull(recommendations, imageName, url ); //pass file here
        recommendations = recommendation.accept(new RecommendationDisplayVisitor());
        recyclerView = findViewById(R.id.RecommendationView);
        setAdapter();
    }
}