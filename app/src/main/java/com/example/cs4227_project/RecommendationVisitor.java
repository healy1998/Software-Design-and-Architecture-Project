package com.example.cs4227_project;

public interface RecommendationVisitor {
    public void visit(RecommendationName name);
    public void visit(RecommendationImage image);
    public void visit(RecommendationFull full);
}
