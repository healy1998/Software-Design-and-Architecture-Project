package com.example.cs4227_project;

public class Action implements Recommendation{
    @Override
    public void accept(RecommendationVisitor recommendationVisitor){
        recommendationVisitor.visit(this);
    }
}