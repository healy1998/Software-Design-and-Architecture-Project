package com.example.cs4227_project;

public class profileSetup {
    String name, age, horror, comedy, scifi,action,romance,disney;



    public profileSetup(ProfileBuilder profileBuilder) {
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

    @Override
    public String toString() {
        return "profileSetup{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", horror='" + horror + '\'' +
                ", comedy='" + comedy + '\'' +
                ", scifi='" + scifi + '\'' +
                ", action='" + action + '\'' +
                ", romance='" + romance + '\'' +
                ", disney='" + disney + '\'' +
                '}';
    }

    public static class ProfileBuilder{
        String name, age, horror, comedy, scifi,action,romance,disney;
        public ProfileBuilder(String name, String age){
            this.name =  name;
            this.age = age;
        }
        public ProfileBuilder withOptionHorror(String horror){
            this.horror = horror;
            return this;
        }
        public ProfileBuilder withOptionComedy(String comedy){
            this.comedy = comedy;
            return this;
        }
        public ProfileBuilder withOptionScifi(String scifi){
            this.scifi = scifi;
            return this;
        }
        public ProfileBuilder withOptionAction(String action){
            this.action = action;
            return this;
        }
        public ProfileBuilder withOptionRomance(String romance){
            this.romance = romance;
            return this;
        }
        public ProfileBuilder withOptionDisney(String disney){
            this.disney = disney;
            return this;
        }
        public profileSetup buildProfile(){
           return new profileSetup(this);
        }

    }
}