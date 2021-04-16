package com.example.cs4227_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;


public class Upload_Activity extends AppCompatActivity implements View.OnClickListener {


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
    private ProgressDialog progressDialog;
    private AlertDialog.Builder show;
    private ArrayList<String> possibleGenres;

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

        possibleGenres= new ArrayList<String>();
        possibleGenres.add("Action");
        possibleGenres.add("Comedy");
        possibleGenres.add("Disney");
        possibleGenres.add("Horror");
        possibleGenres.add("Romance");
        possibleGenres.add("Sci-Fi");


        progressDialog = new ProgressDialog(this);
        show = new AlertDialog.Builder(this);
    }

    class FileChooser {
        public void showFileChooser() {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction((Intent.ACTION_GET_CONTENT));
            startActivityForResult(Intent.createChooser(intent, "Select an Image"), PICK_IMAGE_REQUEST);
        }
    }


    class Upload{
        public void uploadFile() {
            String filmName, filmGenre;
            filmName = name.getText().toString();
            filmGenre = genre.getText().toString();

            if (TextUtils.isEmpty(filmName)) {
                message = "Please enter Film Name";
                UploadMediator um = new UploadMediator();
                um.DisplayMessage(message);
                return;
            }

            for(int i=0; i < possibleGenres.size(); i++) {
                if (filmGenre.equalsIgnoreCase(possibleGenres.get(i)))
                {
                    break;
                }
                else
                {
                    message = "Please enter a valid genre of film from one of these:" +
                            "\nAction" +
                            "\nComedy" +
                            "\nDisney" +
                            "\nHorror" +
                            "\nRomance" +
                            "\nSci-fi";
                    UploadMediator u = new UploadMediator();
                    u.DisplayMessage(message);
                    return;
                }
            }

            if (filePath != null) {

                progressDialog.setTitle("Uploading...");
                progressDialog.show();
                StorageReference riversRef = storageReference.child(filmGenre + "/" + filmName + ".jpg");

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
                                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                progressDialog.setMessage(((int) progress) + "% Uploaded...");
                            }
                        });
            } else {
                //display error toast
            }
        }
    }

    class Change {
        private Command FileChooserCommand, UploadFileCommand;

        public Change(Command Choose, Command Upload) {
            FileChooserCommand = Choose;
            UploadFileCommand = Upload;
        }

        void changeToFile() {
            FileChooserCommand.execute();
        }

        void changeToUpload() {
            UploadFileCommand.execute();
        }
    }

    class showFileChooserOnCommand implements Command {
        private FileChooser myFileChooser;

        public showFileChooserOnCommand(FileChooser F) {
            myFileChooser = F;
        }

        public void execute() {
            myFileChooser.showFileChooser();
        }
    }

    class uploadFileOnCommand implements Command {
        private Upload myUpload;

        public uploadFileOnCommand(Upload U) {
            myUpload = U;
        }

        public void execute() {
            myUpload.uploadFile();
        }
    }

    public void onClick(View view) {
        FileChooser testFileChooser = new FileChooser();
        showFileChooserOnCommand testSFC = new showFileChooserOnCommand(testFileChooser);
        Upload testUpload = new Upload();
        uploadFileOnCommand testUF = new uploadFileOnCommand(testUpload);
        Change testChange = new Change(testSFC, testUF);
        if (view == buttonChoose) {
            //open file chooser
            testChange.changeToFile();

        } else if (view == buttonUpload) {
            //upload file to firebase storage
            testChange.changeToUpload();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class UploadMediator implements Mediator {
        public void DisplayMessage(String message) {
            show
                    .setTitle("Message")
                    .setMessage(message)
                    .setNeutralButton("OK", null)
                    .show();
        }
    }
}