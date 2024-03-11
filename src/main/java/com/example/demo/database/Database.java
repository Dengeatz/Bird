package com.example.demo.database;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.Statement;
@Component
public class Database {
    static Connection con;
    static ResultSet resultSet;
    static Statement statement;
    @Bean
    public static void main() {
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
    public ResultSet GetNameOfCurrentUser(int id){
        try {
            resultSet = statement.executeQuery(String.format("SELECT * FROM user WHERE iduser = %d", id));
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
