package com.example.cs4227_project;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.storage.UploadTask;

public class UploadMediator implements Mediator{

    public static ProgressDialog progressDialog;
    private Context applicationContext;

    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
    {
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_LONG).show();
    }
    public void onFailure(@NonNull Exception e)
    {
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    }
    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot)
    {
        double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
        progressDialog.setMessage(((int) progress) + "% Uploaded...");
    }

    public Context getApplicationContext() {
        return applicationContext;
    }
}
