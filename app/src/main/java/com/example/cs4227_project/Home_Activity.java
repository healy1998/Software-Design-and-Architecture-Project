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

import com.example.cs4227_project.Factory.ActionFactory;
import com.example.cs4227_project.Factory.Genre;
import com.example.cs4227_project.Factory.GenreFactory;
import com.example.cs4227_project.Visitor.Recommendation;
import com.example.cs4227_project.Visitor.RecommendationDisplayVisitor;
import com.example.cs4227_project.Visitor.RecommendationFull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Home_Activity extends AppCompatActivity implements RecyclerAdapter.ListItemClickListener{
    public ArrayList<ArrayList<String>> recommendations = new ArrayList<ArrayList<String>>();
    public  ArrayList<String> imageName = new ArrayList<String>();
    public ArrayList<String> url = new ArrayList<String>();
    public CountDownLatch countDownLatch = new CountDownLatch(1);
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
        FirebaseStorage storage = FirebaseStorage.getInstance();

        //user genres
        String profset = getIntent().getStringExtra("profileSetup");
        String[] genres = profset.split(", ");
        ArrayList<Genre> genresChosen = new ArrayList<Genre>();
        GenreFactory genreFactory = new GenreFactory();
        for(int i=0;i < genres.length;i++){
            String str = genres[i];
            str.replace("}","");
            if(str.contains("'null'") == false && str.contains("name") == false && str.contains("age") == false) {
                genresChosen.add(genreFactory.getGenre(str));
            }
        }

        recyclerView = findViewById(R.id.RecommendationView);


        for(int i = 0; i < genresChosen.size();i++){
            if(genresChosen.get(i).type() == "Action"){
                StorageReference listRef = storage.getReference().child("Action");
                makeArrays(listRef);
            }
            else if(genresChosen.get(i).type() == "Comedy"){
                StorageReference listRef = storage.getReference().child("Comedy");
                makeArrays(listRef);
            }
            else if(genresChosen.get(i).type() == "Horror"){
                StorageReference listRef = storage.getReference().child("Horror");
                makeArrays(listRef);
            }
            else if(genresChosen.get(i).type() == "Disney"){
                StorageReference listRef = storage.getReference().child("Disney");
                makeArrays(listRef);
            }
            else if(genresChosen.get(i).type() == "Sci-Fi"){
                StorageReference listRef = storage.getReference().child("Sci-fi");
                makeArrays(listRef);
            }
            else if(genresChosen.get(i).type() == "Romance"){
                StorageReference listRef = storage.getReference().child("Romance");
                makeArrays(listRef);
            }
        }


        System.out.println("AMOUNT OF GENRES CHOSEN: " + genresChosen.size());

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

    }

    public void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(recommendations, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onListItemClick(int position) {
        Bundle watchBundle = new Bundle(3);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent watchIntent = new Intent(Home_Activity.this,
                watch_Activity.class);
        watchBundle.putStringArrayList("clickedName", imageName);
        watchBundle.putStringArrayList("clickedUrl", url);
        watchBundle.putInt("position", position);
        watchIntent = watchIntent.putExtra("com.example.cs4227_project.watchIntent", watchBundle);
        startActivity(watchIntent);
    }

    private void setInfo() {

    }

    public void makeArrays(StorageReference file){
        int count = 0;
        file.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        countDownLatch = new CountDownLatch(listResult.getItems().size());
                        for(StorageReference item : listResult.getItems()){
                            item.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    int count;
                                    imageName.add(item.toString());
                                    //System.out.println("NAME: " + item.toString());
                                    Uri uri = task.getResult();
                                    url.add(uri.toString());
                                    //System.out.println("URL: " + uri.toString());
                                    if(url.size() == listResult.getItems().size()){
                                        startVisitor();
                                    }
                                    countDownLatch.countDown();
                                }
                            });
                        }
                    }
                });
    }

    public void startVisitor(){
        Recommendation recommendation = new RecommendationFull(recommendations, imageName, url ); //pass file here
        recommendations = recommendation.accept(new RecommendationDisplayVisitor());
        recyclerView = findViewById(R.id.RecommendationView);
        setAdapter();
    }
}