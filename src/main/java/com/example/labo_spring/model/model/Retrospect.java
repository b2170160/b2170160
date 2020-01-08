package com.example.labo_spring.model.model;

        import java.time.LocalDateTime;

public class Retrospect {

    private String page_name;
    private String text;
    private LocalDateTime postedAt;

    public Retrospect(){
        this.page_name="";
        this.text="";
        this.postedAt=LocalDateTime.MIN;
    }

    public Retrospect(String text,String page_name) {
        this.page_name = page_name;
        this.text = text;
        this.postedAt = LocalDateTime.now();
        this.print();
    }

    public void setPostedAt(LocalDateTime postedAt){
        this.postedAt=postedAt;
    }

    public void setText(String text){
        this.text=text;
    }

    public void setPage_name(String page_name){this.page_name=page_name;}

    public String getText() {
        return text;
    }

    public LocalDateTime getPostedAt(){
        return postedAt;
    }

    public String getPage_name(){return page_name;}

    public void print(){
        System.out.println(String.join(",",super.toString(),text,postedAt.toString()));
    }
}