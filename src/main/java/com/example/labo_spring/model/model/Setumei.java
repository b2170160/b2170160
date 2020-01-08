package com.example.labo_spring.model.model;

public class Setumei {

    private String syousai;
    private String koe;
    private String user;

    public Setumei(String syousai,String koe,String user){

        this.syousai=syousai;
        this.koe=koe;
        this.user=user;

    }

    public Setumei(){

        this.syousai="";
        this.koe="";
        this.user="";
    }

    public String getUser(){return user;}
    public String getSyousai(){return syousai;}
    public String getKoe(){return koe;}
    public void setUser(String id){user=id;}
    public void setSyousai(String syou){syousai=syou;}
    public void setKoe(String vois){koe=vois;}
}
