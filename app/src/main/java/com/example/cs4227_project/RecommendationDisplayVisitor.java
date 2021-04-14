package com.example.cs4227_project;

import android.content.Intent;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.NameList;

import java.util.ArrayList;

import javax.xml.namespace.QName;

public class RecommendationDisplayVisitor implements RecommendationVisitor{

    @Override
    public void visit(RecommendationName name){
        for(int i =0; i < name.nameList.size(); i++){
            String movieName = (name.nameList.get(i).split("/")[4]);
            movieName = movieName.replace(".jpg","");
            movieName = movieName.replace("%20"," ");
            System.out.println(movieName);
            name.nameList.set(i, movieName);
        }
    }

    @Override
    public void visit(RecommendationImage image){
    }

    @Override
    public ArrayList<ArrayList<String>> visit(RecommendationFull full){
        ArrayList<ArrayList<String>> recommendations = full.list;
        //System.out.println("SIZE: " + full.image.imageList.size());
        for(int i=0; i<full.name.nameList.size(); i++){
            recommendations.add(new ArrayList<String>());
            recommendations.get(i).add(full.name.nameList.get(i));
            recommendations.get(i).add(full.image.imageList.get(i));
        }
        return recommendations;
    }


}
