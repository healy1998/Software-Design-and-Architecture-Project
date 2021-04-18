package com.example.cs4227_project.PosterAdapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PosterAdapter {

    public Bitmap adaptIntentToBitMap(Intent intent){
        Bundle bundle = intent.getBundleExtra("com.example.cs4227_project.watchIntent");

        ArrayList<String> url = new ArrayList<String>();
        int position = 0;

        position = bundle.getInt("position");
        url = bundle.getStringArrayList("clickedUrl");

        String calledUrl = bundle.getStringArrayList("clickedUrl").get(position);

        Bitmap map = getBitmapFromURL(calledUrl);

        return map;
    }

    public String adaptIntentToTitle(Intent intent){
        Bundle bundle = intent.getBundleExtra("com.example.cs4227_project.watchIntent");

        ArrayList<String> imageName = new ArrayList<String>();
        int position = 0;

        position = bundle.getInt("position");

        String name = bundle.getStringArrayList("clickedName").get(position);

        return name;
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
