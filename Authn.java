package com.example.wsp.spring.model.model;

public class Authn {
    private String userid;
    private String passphrase;
    private String userName;
    private String userRole;

    public Authn(String userid,String passphrase,String userName,String userRole){
        this.userid=userid;
        this.passphrase=passphrase;
        this.userName=userName;
        this.userRole=userRole;
    }
    public Authn(){
        this.userid="";
        this.passphrase="";
        this.userName="";
        this.userRole="";
    }
    public String getUserId(){
        return userid;
    }
    public String getPassphrase(){
        return passphrase;
    }
    public String getUserName(){
        return userName;
    }
    public String getUserRole(){
        return  userRole;
    }
    public void setUser_id(String id){
        userid=id;
    }
    public void setPassphrase(String pass){
        passphrase=pass;
    }
    public void setUserName(String Name){
        userName=Name;
    }
    public void setUserRole(String Role){
        userRole=Role;
    }
}
