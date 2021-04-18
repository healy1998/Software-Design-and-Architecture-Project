package com.example.cs4227_project.Interceptor;

public class FilterManager {
    FilterChain filterChain;

    public FilterManager(LoginContext loginContext){
        filterChain = new FilterChain();
        filterChain.setATarget(loginContext);
    }
    public void setFilter(Interceptor interceptor){
        filterChain.addFilter(interceptor);
    }

    public void filterRequest(String request){
        filterChain.execute(request);
    }
}