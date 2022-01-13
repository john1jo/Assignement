/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author CSS Chega
 */
public class DBConnection 
{
    private static final String url = "jdbc:mysql://localhost:3306/johnconnection";
    private static final String user = "root";
    private static final String password = "";
    private static final String driver = "com.mysql.jdbc.Driver";
    //String con;

    public Connection connMethod() throws ClassNotFoundException {
        Class.forName(driver);
        Connection con = null ;

        try {
            con = DriverManager.getConnection(url, user, password);
            //JOptionPane.showMessageDialog(null, "connected");

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "Error: " + ex);
        }
        return con;
    }
    
}
