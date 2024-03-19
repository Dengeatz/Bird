package com.example.demo.models;

import com.example.demo.Mudak;
import com.example.demo.database.Database;
import com.example.demo.database.UserDB;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;


public class User {
    public int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min=2,max=30,message = "Имя не может быть меньше 2 символов или больше 30")
    public String name;
    @Email
    public String email;
    @Min(value=6, message="Пароль не может быть меньше 6 символов")
    public String password;

    public User() {
        this.name = null;
        this.email = null;
        this.password = null;

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

    public String getName() {
        return name;
    }
    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
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
