package com.example.demo.database;

import com.example.demo.Mudak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.Statement;
@Component
@Scope("singleton")
public class Database implements Mudak {
    static Connection con;
    static ResultSet resultSet;

    static Statement statement;

    public Database() {
        try {
            String url = "jdbc:mysql://localhost:3306/users";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "230905Ilya");
            System.out.println("Все удачно");
            statement = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e);

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    @Bean(name="getStatement")
    @Scope("singleton")
    public Statement getStatement() throws SQLException {
        return statement;
    }
    @Bean(name="getResultSet")
    @Scope("singleton")
    public ResultSet getResultSet() {
        return this.resultSet;
    }

    public ResultSet GetNameOfCurrentUser(int id){
        try {
            resultSet = statement.executeQuery(String.format("SELECT * FROM user WHERE iduser = %d", id));
        } catch(SQLException e) {
            System.out.println(e);
        }
        return resultSet;
    }



    public ResultSet GetNameOfCurrentUser(String name){
        try {
            resultSet = statement.executeQuery(String.format("SELECT * FROM user WHERE name = \"%s\"", name));
        } catch(SQLException e) {
            System.out.println(e);
        }
        return resultSet;
    }


    public void AddUser(String name, String email, String pass) throws SQLException {
        try {
            statement.execute(String.format("INSERT INTO user (name, email, password) VALUES (\"%s\", \"%s\", \"%s\")",name, email, pass));
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
}
