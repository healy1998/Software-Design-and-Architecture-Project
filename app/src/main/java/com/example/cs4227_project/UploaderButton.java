package com.example.cs4227_project;

public class UploaderButton {

    Command theCommand;

    public UploaderButton(Command newCommand)
    {
        theCommand = newCommand;
    }

    public void press()
    {
        theCommand.execute();
    }
}
