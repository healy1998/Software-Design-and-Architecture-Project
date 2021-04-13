package com.example.cs4227_project;

public class loginInterceptor implements interceptor{
    String message;

    public loginInterceptor() {

    }
    @Override
    public String toString() {
        return message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public loginInterceptor(String message) {
        this.message = message;
    }

}
