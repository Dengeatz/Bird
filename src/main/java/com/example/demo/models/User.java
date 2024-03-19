package com.example.demo.models;

import jakarta.validation.constraints.*;
import org.junit.validator.ValidateWith;

public class User {
    public int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min=2,max=30,message = "Имя не может быть меньше 2 символов или больше 30")
    public String name;
    @Email
    public String email;
    @Size(min=6,max=30,message = "Пароль не может быть меньше 6 символов или больше 30")
    public String password;



    public User() {

    }

    public User(String error) {
        this.name = error;
    }

    public User(int id,String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName(){
        return this.name;
    }

    public String getPassword(){
        return this.password;
    }

    public String getEmail(){
        return this.email;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;

    }
}
