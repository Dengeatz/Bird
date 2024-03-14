package com.example.demo.database;

import com.example.demo.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;


public class UserDB extends Database{
    public void addToDatabase(User user) {
        try {

            statement.execute(String.format("INSERT into user (name, email, password) values(\"%s\", \"%s\", \"%s\")", user.getName(), user.getEmail(), Arrays.toString(user.decryptPassword(user.getPassword()))));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public ResultSet getPasswordByLoginDB(String name) {
        try{
            resultSet = statement.executeQuery(String.format("SELECT * FROM user WHERE name = \"%s\"", name));
        } catch(SQLException e) {
            System.out.println(e);
        }
        return resultSet;
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
