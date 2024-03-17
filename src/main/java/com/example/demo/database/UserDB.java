package com.example.demo.database;

import com.example.demo.Mudak;
import com.example.demo.models.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.plaf.nimbus.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;


@Component
@Scope("singleton")
public class UserDB implements Mudak {
    public static Database database = new Database();

    Statement statement = Database.statement;

    ResultSet resultSet = Database.resultSet;



    public void addToDatabase(User user)  {
        try {
            statement.execute(String.format("INSERT into user (name, email, password) values(\"%s\", \"%s\", \"%s\")", user.getName(), user.getEmail(), Arrays.toString(user.decryptPassword(user.getPassword()))));
        } catch (SQLException e) {
            System.out.println(e);;
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
