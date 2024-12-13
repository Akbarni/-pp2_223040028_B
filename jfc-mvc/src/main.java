/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AKBAR NUR IMAN
 */
import model.mybatisutil;  
import model.usermapper;  
import org.apache.ibatis.session.SqlSession;  
import view.userview;  
import controller.usercontroller;  

public class main {  
    public static void main(String[] args) {  
        SqlSession session = mybatisutil.getSqlSession();  
        usermapper mapper = session.getMapper(usermapper.class);  

        userview view = new userview();  
        new usercontroller(view, mapper);  

        view.setVisible(true);  
    }  
}