package com.example.cs4227_project;

public class RecommendationFull implements Recommendation{

    //public RecommendationName image
    //public RecommendationName name
    /* public RecommendationFull(FirebaseFile file){
            imageName = file.name;
            url = file.url;
            image = new RecommendationImage(url);
            name = new RecommendationName(imageName);
        }
     */

    @Override
    public void accept(RecommendationVisitor recommendationVisitor){
        //image.accept(recommendationVisitor);
        //name.accept(recommendationVisitor)
        recommendationVisitor.visit(this);
    }
}
