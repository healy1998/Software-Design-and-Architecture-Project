package com.example.cs4227_project;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;


public class Upload_Activity extends AppCompatActivity implements View.OnClickListener{



    public static final int PICK_IMAGE_REQUEST = 234;
    public static final String TAG = "UploadActivity";
    public static final int REQUEST_CODE = 1;
    public ImageView imageView;
    public StorageReference storageReference;
    public Button buttonChoose;
    public Button buttonUpload;
    public EditText name;
    public EditText genre;
    public Uri filePath;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        storageReference = FirebaseStorage.getInstance().getReference();

        imageView = (ImageView) findViewById(R.id.imageView);
        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        name = (EditText) findViewById(R.id.name);
        genre = (EditText) findViewById(R.id.genre);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        //System.out.println("here") for debugging
        System.out.println("here");
        if(view == buttonChoose){
            //open file chooser
            System.out.println("here");
            //showFileChooser();

            //attempt at using the command pattern
            Uploader newUpload = UploadManager.getUpload();
            //crashes here
            System.out.println("here");

            showFileChooser showFileChooserCommand = new showFileChooser(newUpload);
            System.out.println("here");
            UploaderButton showFileChooserPressed = new UploaderButton(showFileChooserCommand);
            System.out.println("here");
            showFileChooserPressed.press();
            System.out.println("here");

        }
        else if(view == buttonUpload){
            //upload file to firebase storage

            //uploadFile();
            Uploader newUpload2 = UploadManager.getUpload();

            uploadFile uploadFileCommand = new uploadFile(newUpload2);

            UploaderButton uploadFilePressed = new UploaderButton(uploadFileCommand);

            uploadFilePressed.press();
        }
    }

    private void verifyPermissions(){
        Log.d(TAG, "verifyPermissions: asking user for permissions");
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED)
        {
            Uploader newUpload = UploadManager.getUpload();

            showFileChooser onCommand = new showFileChooser(newUpload);

            UploaderButton onPressed = new UploaderButton(onCommand);

            onPressed.press();
        }else{
            ActivityCompat.requestPermissions(Upload_Activity.this, permissions, REQUEST_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        verifyPermissions();
    }
}