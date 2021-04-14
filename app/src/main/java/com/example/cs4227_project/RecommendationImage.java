package com.example.cs4227_project;

import java.util.ArrayList;

public class RecommendationImage implements Recommendation{

    public ArrayList<String> imageList;
    public RecommendationImage(ArrayList<String> url) {
        imageList = url;
    }

    @Override
    public ArrayList<ArrayList<String>> accept(RecommendationVisitor recommendationVisitor){
        recommendationVisitor.visit(this);
        return new ArrayList<ArrayList<String>>();
    }

}