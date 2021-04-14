package com.example.cs4227_project;

public class uploadFile implements Command {

    Uploader theUpload;

    public uploadFile(Uploader newUpload)
    {
        theUpload = newUpload;
    }

    @Override
    public void execute()
    {
        theUpload.uploadFile();
    }
}
