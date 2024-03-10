package com.example.demo.models;

public class Post {

    public String title;
    public String description;
    public String datetime;

    public Post(String title, String description, String datetime) {
        this.title = title;
        this.description = description;
        this.datetime = datetime;
    }

    public String getTitle() {
        return this.title;
    }

}
