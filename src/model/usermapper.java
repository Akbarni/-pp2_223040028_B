/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author AKBAR NUR IMAN
 */


import java.util.List;  
import org.apache.ibatis.annotations.*;  

public interface usermapper {  
    @Select("SELECT * FROM users")  
    List<user> getAllUsers();  

    @Insert("INSERT INTO users (name, email) VALUES (#{name}, #{email})")  
    void insertUser(user user);  
}
