package com.example.cs4227_project.Interceptor;


import java.util.ArrayList;
import java.util.List;

public class FilterChain {
    private List<Interceptor> interceptorList = new ArrayList<Interceptor>();
    private LoginContext aTarget;

    public void addFilter(Interceptor interceptor){
        interceptorList.add(interceptor);
    }

    public void execute(String request){
        for (Interceptor interceptor : interceptorList) {
            interceptor.execute(request);
        }
        aTarget.execute(request);
    }

    public void setATarget(LoginContext aTarget){
        this.aTarget = aTarget;
    }
}