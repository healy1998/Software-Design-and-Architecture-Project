package com.example.cs4227_project.Mediator;

import com.example.cs4227_project.Upload_Activity;

public class UploadMediator implements Mediator {

    public void DisplayMessage(String message) {
        Upload_Activity.show
                .setTitle("Message")
                .setMessage(message)
                .setNeutralButton("OK", null)
                .show();
    }
}

