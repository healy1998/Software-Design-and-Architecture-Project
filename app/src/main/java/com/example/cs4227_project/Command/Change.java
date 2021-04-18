package com.example.cs4227_project.Command;

public class Change {
    private Command FileChooserCommand, UploadFileCommand;

    public Change(Command Choose, Command Upload) {
        FileChooserCommand = Choose;
        UploadFileCommand = Upload;
    }

    public void changeToFile() {
        FileChooserCommand.execute();
    }

    public void changeToUpload() {
        UploadFileCommand.execute();
    }
}
