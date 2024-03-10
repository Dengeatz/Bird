package com.example.demo.database;


import java.sql.ResultSet;
import java.sql.SQLException;

public class PostDB extends Database {
    public ResultSet getPost() {
        try{
            resultSet = statement.executeQuery("SELECT * FROM posts");
            System.out.println(resultSet);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return resultSet;
    }
}
