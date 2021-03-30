package com.example.software_design_and_architecture_project;

public class Action implements Recommendation{
    @Override
    public void accept(RecommendationVisitor recommendationVisitor){
        recommendationVisitor.visit(this);
    }
}
