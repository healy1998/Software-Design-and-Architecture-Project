package com.example.cs4227_project;

import androidx.annotation.NonNull;

import com.google.firebase.storage.UploadTask;

public interface Mediator {

    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot);

    public void onFailure(@NonNull Exception e);

    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot);
}
