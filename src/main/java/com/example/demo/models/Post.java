package com.example.demo.models;

import com.example.demo.database.PostDB;
import org.springframework.stereotype.Component;


public class Post {

    public String title;
    public String description;
    public String datetime;
    public String author;

    public Post(String title, String description, String datetime, String author) {
        this.title = title;
        this.description = description;
        this.datetime = datetime;
        this.author = author;

    }


    public String getTitle() {
        return this.title;
    }

}
