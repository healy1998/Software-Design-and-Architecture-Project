package com.example.cs4227_project.Command;

import com.example.cs4227_project.Upload_Activity;

public class uploadFileOnCommand implements Command {
    public Upload_Activity.Upload myUpload;

    public uploadFileOnCommand(Upload_Activity.Upload U) {
        myUpload = U;
    }

    public void execute() {
        myUpload.uploadFile();
    }
}
