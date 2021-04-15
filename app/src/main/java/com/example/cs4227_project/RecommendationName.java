package com.example.cs4227_project;

import java.util.ArrayList;

public class RecommendationName implements Recommendation{

    public ArrayList<String> nameList;

    public RecommendationName(ArrayList<String> name) {
        nameList = name;
    }

    @Override
    public ArrayList<ArrayList<String>> accept(RecommendationVisitor recommendationVisitor){
        recommendationVisitor.visit(this);
        return  new ArrayList<ArrayList<String>>();
    }

}
