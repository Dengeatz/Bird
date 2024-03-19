package com.example.demo.database;

import com.example.demo.Mudak;
import com.example.demo.models.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Objects;


@Component
@Scope("singleton")
public class UserDB implements Mudak {
    public static Database database = new Database();

    Statement statement = Database.statement;

    ResultSet resultSet = Database.resultSet;
    User user = new User();

    public UserDB() {
        System.out.println("Зарегистрируйтесь в системе");
    }

    public UserDB(int id) {
        try{
            ResultSet user_info = database.GetNameOfCurrentUser(id);
            while(user_info.next()) {
                new User(id,user_info.getString("name"),
                        user_info.getString("email"),
                        user_info.getString("password"));
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    public UserDB(String name) throws SQLException {
        try {
            ResultSet user_info = database.GetNameOfCurrentUser(name);
            if(user_info.next()) {
                System.out.println("Пользователь найден");
                setUser(new User(user_info.getInt("iduser"),user_info.getString("name"),
                        user_info.getString("email"),
                        user_info.getString("password")));
            } else {
                System.out.println("Пользователь не найден");
                new User("Такого пользователя не найдено");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public String[] loginToAccount(User user) throws SQLException {
        ResultSet result = getPasswordByLoginDB(user.name);

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

    public void addToDatabase(User user)  {
        try {
            statement.execute(String.format("INSERT into user (name, email, password) values(\"%s\", \"%s\", \"%s\")", user.getName(), user.getEmail(), Arrays.toString(decryptPassword(user.getPassword()))));
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
