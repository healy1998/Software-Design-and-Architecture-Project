package com.example.software_design_and_architecture_project;

public class RecommendationDemo {
    public static void main(String[] args){
        String checkbox = "Action";
        Recommendation recommendation;
        if(checkbox.matches("Action")){
            recommendation = new Action();
            recommendation.accept(new RecommendationDisplayVisitor());
        }
    }
}
