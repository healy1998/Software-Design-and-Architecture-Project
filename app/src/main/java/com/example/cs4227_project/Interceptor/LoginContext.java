package com.example.cs4227_project.Interceptor;

public class LoginContext {
    public void execute(String request){
        System.out.println("Login Status: " + request);
    }
}
