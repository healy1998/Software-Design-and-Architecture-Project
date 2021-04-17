package com.example.cs4227_project.Visitor;

import java.util.ArrayList;

public interface Recommendation {
    public ArrayList accept(RecommendationVisitor recommendationVisitor);
}
