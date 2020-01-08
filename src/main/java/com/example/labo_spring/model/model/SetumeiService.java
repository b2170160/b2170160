package com.example.labo_spring.model.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class SetumeiService {

    @Autowired
    private SetumeiRepository repository;

    public Setumei findvoice(String user){
        return repository.selectvoice(user);
    }

    public Setumei findsyousai(String user){

        return repository.selectsyousai(user);
    }

    public void setumeiupdate(String syousai,String koe,String userId){
        var setumei = new Setumei(syousai,koe,userId);
        repository.setumeiupdate(setumei);
    }
}
