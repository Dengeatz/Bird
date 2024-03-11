package com.example.demo.database;

import com.example.demo.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;



public class UserDB extends Database{
    public void addToDatabase(User user) {
        try{
            statement.execute(String.format("INSERT into user (name, email, password) values(\"%s\", \"%s\", \"%s\")", user.getName(), user.getEmail(), user.getPassword()));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ResultSet getPasswordDB(int id){
        try{
            resultSet = statement.executeQuery(String.format("SELECT password FROM user WHERE iduser = %d", id));
        } catch(SQLException e) {
            System.out.println(e);
        }
        return resultSet;
    }
}
