package com.example.demo.database;


import java.sql.ResultSet;
import java.sql.SQLException;

public class PostDB extends Database {
    public String getAuthorOfPost(int id) throws SQLException {
        String author = "";
        resultSet = statement.executeQuery(String.format("SELECT u.name FROM posts AS a INNER JOIN user AS u ON u.iduser=\"%d\"", id));
        while(resultSet.next()) {
            author = resultSet.getString("name");
        }
        return author;
    }

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
