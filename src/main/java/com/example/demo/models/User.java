package com.example.demo.models;

import com.example.demo.Mudak;
import com.example.demo.database.Database;
import com.example.demo.database.UserDB;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;


public class User extends UserDB {
    int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min=2,max=30,message = "Имя не может быть меньше 2 символов или больше 30")
    String name;
    @Email
    String email;
    @Min(value=6, message="Пароль не может быть меньше 6 символов")
    String password;


    UserDB userDB = new UserDB();
    Database database = userDB.database;
    public User() {
        System.out.println("Зарегистрируйтесь в системе");

    }

    public User(int id) {
        try{

            ResultSet user_info = database.GetNameOfCurrentUser(id);
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


    public User(String name) throws SQLException {
        try {
            ResultSet user_info = database.GetNameOfCurrentUser(name);
            if(user_info.next()) {
                System.out.println("Пользователь найден");
                this.name = user_info.getString("name");
                this.email = user_info.getString("email");
                this.password = user_info.getString("password");
            } else {
                System.out.println("Пользователь не найден");
                this.name = "Такого пользователя не найдено";
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public String[] loginToAccount(User user) throws SQLException {
        ResultSet result = userDB.getPasswordByLoginDB(user.name);

        while(result.next()) {
            if(Objects.equals(user.name, result.getString("name"))) {
                if(Objects.equals(user.password, result.getString("password"))) {
                    return new String[] {user.name, user.password};
                } else {
                    return new String[] {"Password not valid"};
                }

            } else {
                return new String[] {"Login not valid"};
            }
            }
        return new String[] {"User not find"};
        }
    public String getName() {
        return name;
    }
    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    public char[] decryptPassword(String password){
        int key = 25;
        char[] simvol = new char[password.length()];

        for(int i = 0; i < password.length(); i++) {
            if(Character.isLetter(password.charAt(i)) && Character.isUpperCase(password.charAt(i))) {
                simvol[i] = (char) (((((int) (password.charAt(i))) - 65 + key) % 26) + 65);
            } else if (Character.isLetter(password.charAt(i)) && Character.isLowerCase(password.charAt(i))) {
                simvol[i] = (char) (((((int) (password.charAt(i))) - 97 + key) % 26) + 97);
            } else {
                    simvol[i] = password.charAt(i);
                }
            }
        return simvol;
    }


    public String encryptPassword(ResultSet resultSet){
        String answer = "";
        String encrypted = "";
        try{
            ResultSet post_info = userDB.getPasswordDB(25);
            while(post_info.next()) {
                answer = post_info.getString("password");
                int key = 27;
                char[] simvol = new char[answer.length()];
                for(int i = 0; i < answer.length(); i++) { /* Шифр цезаря :0 */
                    if(Character.isLetter(answer.charAt(i)) && Character.isUpperCase(answer.charAt(i))) {
                        simvol[i] = (char) (((((int) (answer.charAt(i))) - 65 + key) % 26) + 65);
                    } else if (Character.isLetter(answer.charAt(i)) && Character.isLowerCase(answer.charAt(i))) {
                        simvol[i] = (char) (((((int) (answer.charAt(i))) - 97 + key) % 26) + 97);
                    } else {
                        simvol[i] = answer.charAt(i);
                    }
                }
            answer = Arrays.toString(simvol);
            encrypted = answer.replaceAll("[,\s\\[\\]]", ""); /* Убираем лишние символы */

            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        return encrypted; /* Это расшифрованный пароль */
    }

}
