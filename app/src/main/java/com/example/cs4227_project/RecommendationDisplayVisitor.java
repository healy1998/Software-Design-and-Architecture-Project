package com.example.cs4227_project;

public class RecommendationDisplayVisitor implements RecommendationVisitor{

    @Override
    public void visit(RecommendationName name){
        Home_Activity.recommendationNames.add(name.name);
    }

    @Override
    public void visit(RecommendationImage image){
        Home_Activity.recommendationImages.add(image.imageURL);
    }

    @Override
    public void visit(RecommendationFull full){

    }
}
