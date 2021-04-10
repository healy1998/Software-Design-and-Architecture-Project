package com.example.cs4227_project;

public class RecommendationDisplayVisitor implements RecommendationVisitor{

    @Override
    public void visit(Action action){
        System.out.print("Showing action film");
        // If user has checked action
        // Show film on recommendation page
    }

    @Override
    public void visit(Comedy comedy){
        System.out.print("Showing Comedy film");
        // If user has checked Comedy
        // Show film on recommendation page
    }
}
