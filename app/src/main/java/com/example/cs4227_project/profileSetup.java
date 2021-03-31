package com.example.cs4227_project;

public class profileSetup {
    String name, age, horror, comedy, scifi,action,romance,disney;

    public profileSetup(String name, String age, String horror, String comedy, String scifi, String action, String romance, String disney) {
        this.name = name;
        this.age = age;
        this.horror = horror;
        this.comedy = comedy;
        this.scifi = scifi;
        this.action = action;
        this.romance = romance;
        this.disney = disney;
    }

    public profileSetup() {
    }

    public String getRomance() {
        return romance;
    }

    public void setRomance(String romance) {
        this.romance = romance;
    }

    public String getDisney() {
        return disney;
    }

    public void setDisney(String disney) {
        this.disney = disney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHorror() {
        return horror;
    }

    public void setHorror(String horror) {
        this.horror = horror;
    }

    public String getComedy() {
        return comedy;
    }

    public void setComedy(String comedy) {
        this.comedy = comedy;
    }

    public String getScifi() {
        return scifi;
    }

    public void setScifi(String scifi) {
        this.scifi = scifi;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

