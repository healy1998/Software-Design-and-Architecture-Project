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

import com.example.cs4227_project.PosterAdapter.PosterAdapter;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        PosterAdapter adapter = new PosterAdapter();

        Intent watchIntent = getIntent();
        ImageView posterImage = (ImageView) findViewById(R.id.posterView);
        TextView posterText = (TextView) findViewById(R.id.posterTitleText);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String name = adapter.adaptIntentToTitle(watchIntent);
        Bitmap map = adapter.adaptIntentToBitMap(watchIntent);

        changePoster(name,map,posterText,posterImage);
    }

    public static void changePoster(String postName, Bitmap postMap, TextView tView, ImageView iView) {
        tView.setText(postName);
        iView.setImageBitmap(postMap);
    }

}