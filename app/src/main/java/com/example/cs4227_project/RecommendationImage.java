package com.example.cs4227_project;

public class RecommendationImage implements Recommendation{

    public String imageURL;
    /*public RecommendationImage(String url){
        imageURL = url
    */

    @Override
    public void accept(RecommendationVisitor recommendationVisitor){
        recommendationVisitor.visit(this);
    }

}