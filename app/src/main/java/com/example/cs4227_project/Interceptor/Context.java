package com.example.cs4227_project.Interceptor;

public class Context {
    FilterManager fmanager;

    public void setFilterManager(FilterManager filterManager){
        this.fmanager = filterManager;
    }

    public void sendRequest(String request){
        fmanager.filterRequest(request);
    }
}