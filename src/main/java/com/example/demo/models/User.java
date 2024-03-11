package com.example.demo.models;

import com.example.demo.database.Database;
import com.example.demo.database.UserDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;


public class User extends UserDB {
    int id;
    String name;
    String email;
    String password;
    Database db = new Database();
    public User() {
        System.out.println("Зарегистрируйтесь в системе");
        System.out.println(encryptPassword(getPasswordDB(22)));
    }
    public User(int id) {
        try{
            ResultSet user_info = db.GetNameOfCurrentUser(id);
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
        this.password = Arrays.toString(decryptPassword(password));

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
            ResultSet post_info = getPasswordDB(25);
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
