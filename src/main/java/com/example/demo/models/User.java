package com.example.demo.models;

import com.example.demo.database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;


public class User {
    int id;
    String name;
    String email;
    String password;
    Database db = new Database();
    public User() {
        System.out.println("Зарегистрируйтесь в системе");
    }
    public User(int id) {
        try{
            ResultSet user_info = db.GetNameOfCurrentUser(id);
            this.id = id;
            while(user_info.next()) {
                this.name = user_info.getString("name");
                this.email = user_info.getString("email");
                this.password = user_info.getString("password");
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
