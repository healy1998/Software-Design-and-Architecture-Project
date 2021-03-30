package com.example.software_design_and_architecture_project;

public interface RecommendationVisitor {
    public void visit(Action action);
    public void visit(Comedy comedy);
}
