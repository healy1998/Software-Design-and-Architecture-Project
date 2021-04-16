package com.example.cs4227_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class watch_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_);
        Intent watchIntent = getIntent();
        ImageView posterImage = (ImageView) findViewById(R.id.posterView);
        TextView posterText = (TextView) findViewById(R.id.posterTitleText);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        adaptIntent(watchIntent, posterText, posterImage);
    }

    public void adaptIntent(Intent intent, TextView postText, ImageView postImage){
        Bundle bundle = intent.getBundleExtra("com.example.cs4227_project.watchIntent");

        ArrayList<String> imageName = new ArrayList<String>();
        ArrayList<String> url = new ArrayList<String>();
        int position = 0;

        System.out.println(position + "initalized");

        position = bundle.getInt("position");
        System.out.println(position + "after");
        imageName = bundle.getStringArrayList("clickedName");
        url = bundle.getStringArrayList("clickedUrl");

        System.out.println(imageName.get(1) + "after");
        System.out.println(url.get(1) + "after");

        String name = bundle.getStringArrayList("clickedName").get(position);
        String calledUrl = bundle.getStringArrayList("clickedUrl").get(position);

        Bitmap map = getBitmapFromURL(calledUrl);

        changePoster(name,map,postText,postImage);
    }

    public static void changePoster(String postName, Bitmap postMap, TextView tView, ImageView iView) {
        tView.setText(postName);
        iView.setImageBitmap(postMap);
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