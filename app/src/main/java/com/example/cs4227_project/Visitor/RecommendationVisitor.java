package com.example.cs4227_project.Visitor;

import java.util.ArrayList;

public interface RecommendationVisitor {
    public void visit(RecommendationName name);
    public void visit(RecommendationImage image);
    public ArrayList<ArrayList<String>> visit(RecommendationFull full);
}
