package com.example.cs4227_project;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class RecommendationFull implements Recommendation{
    public ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
    //public String imageName = "";
    //public String url = "";
    public ArrayList<String> imageName = new ArrayList<String>();
    public ArrayList<String> url = new ArrayList<String>();
    public RecommendationImage image = new RecommendationImage(url);
    public RecommendationName name = new RecommendationName(imageName);
    public StorageReference file;

    public RecommendationFull(ArrayList<ArrayList<String>> list, ArrayList<String> names, ArrayList<String> urls){
        this.file = file;
        imageName = names;
        url = urls;
        this.list = list;
    }

    @Override
    public ArrayList<ArrayList<String>> accept(RecommendationVisitor recommendationVisitor){
        image = new RecommendationImage(url);
        name = new RecommendationName(imageName);
        image.accept(recommendationVisitor);
        name.accept(recommendationVisitor);
        ArrayList<ArrayList<String>> returnList = recommendationVisitor.visit(this);
        System.out.println("RETURNED: " + returnList.size());
        return returnList;
    }
}
