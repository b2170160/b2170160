package com.example.labo_spring.model.model;

public class Retrospectuser {

    private String user_name;


    public Retrospectuser() {
        this.user_name = "";
    }

    public Retrospectuser(String user_name) {

        this.user_name = user_name;
    }

    public void setUser_name(String user_name){ this.user_name = user_name; }

    public String getUser_name(){ return  user_name; }

}