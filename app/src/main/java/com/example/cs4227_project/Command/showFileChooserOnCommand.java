package com.example.cs4227_project.Command;


import com.example.cs4227_project.Upload_Activity;

public class showFileChooserOnCommand implements Command {
    public Upload_Activity.FileChooser myFileChooser;

    public showFileChooserOnCommand(Upload_Activity.FileChooser F) {
        myFileChooser = F;
    }

    public void execute() {
        myFileChooser.showFileChooser();
    }
}
