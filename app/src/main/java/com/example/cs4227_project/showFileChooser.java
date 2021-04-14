package com.example.cs4227_project;

public class showFileChooser implements Command{

    Uploader theUpload;

    public showFileChooser(Uploader newUpload)
    {
        theUpload = newUpload;
    }

    @Override
    public void execute()
    {
        theUpload.showFileChooser();
    }
}
