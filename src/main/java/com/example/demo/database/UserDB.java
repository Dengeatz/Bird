package com.example.demo.database;

import com.example.demo.Mudak;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.plaf.nimbus.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;


@Component
public class UserDB implements Mudak {


    @Autowired()
    public void addToDatabase(User user) throws SQLException {
        statement.execute(String.format("INSERT into user (name, email, password) values(\"%s\", \"%s\", \"%s\")", user.getName(), user.getEmail(), Arrays.toString(user.decryptPassword(user.getPassword()))));
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
