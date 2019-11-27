package com.example.labo_spring.model.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SignService {

    @Autowired
    private AuthnRepository repository;
    public boolean doSignIn(String userId,String passphrase){
        Authn n=repository.select(userId);
        if(n.getPassphrase().equals(passphrase)){
            return true;
        }
        else{
            return false;
        }
    }
    public String doName(String userId){
        var n=repository.select(userId);
        return n.getUserName();
    }
    public String doRole(String userId){
        var n=repository.select(userId);
        return n.getUserRole();
    }
}
