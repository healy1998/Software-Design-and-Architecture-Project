package com.example.software_design_and_architecture_project;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private ArrayList<String> recommendations;

    public RecyclerAdapter(ArrayList<String> recommendations){
        this.recommendations = recommendations;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameText;
        private ImageView image;

        public MyViewHolder(final View view) {
            super(view);
            nameText = view.findViewById(R.id.RecommendationName);
            image = view.findViewById(R.id.RecommendationImage);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommendation_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        //Drawable d = Drawable.createFromPath("./res/sample.jpeg");
        String name = recommendations.get(position);
        holder.nameText.setText(name);
        holder.image.setBackgroundResource(R.drawable.test_background);
        holder.image.setImageResource(R.drawable.test_foreground);
    }

    @Override
    public int getItemCount() {
        return recommendations.size();
    }
}
