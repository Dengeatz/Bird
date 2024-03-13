package com.example.demo.models;

import com.example.demo.database.PostDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Posts extends PostDB{
    List<Post> result = new ArrayList<>();

    public List<Post> getPosts(){
        return result;
    }

    public Posts() {
        try{
            ResultSet post_info = getPost();
            while(post_info.next()) {
                Post post = new Post(post_info.getString("postname"),post_info.getString("postdescription"),post_info.getString("posttime"),getAuthorOfPost(post_info.getInt("udi")));
                result.add(post);
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

}
