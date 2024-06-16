/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employee.payroll;

/**
 *
 * @author ROG
 */
import java.sql.*;
import javax.swing.JOptionPane;

public class DBConnection {
 
    Connection conn = null;

    public static Connection java_DBConnection() {
   
        try{
            
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","root","butter");
        return conn;   
            
        }catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
           return null;
        
        }
}

    }