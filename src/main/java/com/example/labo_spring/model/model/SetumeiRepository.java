package com.example.labo_spring.model.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.jdbc.core.BeanPropertyRowMapper.newInstance;

@Repository
public class SetumeiRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public Setumei selectvoice(String setumei){
        var sql="select koe from setumei where user = ?";
        return jdbc.queryForObject(sql,newInstance(Setumei.class),setumei);
    }

    public Setumei selectsyousai(String setumei){
        var sql="select syousai from setumei where user = ?";
        return jdbc.queryForObject(sql,newInstance(Setumei.class),setumei);
    }

    public void setumeiupdate(Setumei setumei){
        var sql="update setumei set syousai = ?,koe = ? where user = ?";
        jdbc.update(sql,setumei.getSyousai(),setumei.getKoe(),setumei.getUser());

        System.out.println("sql:"+setumei.getSyousai()+" & "+setumei.getKoe());
    }

}
