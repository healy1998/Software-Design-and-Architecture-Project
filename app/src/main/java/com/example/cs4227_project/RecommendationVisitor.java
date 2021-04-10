package com.example.cs4227_project;

public interface RecommendationVisitor {
    public void visit(Action action);
    public void visit(Comedy comedy);
}
