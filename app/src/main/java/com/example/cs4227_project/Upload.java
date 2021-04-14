package com.example.cs4227_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static com.example.cs4227_project.Upload_Activity.PICK_IMAGE_REQUEST;

public class Upload extends AppCompatActivity implements Uploader{
    private String message;
    public StorageReference storageReference;
    public EditText name;
    public EditText genre;
    public Uri filePath;

    @Override
    public void showFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction((Intent.ACTION_GET_CONTENT));
        startActivityForResult(Intent.createChooser(intent, "Select an Image"), PICK_IMAGE_REQUEST);

    }

    @Override
    public void uploadFile() {

        String filmName, filmGenre;
        filmName = name.getText().toString();
        filmGenre = genre.getText().toString();

        if (TextUtils.isEmpty(filmName)) {
            message = "Please enter Film Name" ;
            ShowMessage(message);
            return;
        }

        if (TextUtils.isEmpty(filmGenre)){
            message = "Please enter Genre of Film";
            ShowMessage(message);
            return;
        }

        if(filePath != null) {

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            /*GenreFactory genreFactory = new GenreFactory();
            Genre movie = genreFactory.getGenre(filmGenre);
            genre.setText(new StringBuilder().append(movie.type()).toString());
            genre.toString();*/
            StorageReference riversRef = storageReference.child(filmGenre +"/" + filmName +".jpg");

            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage(((int) progress) + "% Uploaded...");
                        }
                    })
            ;
        }else{
            //display error toast
        }

    }

    protected void ShowMessage(String message){
        AlertDialog show = new AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage(message)
                .setNeutralButton("OK", null)
                .show();
    }
}
