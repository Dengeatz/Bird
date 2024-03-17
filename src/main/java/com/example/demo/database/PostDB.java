package com.example.demo.database;


import com.example.demo.Mudak;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class PostDB implements Mudak {


    Statement statement = Database.statement;

    ResultSet resultSet = Database.resultSet;


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
        } catch (SQLException e) {
            System.out.println(e);
        }
        return resultSet;
    }
}
