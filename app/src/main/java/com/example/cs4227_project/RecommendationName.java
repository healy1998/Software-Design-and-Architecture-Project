package com.example.cs4227_project;

public class RecommendationName implements Recommendation{

    public String name;

    /*public RecommendationImage(String name){
        this.name = name;
    */

    @Override
    public void accept(RecommendationVisitor recommendationVisitor){
        recommendationVisitor.visit(this);
    }

}
