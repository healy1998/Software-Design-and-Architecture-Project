package com.example.cs4227_project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private ArrayList<ArrayList<String>> recommendations;

    public RecyclerAdapter(ArrayList<ArrayList<String>> recommendations){
        this.recommendations = recommendations;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
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
        //Drawable d = LoadImageFromWebURL(recommendations.get(position).get(1));
        String name = recommendations.get(position).get(0);
        holder.nameText.setText(name);
        //holder.image.setBackgroundResource(R.drawable.test_background);
        //holder.image.setImageResource(R.drawable.test_foreground);
        //System.out.println(recommendations.get(position).get(1));
        Bitmap map = getBitmapFromURL(recommendations.get(position).get(1));
        holder.image.setImageBitmap(Bitmap.createScaledBitmap(map, 120, 120, false));
    }

    @Override
    public int getItemCount() {
        return recommendations.size();
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
}


