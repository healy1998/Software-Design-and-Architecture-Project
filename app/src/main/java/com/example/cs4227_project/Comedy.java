package com.example.cs4227_project;

public class Comedy implements Recommendation {
    @Override
    public void accept(RecommendationVisitor recommendationVisitor) {
        recommendationVisitor.visit(this);
    }
}